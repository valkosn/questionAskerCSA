package dao;

import entity.Answer;
import entity.Category;
import entity.QA;
import utils.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valko Serhii on 06-Sep-16.
 */
public class QaDaoJdbc implements QaDao {

    private Connection connection;

    public QaDaoJdbc(ConnectionProvider connectionProvider) throws SQLException {
        this.connection = connectionProvider.getConnection();
    }

    @Override
    public int save(QA qa) {
        int questionId = qa.getQuestionId();
        if (questionId == 0) {
            try (PreparedStatement questionStatement = connection.prepareStatement("INSERT INTO questions (question_value, category_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                questionStatement.setString(1, qa.getQuestion());
                questionStatement.setInt(2, qa.getCategory().getCategoryId());
                questionStatement.execute();
                ResultSet generatedKeys = questionStatement.getGeneratedKeys();
                if (generatedKeys != null && generatedKeys.next()) {
                    questionId = generatedKeys.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            QA viceQA = getQuestion(qa.getQuestionId());
            try (PreparedStatement questionStatement = connection.prepareStatement("UPDATE questions SET question_value = ?, category_id = ? WHERE questions.uid = ?")) {
                questionStatement.setString(1, qa.getQuestion());
                questionStatement.setInt(2, qa.getCategory().getCategoryId());
                questionStatement.setInt(3, viceQA.getQuestionId());
                questionStatement.executeUpdate();
                for (Answer answer : viceQA.getAnswers()) {
                    deleteAnswer(answer.getAnswerId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try (PreparedStatement answerStatement = connection.prepareStatement("INSERT INTO answers (question_id, answer_value, right_answer) VALUES (?, ?, ?)")) {
            List<Answer> answersList = qa.getAnswers();
            for (int j = 0; j < answersList.size(); j++) {
                answerStatement.setInt(1, questionId);
                answerStatement.setString(2, answersList.get(j).getAnswer());
                answerStatement.setBoolean(3, j == 0);
                answerStatement.execute();
                answerStatement.clearParameters();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionId;
    }

    //TODO: SQL injection is possible
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
             final ResultSet resultSet = questionStatement.executeQuery("SELECT q.uid, q.question_value, a.uid as answer_uid, a.answer_value, q.category_id as category_uid FROM questions q JOIN answers a ON q.uid = a.question_id AND q.uid IN (SELECT q1.uid FROM questions q1 " + categoryQuery + " ORDER BY RANDOM() LIMIT " + amount + ")")) {

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
             final ResultSet resultSet = statement.executeQuery("SELECT questions.uid, questions.question_value, answers.uid AS answer_uid, answers.answer_value, questions.category_id AS category_uid FROM questions INNER JOIN answers ON questions.uid = answers.question_id ORDER BY question_id")) {
            qaList = resultSetObjectMapper(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qaList;
    }

    @Override
    public List<QA> getAllQuestions(int categoryID) {
        List<QA> qaList = new ArrayList<>();
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT questions.uid, questions.question_value, answers.uid AS answer_uid, answers.answer_value, questions.category_id AS category_uid FROM questions INNER JOIN answers ON questions.uid = answers.question_id WHERE category_id = ?");) {
            preparedStatement.setInt(1, categoryID);
            ResultSet resultSet = preparedStatement.executeQuery();
            qaList = resultSetObjectMapper(resultSet);
            resultSet.close();
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
    public QA getQuestion(int id) {
        QA qa = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT questions.uid, questions.question_value, answers.uid AS answer_uid, answers.answer_value, questions.category_id AS category_uid FROM questions INNER JOIN answers ON questions.uid = answers.question_id WHERE questions.uid = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<QA> qaList = resultSetObjectMapper(resultSet);
            if (!(qaList == null) && !qaList.isEmpty()) {
                qa = qaList.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qa;
    }

    @Override
    public void deleteQuestion(int id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM questions WHERE uid = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAnswer(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM answers WHERE uid = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<QA> resultSetObjectMapper(ResultSet resultSet) throws SQLException {
        List<QA> qaList = new ArrayList<>();
        QA prQA = null;
        while (resultSet.next()) {
            int uid = resultSet.getInt("uid");
            int categoryUID = resultSet.getInt("category_uid");
            if (prQA == null || prQA.getQuestionId() != uid) {
                Category category = new Category(categoryUID, null);
                prQA = new QA(uid, resultSet.getString("question_value"), new ArrayList<>(4), category);
                qaList.add(prQA);
            }
            Answer answer = new Answer(resultSet.getInt("answer_uid"), resultSet.getString("answer_value"));
            prQA.getAnswers().add(answer);
        }
        resultSet.close();
        return qaList;
    }
}
