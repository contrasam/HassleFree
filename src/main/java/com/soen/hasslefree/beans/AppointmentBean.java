package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Appointment;
import com.soen.hasslefree.models.AppointmentType;
import com.soen.hasslefree.models.Patient;
import com.soen.hasslefree.models.Physician;
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
        populateAvaliableTimeList();
        return avaliableTimeList;
    }

    public void setAvaliableTimeList(List<String> avaliableTimeList) {
        this.avaliableTimeList = avaliableTimeList;
    }

    public void populateAppointmentTypeList() {
        List<String> appointment = new ArrayList();
        appointment.add("Annual Check Up");
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

    public void populatePhysicianList() {
        ArrayList<Physician> physicians = Physician.getAllPhysicians();
        HashMap<String, Long> holder = new HashMap<String, Long>();
        for (Physician physician : physicians) {
            String fullName = physician.getFirstName() + " " + physician.getLastName();
            long userId = physician.getUserId();
            holder.put(fullName, userId);
        }
        this.physicanList = holder;
    }

    public void makeAppointment() {
        //Initialization
        Appointment appointment = new Appointment();
        MutableDateTime startDateTime = new MutableDateTime(dateHolder);
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
        appointment.saveAppointment();
    }
}
