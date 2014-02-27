/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soen.hasslefree.dao;

import com.soen.hasslefree.models.Nurse;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Khalid
 */

public class NurseDao {

    public void addNurse(Nurse nurse) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(nurse);
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

    public void deleteUser(long nurseId) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Nurse nurse = (Nurse) session.load(Nurse.class, new Long(nurseId));
            session.delete(nurse);
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

    public void updateUser(Nurse nurse) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(nurse);
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

    public Set<Nurse> getAllUsers() {
        Set<Nurse> nurses = new HashSet<Nurse>();;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            nurses = (Set)session.createQuery("select concat(firstName, ' ', lastName) as name from nurse").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return nurses;
    }

    public Set<Nurse> getUserById(String nurseId) {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String queryString = "from nurse where concat(first_name, ' ', last_name) = :id";
            Query query = session.createQuery(queryString);
            query.setString("id", nurseId);
            //cust = (Customer) query.uniqueResult();
            Set<Nurse> nurseSet = (Set) query.list();
            if (nurseSet.size() > 0) {
                return nurseSet;
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


