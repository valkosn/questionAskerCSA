package com.asker.service;

import com.asker.entity.Category;

import java.util.Map;

/**
 * Created by Valko Serhii on 14-Sep-16.
 */
public interface CategoryService {
    Map<Integer, String> getAllCategories();

    Category findByCategoryName(String categoryName);

    int add(Category category);

    void remove(int id);
}
