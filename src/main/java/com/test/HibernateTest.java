package com.test;

import com.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest
{
    @Test
    public void test1(String ip, String name, int level)
    {
        try {
            // 1 加载配置文件
            Configuration configure = new Configuration().configure();

            // 2 创建 JDBC 连接池
            SessionFactory sessionFactory = configure.buildSessionFactory();

            // 3 创建 session JDBC 连接池
            Session session = sessionFactory.openSession();

            // 4 保存
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(ip);
            customer.setLevel(String.valueOf(level));

            session.save(customer);

            // 5 释放资源
            session.close();
            sessionFactory.close();
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
