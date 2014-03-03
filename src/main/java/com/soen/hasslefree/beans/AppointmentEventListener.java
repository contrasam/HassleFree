/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Appointment;
import com.soen.hasslefree.models.ClinicHours;
import com.soen.hasslefree.models.PhysicianTimeSlot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.joda.time.MutableDateTime;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@SessionScoped
public class AppointmentEventListener implements Serializable {

    private List<String> timeSlots;
    private Date dateHolder;
    private String appointmentType;
    private ArrayList<PhysicianTimeSlot> timeSlotListDIBackup;
    private ArrayList<ArrayList<PhysicianTimeSlot>> timeSlotListCUBackup;

    public ArrayList<PhysicianTimeSlot> getTimeSlotListDIBackup() {
        return timeSlotListDIBackup;
    }

    public void setTimeSlotListDIBackup(ArrayList<PhysicianTimeSlot> timeSlotListDIBackup) {
        this.timeSlotListDIBackup = timeSlotListDIBackup;
    }

    public ArrayList<ArrayList<PhysicianTimeSlot>> getTimeSlotListCUBackup() {
        return timeSlotListCUBackup;
    }

    public void setTimeSlotListCUBackup(ArrayList<ArrayList<PhysicianTimeSlot>> timeSlotListCUBackup) {
        this.timeSlotListCUBackup = timeSlotListCUBackup;
    }

    public List<String> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public Date getDateHolder() {
        return dateHolder;
    }

    public void setDateHolder(Date dateHolder) {
        this.dateHolder = dateHolder;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public AppointmentEventListener() {
    }

    public void recordAppointmentType(ValueChangeEvent e) {
        String appType = e.getNewValue().toString();
        appointmentType = appType;
    }

    public void fetchPhysicianListFromDate(ValueChangeEvent e) {
        AppointmentBean appointment = (AppointmentBean) FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().get("appointmentBean");
        Date ReceivedDate = (Date) e.getNewValue();
        dateHolder = ReceivedDate;
        HashMap<String, Long> physicanListHolder = new HashMap<String, Long>();
        appointment.setDateHolder(ReceivedDate);

        //TODO: Generate and set the physician list in appointment bean based on recived date (DAO Method)
        // Updating Physician list values (placing it in physicanListHolder before updation)
        physicanListHolder.put("No Preference", Long.parseLong("0"));
        appointment.setPhysicanList(physicanListHolder);
    }

    public void fetchTimeSlotListForPhysician(ValueChangeEvent e) {
        AppointmentBean appointment = (AppointmentBean) FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().get("appointmentBean");
        long ChangedPhysicianId = (Long) e.getNewValue();
        MutableDateTime dateEntered = new MutableDateTime(dateHolder);

        List<String> timeSlotHolder = new ArrayList<String>();
        appointment.setRelatedPhysician(ChangedPhysicianId);
        ArrayList<PhysicianTimeSlot> timeSlotListDI = null;
        ArrayList<ArrayList<PhysicianTimeSlot>> timeSlotListCU = null;
        //Getting Clinic Starting and ending times
        ArrayList<ClinicHours> clinicHours = ClinicHours.getAllClinicHours();
        //Check forselected appointment type
//        if ((dateEntered.isEqual(clinicHours.get(0).getStartTime()) || dateEntered.isAfter(clinicHours.get(0).getStartTime())) && dateEntered.isBefore(clinicHours.get(0).getEndTime())) {
            if (appointmentType.equals("Drop In")) {
                timeSlotListDI = Appointment.getAvailableDropInByPhysicianID(ChangedPhysicianId, clinicHours.get(0).getStartTime(), clinicHours.get(0).getEndTime());
                // Persisting values to access from request scoped (Appointment Bean)
                timeSlotListDIBackup = timeSlotListDI;
                for (PhysicianTimeSlot timeSlot : timeSlotListDI) {
                    String hourDI = Integer.toString(timeSlot.getStartTime().getHourOfDay());
                    String minutesDI = Integer.toString(timeSlot.getStartTime().getMinuteOfHour());

                    if (minutesDI.equals("0")) {
                        minutesDI = "00";
                    }
                    if (hourDI.length() == 1) {
                        hourDI = "0" + hourDI;
                    }
                    timeSlotHolder.add(hourDI + ":" + minutesDI);
                }
            }
            if (appointmentType.equals("Annual Check Up")) {
                timeSlotListCU = Appointment.getallAvailableCheckUpsByPhysicianID(ChangedPhysicianId, clinicHours.get(0).getStartTime(), clinicHours.get(0).getEndTime());
                // Persisting values to access from request scoped (Appointment Bean)
                timeSlotListCUBackup = timeSlotListCU;
                for (int i = 0; i < timeSlotListCU.size(); i++) {
                    String hourCU = Integer.toString(timeSlotListCU.get(i).get(0).getStartTime().getHourOfDay());
                    String minutesCU = Integer.toString(timeSlotListCU.get(i).get(0).getStartTime().getMinuteOfHour());

                    if (minutesCU.equals("0")) {
                        minutesCU = "00";
                    }
                    if (hourCU.length() == 1) {
                        hourCU = "0" + hourCU;
                    }
                    timeSlotHolder.add(hourCU + ":" + minutesCU);
                    i += 2;
                }
            }

            timeSlots = timeSlotHolder;
//        }
    }

}
