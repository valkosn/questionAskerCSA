package dao;

import entity.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import service.HibernateUtil;
import service.QaService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Valko Serhii on 11/23/2016.
 */
public class CategoryDaoJpa implements CategoryDao {

    private EntityManager entityManager;
    private QaService qaService;

    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @Override
    public Map<Integer, String> getAllCategories() {
        Map<Integer, String> resultMap = new HashMap<>();
        entityManager = HibernateUtil.getEm();
        List<Category> categoryList = entityManager.createQuery("select cat from Category cat").getResultList();
        for(Category cat : categoryList){
            resultMap.put(cat.getCategoryId(), cat.getCategoryName());
        }
        return resultMap;
    }

    @Override
    public int add(String categoryName) {
        entityManager = HibernateUtil.getEm();
        entityManager.getTransaction().begin();
        entityManager.persist(new Category(categoryName));
        entityManager.getTransaction().commit();
        entityManager.close();
        return findByCategoryName(categoryName).getCategoryId();
    }

    @Override
    public void delete(int id) {
        entityManager = HibernateUtil.getEm();
        Category category = entityManager.find(Category.class, id);
        if(category != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(category);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        entityManager = HibernateUtil.getEm();
        entityManager.getTransaction().begin();
        Category category = null;
        try {
            category = (Category) entityManager
                    .createQuery("SELECT cat from Category cat where cat.categoryName = " + categoryName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
        return category;
    }
}
