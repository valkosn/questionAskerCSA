package com.asker.dao;

import com.asker.entity.Category;
import com.asker.entity.QA;
import com.asker.service.QaService;
import com.asker.utils.ConnectionProvider;

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
  public int add(String category) {
    int categoryId = -1;
    try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories (category_value) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, category);
      preparedStatement.execute();
      ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
      if (generatedKeys != null && generatedKeys.next()) {
        categoryId = generatedKeys.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return categoryId;
  }

  @Override
  public void delete(int id) {
    List<QA> qaList = qaService.getAllQuestionByCategory(id);
    if (!(qaList == null) && !qaList.isEmpty()) {
      for (QA question : qaList) {
        qaService.deleteQuestion(question.getQuestionId());
      }
    }
    try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE uid = ?")) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Category findByCategoryName(String categoryName) {
    Category category = null;
    try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE category_value = ?")) {
      preparedStatement.setString(1, categoryName);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet != null && resultSet.next()) {
        category = new Category(resultSet.getInt(1), categoryName);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return category;
  }
}
