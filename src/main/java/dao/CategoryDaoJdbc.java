package dao;

import enteties.QA;
import service.QaService;
import utils.ConnectionProvider;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class CategoryDaoJdbc implements CategoryDao {

    private Connection connection;
    private QaService qaService;

    public void setConnection(ConnectionProvider connectionProvider) {
        this.connection = connectionProvider.getConnection();
    }

    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @Override
    public Map<Integer, String> getAllCategories() {
        Map<Integer, String> allCategories = new HashMap<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()) {
                allCategories.put(resultSet.getInt("uid"), resultSet.getString("category_value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCategories;
    }

    @Override
    public void add(String category) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories (category_value) VALUES (?)")) {
            preparedStatement.setString(1, category);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        List<QA> qaList = qaService.getAllQuestionByCategory(id);
        if (!(qaList == null) && !qaList.isEmpty()) {
            for (QA question : qaList) {
                qaService.deleteQuestion(question.getId());
            }
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE uid = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
