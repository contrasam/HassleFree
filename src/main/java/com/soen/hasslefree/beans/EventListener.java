/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.PhysicianTimeSlot;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author PradeepSamuel
 */
public class EventListener implements ValueChangeListener {

    private long relatedPhysician;
    private Date dateOfAppointment;
    private ArrayList<String> avaliableTimeList;

    /**
     * Creates a new instance of EventListener
     */
    public EventListener() {
    }

    public long getRelatedPhysician() {
        return relatedPhysician;
    }

    public void setRelatedPhysician(long relatedPhysician) {
        this.relatedPhysician = relatedPhysician;
    }

    public ArrayList<String> getAvaliableTimeList() {
        return avaliableTimeList;
    }

    public void setAvaliableTimeList(ArrayList<String> avaliableTimeList) {
        this.avaliableTimeList = avaliableTimeList;
    }

    public void refreshAppointmentSlots(ValueChangeEvent e) {
        this.dateOfAppointment = (Date) e.getNewValue();
        ArrayList<PhysicianTimeSlot> availabilities = PhysicianTimeSlot.getAllPhysicianTimeSlots();
        ArrayList<String> tempHolder = new ArrayList<String>();
        for (PhysicianTimeSlot availability : availabilities) {
            String hour = Integer.toString(availability.getStartTime().getHourOfDay());
            String minutes = Integer.toString(availability.getStartTime().getMinuteOfHour());
            if (minutes.equals("0")) {
                minutes = "00";
            } else if (hour.length() <= 1) {
                hour = "0" + hour;
            }
            if (availability.getPhysicianAvailability().getRelatedPhysician().getUserId() == relatedPhysician) {
                tempHolder.add(hour + ":" + minutes);
            }
        }
        this.avaliableTimeList = tempHolder;
    }

    @Override
    public void processValueChange(ValueChangeEvent vce) throws AbortProcessingException {
        AppointmentBean appointment = (AppointmentBean) FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().get("country");
        
    }

}
