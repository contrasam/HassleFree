/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped

@Entity
@Table
public class Appointment implements Serializable {

    @Id
    @GeneratedValue
    private long appointmentID;

    public static enum AppointmentStatus {

        INITIATED, CONFIRMED, COMPLETED
    };

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime startTime;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppointmentType appointmentType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Physician relatedPhysician;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient relatedPatient;

    public Patient getRelatedPatient() {
        return relatedPatient;
    }

    public void setRelatedPatient(Patient relatedPatient) {
        this.relatedPatient = relatedPatient;
    }

    
    
    public Appointment() {
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Physician getRelatedPhysician() {
        return relatedPhysician;
    }

    public void setRelatedPhysician(Physician relatedPhysician) {
        this.relatedPhysician = relatedPhysician;
    }


    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void saveAppointment() {
        ObjectDao appointmentDao = new ObjectDao();
        appointmentDao.addObject(this);
    }

    public void updateAppointment() {
        ObjectDao appointmentDao = new ObjectDao();
        appointmentDao.addOrUpdateObject(this);
    }

    public void deleteAppointment() throws IllegalAccessException, InvocationTargetException {
        ObjectDao appointmentDao = new ObjectDao();
        appointmentDao.deleteObject(this,this.appointmentID,Appointment.class);
    }

    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointments;
        ObjectDao AppointmentDao = new ObjectDao();
        appointments = AppointmentDao.getAllObjects("Appointment");
        return appointments;
    }

// getting the Available DropIn For Any Physician
    public static ArrayList<PhysicianTimeSlot> getAvailableDropInForAnyPhysician(DateTime startDate, DateTime endDate) {
        Session session = null;
        ArrayList<PhysicianTimeSlot> listOfDropInForAnyPhysician = new ArrayList<PhysicianTimeSlot>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from PhysicianTimeSlot where (startTime between  :startDate and :endDate) and isAvailable = :isAvailable order by startTime ");
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            query.setParameter("isAvailable", true);

            listOfDropInForAnyPhysician = (ArrayList) query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        ArrayList<PhysicianTimeSlot> filteredDropInList = mentainConcurrentTimeSlot(listOfDropInForAnyPhysician);

        return filteredDropInList;
    }

    // getting the Available DropIn For spicific Physician
    public static ArrayList<PhysicianTimeSlot> getAvailableDropInByPhysicianID(long physicianId, DateTime startDate, DateTime endDate) {

        Session session = null;
        ArrayList<PhysicianTimeSlot> listOfDropInForPhysician = new ArrayList<PhysicianTimeSlot>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from PhysicianTimeSlot where (startTime between  :startDate and :endDate) and (relatedPhysician_userId = :physicianId) and isAvailable = :isAvailable order by startTime ");
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            query.setParameter("physicianId", physicianId);
            query.setParameter("isAvailable", true);

            listOfDropInForPhysician = (ArrayList) query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listOfDropInForPhysician;
    }

    // getting the Available Check In For spicific Physician
    public static ArrayList<ArrayList<PhysicianTimeSlot>> getallAvailableCheckUpsByPhysicianID(long physicianId, DateTime startDate, DateTime endDate) {

        ArrayList<ArrayList<PhysicianTimeSlot>> allAvailableCheckUpList = new ArrayList<ArrayList<PhysicianTimeSlot>>();

        ArrayList<PhysicianTimeSlot> inputDropInListForPhysician;

        inputDropInListForPhysician = Appointment.getAvailableDropInByPhysicianID(physicianId, startDate, endDate);

//         for (PhysicianTimeSlot slot : inputDropInListForPhysician) {
//            Physician physician = slot.getRelatedPhysician();
//            System.out.println("Slot Id:" + slot.getPhysicianTimeSlotID() + "        Stat Time:" + slot.getStartTime() + " End Time:" + slot.getEndTime()+ "          Physician Name:" + physician.getFirstName()+" "+ physician.getLastName() );
//        }
        for (int i = 0; i < inputDropInListForPhysician.size() - 2; i++) {

            PhysicianTimeSlot firstTimeSlot = inputDropInListForPhysician.get(i);
            PhysicianTimeSlot secondTimeSlot = inputDropInListForPhysician.get(i + 1);
            PhysicianTimeSlot thirdTimeSlot = inputDropInListForPhysician.get(i + 2);

            boolean firstAndSecondLinked = firstTimeSlot.getEndTime().isEqual(secondTimeSlot.getStartTime());
            boolean secondAndThirdLinked = secondTimeSlot.getEndTime().isEqual(thirdTimeSlot.getStartTime());

            if (firstAndSecondLinked && secondAndThirdLinked) {
                ArrayList<PhysicianTimeSlot> singleCheckUp = new ArrayList<PhysicianTimeSlot>();
                singleCheckUp.add(firstTimeSlot);
                singleCheckUp.add(secondTimeSlot);
                singleCheckUp.add(thirdTimeSlot);
                allAvailableCheckUpList.add(singleCheckUp);
            }
        }
        return allAvailableCheckUpList;
    }

    private static ArrayList<PhysicianTimeSlot> mentainConcurrentTimeSlot(ArrayList<PhysicianTimeSlot> listOfDropInForAnyPhysician) {

        ArrayList<PhysicianTimeSlot> filteredDropInList = new ArrayList<PhysicianTimeSlot>();
        Set<DateTime> startTimeSet = new HashSet<DateTime>();

        for (PhysicianTimeSlot slot : listOfDropInForAnyPhysician) {
            if (startTimeSet.add(slot.getStartTime())) {
                filteredDropInList.add(slot);
            }
        }

        return filteredDropInList;
    }

    public static ArrayList<Appointment> getAppointmentsByUserId(long id) {
        ArrayList<Appointment> allAppointments = null;
        ArrayList<Appointment> filteredAppointments = new ArrayList();
        ObjectDao appointmentDao = new ObjectDao();
        allAppointments = appointmentDao.getAllObjects("Appointment");
        for (Appointment appointment : allAppointments) {
            if (appointment.getRelatedPatient().getUserId() == id) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }

    public static Appointment getAppointmentById(long id) {
        Appointment appointmentHolder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            appointmentHolder = (Appointment) session.get(Appointment.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return appointmentHolder;
    }
}
