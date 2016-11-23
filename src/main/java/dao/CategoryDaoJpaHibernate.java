package dao;

import enteties.Category;
import org.hibernate.Session;
import service.HibernateUtil;
import service.QaService;

import java.util.Map;

/**
 * Created by Valko Serhii on 11/23/2016.
 */
public class CategoryDaoJpaHibernate implements CategoryDao{

    private Session session;
    private QaService qaService;

    public void setQaService(QaService qaService) {
        this.qaService = qaService;
    }

    @Override
    public Map<Integer, String> getAllCategories() {
        session = HibernateUtil.getSessionFactory().openSession();
        return null;
    }

    @Override
    public int add(String category) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new Category(category));
        session.getTransaction().commit();
        session.close();
        return 0;
    }

    @Override
    public void delete(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category category = new Category();
        category.setCategoryId(id);
        session.delete(category);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return null;
    }
}
