/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped

@Entity
@Table
public class Appointment implements Serializable {

    @Id
    @GeneratedValue
    private long appointmentID;

    private enum AppointmentStatus {

        INITIATED, CONFIRMED, COMPLETED
    };

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    private Physician relatedPhysician;

    @ManyToOne
    private User relatedPatient;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime startTime;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private AppointmentType appointmentType;

    public Appointment() {
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Physician getRelatedPhysician() {
        return relatedPhysician;
    }

    public void setRelatedPhysician(Physician relatedPhysician) {
        this.relatedPhysician = relatedPhysician;
    }

    public User getRelatedPatient() {
        return relatedPatient;
    }

    public void setRelatedPatient(User relatedPatient) {
        this.relatedPatient = relatedPatient;
    }

    public void saveAppointment() {
        ObjectDao appointmentDao = new ObjectDao();
        appointmentDao.addObject(this);
    }

    public void updateAppointment() {
        ObjectDao appointmentDao = new ObjectDao();
        appointmentDao.updateObject(this);
    }

    public void deleteAppointment() {
        ObjectDao appointmentDao = new ObjectDao();
        appointmentDao.deleteObject(this);
    }

    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointments;
        ObjectDao AppointmentDao = new ObjectDao();
        appointments = AppointmentDao.getAllObjects("Appointment");
        return appointments;
    }

    public void getAvailableDropInForAnyPhysician() {
    }

    public ArrayList<PhysicianTimeSlot> getAvailableDropInByPhysicianID(long physicianId, DateTime startDate, DateTime endDate) {

        Session session = null;
         ArrayList<PhysicianTimeSlot> listOfDropInForPhysician= new ArrayList<PhysicianTimeSlot>();
         
        try {
            session = HibernateUtil.getSessionFactory().openSession();
           Query query = session.createQuery("from PhysicianTimeSlot where (startTime between  :startDate and :endDate) and (relatedPhysician_userId = :physicianId) and isAvailable = :isAvailable order by startTime " );
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            query.setParameter("physicianId", physicianId);
             query.setParameter("isAvailable", true);
            
            listOfDropInForPhysician = (ArrayList)query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
       return listOfDropInForPhysician;      
    }

    
    
    public void getAvailableCheckInByPhysicianID() {
    }

}
