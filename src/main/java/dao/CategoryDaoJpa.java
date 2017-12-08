package dao;

import entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Valko Serhii on 11/23/2016.
 */

@Repository
@Transactional
public class CategoryDaoJpa implements CategoryDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Map<Integer, String> getAllCategories() {
        Map<Integer, String> resultMap = new HashMap<>();
        List<Category> categoryList = entityManager.createNamedQuery("getAllCategories", Category.class).getResultList();
        for(Category cat : categoryList){
            resultMap.put(cat.getCategoryId(), cat.getCategoryName());
        }
        return resultMap;
    }

    @Override
    public int add(String categoryName) {
        entityManager.persist(new Category(categoryName));
        return findByCategoryName(categoryName).getCategoryId();
    }

    @Override
    public void delete(int id) {
        Category category = entityManager.find(Category.class, id);
        if(category != null) {
            entityManager.remove(category);
        }
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        List<Category> result = entityManager.createNamedQuery("getCategoryByName", Category.class).getResultList();

        return result.size() == 1 ? result.get(0) : null;
    }
}
