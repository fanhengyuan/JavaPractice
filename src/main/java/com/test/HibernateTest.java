package com.test;

import com.domain.Customer;
import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class HibernateTest {
/*    private static HibernateTest instance;

    private HibernateTest () {}

    public static synchronized HibernateTest getInstance()
    {
        if(instance == null) {
            instance = new HibernateTest();
        }
        return instance;
    }*/

/*    @Test
    public void test1(String ip, String name, int level)
    {
        try {
            Session session = HibernateUtil.openSession();

            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(ip);
            customer.setLevel(String.valueOf(level));

            session.save(customer);

            // 5 释放资源
            session.close();
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }*/

    @Test
    public void insertOne()
    {
        // 添加一条记录
        Session session = HibernateUtil.openSession();

        Customer customer = new Customer();
        customer.setName("O(∩_∩)O哈哈~");

        session.save(customer);

        // 5 释放资源
        session.close();
    }

    @Test
    public void selectOne()
    {
        // 查询一条记录
        Session session = HibernateUtil.openSession();

        Customer customer = session.get(Customer.class, 1L);
        System.out.println(customer);
        session.close();
    }

    @Test
    public void selectAll()
    {
        // 查询所有记录
        Session session = HibernateUtil.openSession();

        // HQL
        Query query = session.createQuery("from com.domain.Customer");
        List<com.domain.Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }

        session.close();
    }

    @Test
    public void updateOne()
    {
        // 修改
        Session session = HibernateUtil.getCurrentSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        // 查询
        Customer customer = session.get(Customer.class, 3L);

        customer.setName("修改测试 O(∩_∩)O哈哈~");
        session.update(customer);
        System.out.println(customer);

        transaction.commit();
        session.close();
    }

    @Test
    public void deleteOne()
    {
        // 删除记录
        Session session = HibernateUtil.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        // 查询
        Customer customer = session.get(Customer.class, 1L);

        session.delete(customer);

        transaction.commit();
        session.close();
    }
}