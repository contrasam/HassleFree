/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Clinic;
import com.soen.hasslefree.models.ClinicHours;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.joda.time.DateTime;

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
    private DateTime clinicStartTime;
    private DateTime clinicEndTime;
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

    public DateTime getClinicStartTime() {
        return clinicStartTime;
    }

    public void setClinicStartTime(DateTime clinicStartTime) {
        this.clinicStartTime = clinicStartTime;
    }

    public DateTime getClinicEndTime() {
        return clinicEndTime;
    }

    public void setClinicEndTime(DateTime clinicEndTime) {
        this.clinicEndTime = clinicEndTime;
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

    public void populateWorkingHours(AjaxBehaviorEvent event) {
        ClinicHours clinicHours = ClinicHours.getClinicHoursByClinicId(clinicId);
        clinicStartTime = clinicHours.getStartTime();
        clinicEndTime = clinicHours.getEndTime();
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
