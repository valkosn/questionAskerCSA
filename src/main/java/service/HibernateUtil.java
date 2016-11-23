package service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Valko Serhii on 11/23/2016.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        sessionFactory = cfg.buildSessionFactory(registryBuilder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
