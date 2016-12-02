package utils;

import dao.QaDaoJdbc;
import entity.Category;
import entity.QA;
import org.apache.log4j.Logger;
import service.CategoryService;
import service.QaService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Orest Lozynskyy
 * @since 11/9/2016
 */
public class DBInitializerImpl implements DBInitializer {

  private final Logger LOG = Logger.getLogger(QaDaoJdbc.class);
  private String filePath;
  private Connection connection;
  private QaService qaService;
  private CategoryService categoryService;

  public DBInitializerImpl(String filePath, ConnectionProvider connectionProvider) {
    this.filePath = filePath;
    this.connection = connectionProvider.getConnection();
  }

  public void setQaService(QaService qaService) {
    this.qaService = qaService;
  }

  public void setCategoryService(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public void init() throws IOException {
    long startTime = System.currentTimeMillis();
    try (Statement statement = this.connection.createStatement()) {
      statement.executeUpdate("DROP TABLE IF EXISTS answers CASCADE");
      statement.executeUpdate("DROP TABLE IF EXISTS questions CASCADE");
      statement.executeUpdate("DROP TABLE IF EXISTS categories CASCADE");
      statement.execute("CREATE TABLE IF NOT EXISTS categories (uid SERIAL PRIMARY KEY, category_value VARCHAR(50))");
      statement.execute("CREATE TABLE IF NOT EXISTS questions (uid SERIAL PRIMARY KEY, category_id INT REFERENCES categories(uid), question_value VARCHAR(400))");
      statement.execute("CREATE TABLE IF NOT EXISTS answers (uid SERIAL PRIMARY KEY, question_id INT NOT NULL REFERENCES questions (uid), answer_value VARCHAR(400), right_answer BOOL)");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    List<QA> qaList = new JsonFileConverter().toJavaObjectViaJackson(filePath);
    for (QA qa : qaList) {
      int categoryId = categoryService.add(new Category(qa.getCategory().getCategoryName()));
      qa.getCategory().setCategoryId(categoryId);
      qaService.save(qa);
    }
    LOG.info("Data base initialization time is " + (System.currentTimeMillis() - startTime) + " millis");
  }
}
