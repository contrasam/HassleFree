/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Khalid
 */
public class ObjectDao {

    private static SessionFactory factory;
    private String message;
    

    public ObjectDao() {
        message="";
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
           message="Failed to create sessionFactory object." + ex;
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String getMessage() {
        return message;
    }


    public Long addObject(Object object) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long physicianID = null;
        try {
            tx = session.beginTransaction();
            // Adding Object
            physicianID = (Long) session.save(object);
            this.message = "Info addes Successfully!";
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return physicianID;
    }
        public void addOrUpdateObject(Object object) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Adding Object
            session.saveOrUpdate(object);
            this.message = "Info addes or update Successfully!";
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    /* Method to  READ all the Physicians */

    public ArrayList getAllObjects(String tableName) {
        Session session = factory.openSession();
        ArrayList objects = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Get All Physicians 
          objects = (ArrayList) session.createQuery("FROM " + tableName).list();
           this.message = "Records retrieved Successfully!";
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return objects;
    }
    /* Method to UPDATE salary for an employee */

    public void updateObject(Object object) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Update Object
            session.update(object);
             this.message = "Info updated Successfully!";
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */

    public void deleteObject(Object object) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Deleting Object
            session.delete(object);
            this.message = "Info deleted Successfully!";
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
