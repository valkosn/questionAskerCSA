package service;

import dao.CategoryDao;
import entity.Category;
import java.util.Map;

/**
 * Created by Valko Serhii on 14-Sep-16.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDAO;

    public void setCategoryDAO(CategoryDao categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Map<Integer, String> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public Category findByCategoryName (String categoryName){
        return categoryDAO.findByCategoryName(categoryName);
    }

    @Override
    public int add(Category category) {
        int categoryId;
        Category categoryFromDB = findByCategoryName(category.getCategoryName());
        if (categoryFromDB == null) {
            categoryId = categoryDAO.add(category.getCategoryName());
        } else {
            categoryId = categoryFromDB.getCategoryId();
        }
        return categoryId;
    }

    @Override
    public void remove(int id) {
        categoryDAO.delete(id);
    }
}
