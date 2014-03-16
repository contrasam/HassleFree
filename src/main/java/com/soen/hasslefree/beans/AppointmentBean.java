package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Appointment;
import com.soen.hasslefree.models.Appointment.AppointmentStatus;
import com.soen.hasslefree.models.AppointmentType;
import com.soen.hasslefree.models.Patient;
import com.soen.hasslefree.models.Physician;
import com.soen.hasslefree.models.PhysicianTimeSlot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class AppointmentBean implements Serializable {

    private String appointmentType;
    private Date dateHolder;
    private String timeHolder;
    private Date dateTimeOfAppointment;
    private long relatedPhysician;
    private long relatedPatient;
    private HashMap<String, Long> physicanList;
    private List<String> appointmentTypeList;
    private List<String> avaliableTimeList;

    public AppointmentBean() {
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getTimeHolder() {
        return timeHolder;
    }

    public void setTimeHolder(String timeHolder) {
        this.timeHolder = timeHolder;
    }

    public Date getDateHolder() {
        return dateHolder;
    }

    public void setDateHolder(Date dateHolder) {
        this.dateHolder = dateHolder;
    }

    public Date getDateTimeOfAppointment() {
        return dateTimeOfAppointment;
    }

    public long getRelatedPhysician() {
        return relatedPhysician;
    }

    public void setRelatedPhysician(long relatedPhysician) {
        this.relatedPhysician = relatedPhysician;
    }

    public long getRelatedPatient() {
        return relatedPatient;
    }

    public void setRelatedPatient(long relatedPatient) {
        this.relatedPatient = relatedPatient;
    }

    public void setDateTimeOfAppointment(Date dateTimeOfAppointment) {
        this.dateTimeOfAppointment = dateTimeOfAppointment;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public HashMap<String, Long> getPhysicanList() {
        populatePhysicianList();
        return physicanList;
    }

    public void setPhysicanList(HashMap<String, Long> physicanList) {
        this.physicanList = physicanList;
    }

    public List<String> getAppointmentTypeList() {
        populateAppointmentTypeList();
        return appointmentTypeList;
    }

    public void setAppointmentTypeList(List<String> appointmentTypeList) {
        this.appointmentTypeList = appointmentTypeList;
    }

    public List<String> getAvaliableTimeList() {
        return avaliableTimeList;
    }

    public void setAvaliableTimeList(List<String> avaliableTimeList) {
        this.avaliableTimeList = avaliableTimeList;
    }

    public void populateAppointmentTypeList() {
        ArrayList<AppointmentType> appointmentTypes = AppointmentType.getAllAppointmentTypes();
        ArrayList<String> appointmentTypesList = new ArrayList<String>();
        for (AppointmentType type : appointmentTypes) {
            appointmentTypesList.add(type.getTypeName());
        }
        this.appointmentTypeList = appointmentTypesList;
    }

    public String makeAppointment() {
        //Initialization
        Appointment appointment = new Appointment();
        MutableDateTime startDateTime = new MutableDateTime(dateHolder);
        ArrayList<PhysicianTimeSlot> TimeSlotListDI = null;
        ArrayList<ArrayList<PhysicianTimeSlot>> TimeSlotListCU = null;

        // Getting email id of the user through the facesContext
        String patientEmail = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("patientEmail");
        int duration = 0;

        // Spliting the hours and the minutes from the time value string
        String[] Split = timeHolder.split(":");

        //Adding Start Time to the date using a temporary calendar object
        startDateTime.setHourOfDay(Integer.parseInt(Split[0]));
        startDateTime.setMinuteOfHour(Integer.parseInt(Split[1]));

        //Calculating End Time according to the appointment type
        ArrayList<AppointmentType> typesFromDb = AppointmentType.getAllAppointmentTypes();
        for (AppointmentType type : typesFromDb) {
            if (type.getTypeName().equals(this.appointmentType)) {
                duration = type.getDuration();
                appointment.setAppointmentType(type);
                break;
            }
        }
        MutableDateTime endDateTime = new MutableDateTime(startDateTime);
        endDateTime.addMinutes(duration);

        //Making the appointment (saving to database)
        appointment.setStartTime(startDateTime.toDateTime());
        appointment.setEndTime(endDateTime.toDateTime());
        appointment.setRelatedPhysician(Physician.getPhysicianById(relatedPhysician));
        appointment.setRelatedPatient(Patient.getPatientByEmail(patientEmail));
        appointment.setStatus(AppointmentStatus.INITIATED);
        appointment.saveAppointment();

        // Updating time slots table
        AppointmentEventListener appointmentEventListener = (AppointmentEventListener) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("appointmentEventListener");

        if (appointment.getAppointmentType().getTypeName().equals("Drop In")) {
            TimeSlotListDI = appointmentEventListener.getTimeSlotListDIBackup();
            for (PhysicianTimeSlot single : TimeSlotListDI) {
                if ((single.getStartTime().isEqual(startDateTime) || single.getStartTime().isAfter(startDateTime)) && (single.getEndTime().isEqual(endDateTime) || single.getEndTime().isBefore(endDateTime))) {
                    single.setIsAvailable(false);
                    single.updatePhysicianTimeSlot();
                }
            }
        }
        if (appointment.getAppointmentType().getTypeName().equals("Annual Check Up")) {
            TimeSlotListCU = appointmentEventListener.getTimeSlotListCUBackup();
            for (ArrayList<PhysicianTimeSlot> inner : TimeSlotListCU) {
                for (PhysicianTimeSlot single : inner) {
                    if ((single.getStartTime().isEqual(startDateTime) || single.getStartTime().isAfter(startDateTime)) && (single.getEndTime().isEqual(endDateTime) || single.getEndTime().isBefore(endDateTime))) {
                        single.setIsAvailable(false);
                        single.updatePhysicianTimeSlot();
                    }
                }
            }
        }

        return "myAppointments";
    }

    public void populatePhysicianList() {
        ArrayList<Physician> physicians = Physician.getAllPhysicians();
        HashMap<String, Long> holder = new HashMap<String, Long>();
        for (Physician physician : physicians) {
            String fullName = physician.getFirstName() + " " + physician.getLastName();
            long userId = physician.getUserId();
            holder.put(fullName, userId);
        }
        holder.put(" ", Long.parseLong("0"));
        this.physicanList = holder;
    }
}
