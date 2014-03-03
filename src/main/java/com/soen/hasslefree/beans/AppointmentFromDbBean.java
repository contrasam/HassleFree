/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Appointment;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@RequestScoped
public class AppointmentFromDbBean {

    private ArrayList<Appointment> userAppointments;

    /**
     * Creates a new instance of AppointmentFromDbBean
     */
    public AppointmentFromDbBean() {
    }

    public ArrayList<Appointment> getUserAppointments() {
        populateUserAppointments();
        return userAppointments;
    }

    public void setUserAppointments(ArrayList<Appointment> userAppointments) {
        this.userAppointments = userAppointments;
    }

    public void populateUserAppointments() {
        ArrayList<Appointment> tempHolder = Appointment.getAppointmentsByUserId(5);
        userAppointments = tempHolder;
    }

    public String deleteAppointment() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long id = Long.parseLong(params.get("appID"));
        Appointment tempAppointment = Appointment.getAppointmentById(id);
        tempAppointment.deleteAppointment();
        return "myAppointments";
    }

}
