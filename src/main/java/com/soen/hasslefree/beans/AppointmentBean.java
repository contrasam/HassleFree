/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@RequestScoped
public class AppointmentBean implements Serializable {

    private String appointmentType;
    private Date dateTimeOfAppointment;
    private String physicanName;
    private List<String> appointmentTypeList;
    private List<String> avaliableTimeList;

    public AppointmentBean() {
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public Date getDateTimeOfAppointment() {
        return dateTimeOfAppointment;
    }

    public void setDateTimeOfAppointment(Date dateTimeOfAppointment) {
        this.dateTimeOfAppointment = dateTimeOfAppointment;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getPhysicanName() {
        return physicanName;
    }

    public void setPhysicanName(String physicanName) {
        this.physicanName = physicanName;
    }

    public List<String> getAppointmentTypeList() {
        populateAppointmentTypeList();
        return appointmentTypeList;
    }

    public void setAppointmentTypeList(List<String> appointmentTypeList) {
        this.appointmentTypeList = appointmentTypeList;
    }

    public List<String> getAvaliableTimeList() {
        populateAvaliableTimeList();
        return avaliableTimeList;
    }

    public void setAvaliableTimeList(List<String> avaliableTimeList) {
        this.avaliableTimeList = avaliableTimeList;
    }

    public void populateAppointmentTypeList() {
        List<String> appointment = new ArrayList();
        appointment.add("Check Up");
        appointment.add("Drop In");
        this.appointmentTypeList = appointment;
    }

    public void populateAvaliableTimeList() {
        List<String> timeList = new ArrayList();
        timeList.add("10:00");
        timeList.add("10:30");
        timeList.add("11:00");
        this.avaliableTimeList = timeList;
    }
}
