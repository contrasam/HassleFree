/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.dao;

import com.soen.hasslefree.models.Address;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PradeepSamuel
 */
public class AddressDao {

   
    
    public void addAddress(Address address) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(address);
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

    public void deleteAddress(long addressId) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Address address = (Address) session.load(Address.class, new Long(addressId));
            session.delete(address);
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

    public void updateAddress(Address address) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(address);
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

    public Set<Address> getAllAddress() {
        Set<Address> addresses = new HashSet<Address>();;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            addresses = (Set)session.createQuery("select concat(streetNumber, ' ', streetName, ' ', city) as fulladdress from address").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return addresses;
    }

    public Set<Address> getAddressById(long userId) {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String queryString = "from address where addressId  = :id";
            Query query = session.createQuery(queryString);
            query.setLong("id", userId);
            //cust = (Customer) query.uniqueResult();
            Set<Address> userSet = (Set) query.list();
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
