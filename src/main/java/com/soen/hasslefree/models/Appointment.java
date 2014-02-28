/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

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
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startTime;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endTime;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private AppointmentType appointmentType;
    

    public Appointment() {
    }

    public Appointment(int year, int month, int day, int startHour, int startMinutes, String appointmentType) {
//        this.appointmentType = new AppointmentType();
//        this.appointmentType.setTypeName(appointmentType);
//        this.appointmentType.setDuration(20);
//        this.appointmentSlot = new TimeSlot(year, month, day, startHour, startMinutes);
//        GregorianCalendar tempCalendar = (GregorianCalendar) appointmentSlot.getStart().clone();
//        tempCalendar.add(Calendar.MINUTE, this.appointmentType.getDuration());
//        this.appointmentSlot.setEnd(tempCalendar);
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

   

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
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
}
