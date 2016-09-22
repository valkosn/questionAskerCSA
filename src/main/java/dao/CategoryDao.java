package dao;

import java.util.Map;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface CategoryDao {

    Map<Integer, String> getAllCategories();

    void add(String category);

    void delete(String id);
}
