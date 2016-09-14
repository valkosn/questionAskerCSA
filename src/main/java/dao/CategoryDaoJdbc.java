package dao;

import service.ISettings;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class CategoryDaoJdbc implements CategoryDao {

    private Connection connection;

    public CategoryDaoJdbc(ISettings settings) {
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
}
