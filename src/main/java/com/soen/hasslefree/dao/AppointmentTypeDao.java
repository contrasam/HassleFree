/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.dao;

import com.soen.hasslefree.models.AppointmentType;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PradeepSamuel
 */
public class AppointmentTypeDao {

    public List<String> getAllAppointmentTypes() {
        List<String> appointmentTypes = new ArrayList<String>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            appointmentTypes = session.createQuery("SELECT typeName FROM AppointmentType").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return appointmentTypes;
    }
    
    public void addAppointmentType(AppointmentType appointmentType) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(appointmentType);
            transaction.commit();
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
}
