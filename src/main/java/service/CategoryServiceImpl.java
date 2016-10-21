package service;

import dao.CategoryDao;
import java.util.Map;

/**
 * Created by Valko Serhii on 14-Sep-16.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDAO;

    private QaService qaService;

    public void setCategoryDAO(CategoryDao categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @Override
    public Map<Integer, String> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public void add(String category) {
        Map<Integer, String> categories = getAllCategories();
        if (!categories.containsValue(category)) {
            categoryDAO.add(category);
        }
    }

    @Override
    public void remove(int id) {
        categoryDAO.delete(id);
    }
}
