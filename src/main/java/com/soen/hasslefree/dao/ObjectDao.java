/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.dao;

import com.soen.hasslefree.models.Physician;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javassist.bytecode.stackmap.TypeData.ClassName;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Khalid
 */
public class ObjectDao<T> {

    private static SessionFactory factory;
    private T t;
     public T get(){
        return this.t;
    }
     
    public void set(T t1){
        this.t=t1;
    }

    public ObjectDao() {
        makeSessionFactory();
    }

    public static void makeSessionFactory() {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Long addObject(Object object) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long physicianID = null;
        try {
            tx = session.beginTransaction();
            // Adding Object
            physicianID = (Long) session.save(object);
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
            session.merge(object);
         
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

    public  void updateObject(Object object,long id,Class<T> ClassName) throws IllegalAccessException, InvocationTargetException {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Update Object
            
             this.t = (T) session.get(ClassName, id);
            BeanUtils.copyProperties(t, object);
            session.update(t);
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

    public void deleteObject(Object object,long id,Class<T> ClassName) throws IllegalAccessException, InvocationTargetException {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Deleting Object
            this.t = (T) session.load(ClassName, id);
            session.delete(t);
            session.flush();
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
