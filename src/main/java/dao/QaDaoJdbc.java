package dao;

import enteties.QA;
import org.apache.log4j.Logger;
import service.Settings;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.JsonFileConverter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valko Serhii on 06-Sep-16.
 */
public class QaDaoJdbc implements QaDao {

    private final Logger LOG = Logger.getLogger(QaDaoJdbc.class);
    private String filePath;
    // FIXME: 08-Sep-16 Fix Spring instantiation issue in application-context.xml
    private Settings settings = new Settings("askerDB.properties");
    private Connection connection;

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public QaDaoJdbc(String filePath) throws SQLException {
        this.filePath = filePath;
        try {
            Class.forName(settings.getValue("jdbc.driver_class"));
            this.connection = DriverManager.getConnection(
                    settings.getValue("jdbc.url"), settings.getValue("jdbc.login"), settings.getValue("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        long startTime = System.currentTimeMillis();
        initDB();
        try (final PreparedStatement questionStatement = this.connection.prepareStatement("INSERT INTO questions (question_value) VALUES (?)");
             final PreparedStatement answersStatement = this.connection.prepareStatement("INSERT INTO answers (question_id, answer_value, right_answer) VALUES (?, ?, ?);")) {
            List<QA> qaList = new JsonFileConverter().toJavaObject(filePath);
            for (int i = 0; i < qaList.size(); i++) {
                questionStatement.setString(1, qaList.get(i).getQuestion());
                questionStatement.execute();
                questionStatement.clearParameters();
                List<String> answersList = qaList.get(i).getAnswers();
                for (int j = 0; j < answersList.size(); j++) {
                    answersStatement.setInt(1, i + 1);
                    answersStatement.setString(2, answersList.get(j));
                    answersStatement.setBoolean(3, j == 0);
                    answersStatement.execute();
                    answersStatement.clearParameters();
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        LOG.info("Data base initialization time is " + (System.currentTimeMillis() - startTime) + " millis");

    }

    private void initDB(){
        try(Statement statement = this.connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS answers");
            statement.executeUpdate("DROP TABLE IF EXISTS questions");
            statement.execute("CREATE TABLE IF NOT EXISTS questions (uid SERIAL PRIMARY KEY, question_value VARCHAR(400))");
            statement.execute("CREATE TABLE IF NOT EXISTS answers (uid SERIAL PRIMARY KEY, question_id INT NOT NULL REFERENCES questions (uid), answer_value VARCHAR(400), right_answer BOOL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<QA> getRandomQuestions(int amount) {
        List<QA> qaList = new ArrayList<>();
        try (final Statement questionStatement = this.connection.createStatement();
             final ResultSet questionResultSet = questionStatement.executeQuery("SELECT questions.uid, questions.question_value FROM questions ORDER BY RANDOM() LIMIT " + amount)) {
            while (questionResultSet.next()) {
                List<String> answersList = new ArrayList<>();
                int questionUID = questionResultSet.getInt("uid");
                try (final PreparedStatement answersStatement = this.connection.prepareStatement("SELECT answer_value FROM answers WHERE question_id = ?")) {
                    answersStatement.setInt(1, questionUID);
                    ResultSet answersResultSet = answersStatement.executeQuery();
                    while (answersResultSet.next()) {
                        answersList.add(answersResultSet.getString("answer_value"));
                    }
                    answersResultSet.close();
                }

                qaList.add(new QA(questionUID, questionResultSet.getString("question_value"), answersList));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qaList;
    }

    @Override
    public List<QA> getAllQuestions() {
        List<QA> qaList = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet resultSet = statement.executeQuery("SELECT questions.uid, questions.question_value, answer_value FROM questions INNER JOIN answers ON questions.uid = answers.question_id")) {
            qaList = resultSetObjectMapper(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return qaList;
    }

    @Override
    public String getCorrectAnswer(int id) {
        String correctAnswer = null;
        try (final PreparedStatement statement = this.connection.prepareStatement("SELECT answer_value FROM answers WHERE question_id = ? AND right_answer = TRUE;")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            correctAnswer = resultSet.getString("answer_value");
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return correctAnswer;
    }

    @Override
    public void setQuestions(List<QA> questions) {
        throw new NotImplementedException();
    }

    private List<QA> resultSetObjectMapper(ResultSet resultSet) throws SQLException {
        List<QA> qaList = new ArrayList<>();
        List<String> answersList = new ArrayList<>();
        int prID = 0;
        while (resultSet.next()) {
            int uid = resultSet.getInt("uid");
            String question_value = resultSet.getString("question_value");
            if (prID != uid) {
                prID = uid;
                if (!answersList.isEmpty()) {
                    qaList.add(new QA(uid, question_value, answersList));
                }
                answersList.clear();
            }
            answersList.add(resultSet.getString("answer_value"));
        }
        resultSet.close();
        return qaList;
    }
}
