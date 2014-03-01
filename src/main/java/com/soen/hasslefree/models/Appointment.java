/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Physician relatedPhysician;

    @OneToOne(cascade = CascadeType.ALL)
    private Patient relatedPatient;

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

    public Patient getRelatedPatient() {
        return relatedPatient;
    }

    public void setRelatedPatient(Patient relatedPatient) {
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
}
