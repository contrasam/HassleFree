/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@SessionScoped
public class AppointmentEventListener {

    private HashMap<String, Long> physicanListHolder;
    private List<String> timeSlotHolder;

    /**
     * Creates a new instance of AppointmentEventListener
     */
    public AppointmentEventListener() {
    }

    public HashMap<String, Long> getPhysicanListHolder() {
        return physicanListHolder;
    }

    public void setPhysicanListHolder(HashMap<String, Long> physicanListHolder) {
        this.physicanListHolder = physicanListHolder;
    }

    public List<String> getTimeSlotHolder() {
        return timeSlotHolder;
    }

    public void setTimeSlotHolder(List<String> timeSlotHolder) {
        this.timeSlotHolder = timeSlotHolder;
    }

    public void fetchPhysicianListFromDate(ValueChangeEvent e) {
        AppointmentBean appointment = (AppointmentBean) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("AppointmentBean");
        appointment.setDateHolder((Date) e.getNewValue());

        //TODO: Generate and set the physician list in appointment bean based on recived date (DAO Method)
        // Updating Physician list values (placing it in physicanListHolder before updation)
        physicanListHolder.put("No Preference", Long.parseLong("0"));
        appointment.setPhysicanList(physicanListHolder);
    }

    public void fetchTimeSlotListForPhysician(ValueChangeEvent e) {
        AppointmentBean appointment = (AppointmentBean) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("AppointmentBean");
        appointment.setRelatedPhysician((Long) e.getNewValue());

        //TODO: Genrerate and set the physician Time slots in the appointment bean based on the received physician ID (DAO method)
        //Updating Time slot list values (placing it in timeSlotHolder before updation)
        appointment.setAvaliableTimeList(timeSlotHolder);
    }

}
