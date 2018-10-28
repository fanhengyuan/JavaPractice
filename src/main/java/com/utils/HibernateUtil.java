package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static {
        Configuration configure = new Configuration().configure();

        sessionFactory = configure.buildSessionFactory();
    }

    public static Session openSession()
    {
        Session s = sessionFactory.openSession();
        return s;
    }
}
