/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.AppointmentTypeDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped
public class AppointmentType {

    private long appointmentTypeId;
    private String typeName;
    private int duration;
    private float cost;
    private List<String> appointmentTypeList;

    public List<String> getAppointmentTypeList() {
        AppointmentTypeDao dao = new AppointmentTypeDao();
        this.appointmentTypeList = dao.getAllAppointmentTypes();  
        return appointmentTypeList;
    }
    
    public long getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public void setAppointmentTypeId(long appointmentTypeId) {
        this.appointmentTypeId = appointmentTypeId;
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
    public void saveAppointmentType() {
        try {
            AppointmentTypeDao appointmentType = new AppointmentTypeDao();
            appointmentType.addAppointmentType(this);
            System.out.println("Address saved to database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
