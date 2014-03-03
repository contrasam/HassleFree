/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Clinic;
import com.soen.hasslefree.models.Physician;
import com.soen.hasslefree.models.PhysicianAvailability;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.joda.time.MutableDateTime;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@RequestScoped
public class PhysicianAvailabilityBean implements Serializable {

    private Date dateHolder;
    private String startTimeHolder;
    private String endTimeHolder;
    private long clinicId;
    private HashMap<String, Long> clinicList;

    /**
     * Creates a new instance of PhysicianAvailability
     */
    public PhysicianAvailabilityBean() {
    }

    public Date getDateHolder() {
        return dateHolder;
    }

    public void setDateHolder(Date dateHolder) {
        this.dateHolder = dateHolder;
    }

    public String getStartTimeHolder() {
        return startTimeHolder;
    }

    public void setStartTimeHolder(String startTimeHolder) {
        this.startTimeHolder = startTimeHolder;
    }

    public String getEndTimeHolder() {
        return endTimeHolder;
    }

    public void setEndTimeHolder(String endTimeHolder) {
        this.endTimeHolder = endTimeHolder;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    public HashMap<String, Long> getClinicList() {
        populateClinicList();
        return clinicList;
    }

    public void setClinicList(HashMap<String, Long> clinicList) {
        this.clinicList = clinicList;
    }

    public String addAvailability() {
        String[] startSplit = startTimeHolder.split(":");
        String[] endSplit = endTimeHolder.split(":");
        MutableDateTime startDateTime = new MutableDateTime(dateHolder);
        MutableDateTime endDateTime = new MutableDateTime(dateHolder);
        String patientEmail = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("patientEmail");

        // Setting start time value from string input
        startDateTime.setHourOfDay(Integer.parseInt(startSplit[0]));
        startDateTime.setMinuteOfHour(Integer.parseInt(startSplit[1]));

        // Setting end time value from string input
        endDateTime.setHourOfDay(Integer.parseInt(endSplit[0]));
        endDateTime.setMinuteOfHour(Integer.parseInt(endSplit[1]));

        PhysicianAvailability pa = new PhysicianAvailability();
        pa.setStartTime(startDateTime.toDateTime());
        pa.setEndTime(endDateTime.toDateTime());
        pa.setRelatedPhysician(Physician.getPhysicianByEmail(patientEmail));
        pa.savePhysicianAvailability();
        
        return "myAvailabilities";
    }

    public void populateClinicList() {
        ArrayList<Clinic> clinics = Clinic.getAllClinics();
        HashMap<String, Long> holder = new HashMap<String, Long>();
        for (Clinic clinic : clinics) {
            String clinicName = clinic.getName();
            long Id = clinic.getClinicId();
            holder.put(clinicName, Id);
        }
        this.clinicList = holder;
    }

}
