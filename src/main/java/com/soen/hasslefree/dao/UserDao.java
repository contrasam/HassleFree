/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.dao;

import com.soen.hasslefree.models.User;
import com.soen.hasslefree.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PradeepSamuel
 */
public class UserDao {

    public void addUser(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            // transaction.commit();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void deleteUser(long userId) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            User user = (User) session.load(User.class, new Long(userId));
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<User>();;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            users = (Set)session.createQuery("select concat(firstName, ' ', lastName) as name from user").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    public Set<User> getUserById(long userId) {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String queryString = "from user where addressId  = :id";
            Query query = session.createQuery(queryString);
            query.setLong("id", userId);
            //cust = (Customer) query.uniqueResult();
            Set<User> userSet = (Set) query.list();
            if (userSet.size() > 0) {
                return userSet;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
}


