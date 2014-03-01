/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped

@Entity
@Table
public class AppointmentType implements Serializable {

    @Id
    @GeneratedValue
    private long AppointmentTypeId;

    @Column
    private String typeName;

    @Column
    private int duration;

    @Column
    private float cost;

    @OneToMany(mappedBy = "appointmentType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> associatedPatients = new HashSet<Appointment>(0);

    public AppointmentType() {
    }

    public long getAppointmentTypeId() {
        return AppointmentTypeId;
    }

    public void setAppointmentTypeId(long AppointmentTypeId) {
        this.AppointmentTypeId = AppointmentTypeId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Appointment> getAssociatedPatients() {
        return associatedPatients;
    }

    public void setAssociatedPatients(Set<Appointment> associatedPatients) {
        this.associatedPatients = associatedPatients;
    }

    
    public void saveAppointmentType() {
        ObjectDao appointmentTypeDao = new ObjectDao();
        appointmentTypeDao.addObject(this);
    }

    public void updateAppointmentType() {
        ObjectDao appointmentTypeDao = new ObjectDao();
        appointmentTypeDao.updateObject(this);
    }

    public void deleteAppointmentType() {
        ObjectDao appointmentTypeDao = new ObjectDao();
        appointmentTypeDao.deleteObject(this);
    }

    public static ArrayList<AppointmentType> getAllAppointmentTypes() {
        ArrayList<AppointmentType> appointmentTypes;
        ObjectDao appointmentTypeDao = new ObjectDao();
        appointmentTypes = appointmentTypeDao.getAllObjects("AppointmentType");
        return appointmentTypes;
    }
}
