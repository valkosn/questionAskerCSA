package dao;

import entity.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import service.HibernateUtil;
import service.QaService;

import javax.persistence.EntityManager;
import java.util.Map;

/**
 * Created by Valko Serhii on 11/23/2016.
 */
public class CategoryDaoJpaHibernate implements CategoryDao {

    private Session session;
    private EntityManager entityManager;
    private QaService qaService;

    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @Override
    public Map<Integer, String> getAllCategories() {
//        session = HibernateUtil.getSessionFactory().openSession();
        return null;
    }

    @Override
    public int add(String categoryName) {
        entityManager = HibernateUtil.getEm();
        entityManager.getTransaction().begin();
        entityManager.persist(new Category(categoryName));
        entityManager.getTransaction().commit();
        entityManager.close();
//        session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(new Category(categoryName));
//        session.getTransaction().commit();
//        session.close();

        return 42;
    }

    @Override
    public void delete(int id) {
//        session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        Category category = new Category();
//        category.setCategoryId(id);
//        session.delete(category);
//        session.getTransaction().commit();
//        session.close();

    }

    @Override
    public Category findByCategoryName(String categoryName) {
        entityManager = HibernateUtil.getEm();
        entityManager.getTransaction().begin();
        entityManager.createQuery("SELECT Category from Category where Category.categoryName = categoryName");

        return null;
    }
}
