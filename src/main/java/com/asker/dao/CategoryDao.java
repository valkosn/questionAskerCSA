package com.asker.dao;

import com.asker.entity.Category;

import java.util.Map;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface CategoryDao {

    Map<Integer, String> getAllCategories();

    int add(String category);

    void delete(int id);

    Category findByCategoryName(String categoryName);
}
