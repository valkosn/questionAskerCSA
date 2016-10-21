package service;

import java.util.Map;

/**
 * Created by Valko Serhii on 14-Sep-16.
 */
public interface CategoryService {
    Map<Integer, String> getAllCategories();

    void add(String category);

    void remove(int id);
}
