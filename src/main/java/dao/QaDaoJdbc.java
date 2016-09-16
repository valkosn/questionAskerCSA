package dao;

import enteties.QA;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.ConnectionProvider;
import utils.JsonFileConverter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valko Serhii on 06-Sep-16.
 */
public class QaDaoJdbc implements QaDao {

    private final Logger LOG = Logger.getLogger(QaDaoJdbc.class);
    private String filePath;
    private Connection connection;

    public QaDaoJdbc(String filePath, ConnectionProvider connectionProvider) throws SQLException {
        this.filePath = filePath;
        this.connection = connectionProvider.getConnection();

    }

    public void init() {
        long startTime = System.currentTimeMillis();
        initDB();
        try (final PreparedStatement questionStatement = this.connection.prepareStatement("INSERT INTO questions (question_value, category_id) VALUES (?, ?)");
             final PreparedStatement answersStatement = this.connection.prepareStatement("INSERT INTO answers (question_id, answer_value, right_answer) VALUES (?, ?, ?)");
             final PreparedStatement categoryStatement = this.connection.prepareStatement("WITH s AS (SELECT uid, category_value FROM categories WHERE category_value = ?), i AS (INSERT INTO categories (category_value) SELECT ? WHERE NOT exists(SELECT 1 FROM s) RETURNING uid, category_value) SELECT uid, category_value FROM i UNION ALL SELECT uid, category_value FROM s")) {
            List<QA> qaList = new JsonFileConverter().toJavaObjectViaJackson(filePath);
            for (int i = 0; i < qaList.size(); i++) {
                categoryStatement.setString(1, qaList.get(i).getCategory());
                categoryStatement.setString(2, qaList.get(i).getCategory());
                ResultSet categoryResultSet = categoryStatement.executeQuery();
                categoryResultSet.next();
                int categoryUid = categoryResultSet.getInt("uid");
                questionStatement.setString(1, qaList.get(i).getQuestion());
                questionStatement.setInt(2, categoryUid);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("Data base initialization time is " + (System.currentTimeMillis() - startTime) + " millis");

    }

    private void initDB() {
        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS answers");
            statement.executeUpdate("DROP TABLE IF EXISTS questions");
            statement.executeUpdate("DROP TABLE IF EXISTS categories");
            statement.execute("CREATE TABLE IF NOT EXISTS categories (uid SERIAL PRIMARY KEY, category_value VARCHAR(50))");
            statement.execute("CREATE TABLE IF NOT EXISTS questions (uid SERIAL PRIMARY KEY, category_id INT REFERENCES categories(uid), question_value VARCHAR(400))");
            statement.execute("CREATE TABLE IF NOT EXISTS answers (uid SERIAL PRIMARY KEY, question_id INT NOT NULL REFERENCES questions (uid), answer_value VARCHAR(400), right_answer BOOL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<QA> getRandomQuestions(int amount, String[] categories) {
        List<QA> qaList = new ArrayList<>();
        String categoryQueryPrefix = "q1.category_id = ";
        StringBuilder categoryQuery = new StringBuilder("WHERE ");
        if (categories != null && categories.length != 0) {
            for (int i = 0; i < categories.length; i++) {
                categoryQuery.append(categoryQueryPrefix).append(categories[i]);
                if (i < categories.length - 1) {
                    categoryQuery.append(" OR ");
                }
            }
        } else {
            categoryQuery.delete(0, categoryQuery.length());
        }
        try (final Statement questionStatement = this.connection.createStatement();
             final ResultSet resultSet = questionStatement.executeQuery("SELECT q.uid, q.question_value, a.answer_value FROM questions q JOIN answers a ON q.uid = a.question_id AND q.uid IN (SELECT q1.uid FROM questions q1 " + categoryQuery + " ORDER BY RANDOM() LIMIT " + amount + ")")) {

            qaList = resultSetObjectMapper(resultSet);
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
    public int getQuestionAmount() {
        int count = 0;
        try (final Statement statement = this.connection.createStatement();
             final ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM questions")) {
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void setQuestions(List<QA> questions) {
        throw new NotImplementedException();
    }

    private List<QA> resultSetObjectMapper(ResultSet resultSet) throws SQLException {
        List<QA> qaList = new ArrayList<>();
        QA prQA = null;
        while (resultSet.next()) {
            int uid = resultSet.getInt("uid");
            if (prQA == null || prQA.getId() != uid) {
                prQA = new QA(uid, resultSet.getString("question_value"), new ArrayList<>(4));
                qaList.add(prQA);
            }
            prQA.getAnswers().add(resultSet.getString("answer_value"));
        }
        resultSet.close();
        return qaList;
    }
}
