package dao;

import utils.ConnectionProvider;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class CategoryDaoJdbc implements CategoryDao {

    private Connection connection;

    public void setConnection(ConnectionProvider connectionProvider ) {
        this.connection = connectionProvider.getConnection();
    }

    @Override
    public Map<Integer, String> getAllCategories() {
        Map<Integer, String> allCategories = new HashMap<>();
        try(Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while(resultSet.next()){
                allCategories.put(resultSet.getInt("uid"), resultSet.getString("category_value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCategories;
    }

    @Override
    public void add(String category) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories (category_value) VALUES (?)")) {
            preparedStatement.setString(1, category);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE uid = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
