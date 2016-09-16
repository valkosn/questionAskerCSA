package dao;

import utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class CategoryDaoJdbc implements CategoryDao {

    private Connection connection;


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

    public void setConnection(ConnectionProvider connectionProvider ) {
        this.connection = connectionProvider.getConnection();
    }
}
