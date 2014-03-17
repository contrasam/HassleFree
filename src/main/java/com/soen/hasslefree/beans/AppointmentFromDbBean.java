/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Appointment;
import com.soen.hasslefree.models.Patient;
import com.soen.hasslefree.models.User;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String loginId = (String) session.getAttribute("loginId");
        User patient = Patient.getPatientByEmail(loginId);
        ArrayList<Appointment> tempHolder = Appointment.getAppointmentsByUserId(patient.getUserId());
        userAppointments = tempHolder;
    }

    public String deleteAppointment() throws IllegalAccessException, InvocationTargetException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long id = Long.parseLong(params.get("appID"));
        Appointment tempAppointment = Appointment.getAppointmentById(id);
        tempAppointment.deleteAppointment();
        return "myAppointments";
    }

}
