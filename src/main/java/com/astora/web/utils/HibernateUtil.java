package com.astora.web.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
public class HibernateUtil {
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionAnnotationFactory() throws Exception {
        SessionFactory sessionFa = null;
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFa = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            throw ex;
        }
        return sessionFa;
    }

    public static SessionFactory getSessionFactory() throws Exception {
        if (sessionFactory == null) {
            sessionFactory = buildSessionAnnotationFactory();
        }
        return sessionFactory;
    }

}
