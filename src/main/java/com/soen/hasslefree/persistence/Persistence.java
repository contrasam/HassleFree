/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.persistence;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Khalid
 */
public class Persistence {

    public void persistence(Object object) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.save(object);

    }

    public void beginSession() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public void commitSession() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().commit();
    }

    public List getAllList(String fromString) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery(fromString).list();
        session.getTransaction().commit();
        return result;

    }
}
