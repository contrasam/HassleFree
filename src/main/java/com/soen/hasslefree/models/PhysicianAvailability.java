/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

/**
 *
 * @author Khalid
 */
@Entity
@Table
public class PhysicianAvailability implements Serializable{

    @Id
    @GeneratedValue
    private long physicianAvailabilityID;

    @ManyToOne(cascade = CascadeType.ALL)
    private Physician relatedPhysician;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime startTime;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime endTime;

    @OneToMany(mappedBy = "physicianAvailability", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PhysicianTimeSlot> physicianTimeSlots = new HashSet<PhysicianTimeSlot>(0);

    public long getPhysicianAvailabilityID() {
        return physicianAvailabilityID;
    }

    public void setPhysicianAvailabilityID(long physicianAvailabilityID) {
        this.physicianAvailabilityID = physicianAvailabilityID;
    }

    public Physician getRelatedPhysician() {
        return relatedPhysician;
    }

    public void setRelatedPhysician(Physician relatedPhysician) {
        this.relatedPhysician = relatedPhysician;
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

    public Set<PhysicianTimeSlot> getPhysicianTimeSlots() {
        return physicianTimeSlots;
    }

    public void setPhysicianTimeSlots(Set<PhysicianTimeSlot> physicianTimeSlots) {
        this.physicianTimeSlots = physicianTimeSlots;
    }

    public void savePhysicianAvailability() {
        ObjectDao physicianAvailabilityDao = new ObjectDao();
        generateTimeSlots();
        physicianAvailabilityDao.addOrUpdateObject(this);
    }

    public void updatePhysicianAvailability() {
        ObjectDao physicianAvailabilityDao = new ObjectDao();
        physicianAvailabilityDao.updateObject(this);
    }

    public void deletePhysicianAvailability() {
        ObjectDao physicianAvailabilityDao = new ObjectDao();
        physicianAvailabilityDao.deleteObject(this);
    }

    public static PhysicianAvailability getPhysicianAvailabilityById(long id) {
        PhysicianAvailability physicianAvailabilityHolder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            physicianAvailabilityHolder = (PhysicianAvailability) session.get(PhysicianAvailability.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return physicianAvailabilityHolder;
    }

    public static ArrayList<PhysicianAvailability> getAllPhysicianAvailabilities() {
        ArrayList<PhysicianAvailability> physicianAvailabilities;
        ObjectDao physicianAvailabilityDao = new ObjectDao();
        physicianAvailabilities = physicianAvailabilityDao.getAllObjects("PhysicianAvailability");
        return physicianAvailabilities;
    }

    public void generateTimeSlots() {
        MutableDateTime slotStatTime = new MutableDateTime();
        MutableDateTime slotEndTime = new MutableDateTime();

        long availabilityStartTime = this.startTime.getMillis();
        long availabilityEndTime = this.endTime.getMillis();

        long availableDuration = availabilityEndTime-availabilityStartTime  ;
        long slotDuration = 20 * 60 * 1000; // 20 min * 60 sec * 1000 millisecond

        if (availableDuration > 0) {

            long currentSlotStartTime = availabilityStartTime;
            boolean stopSlicing = false;
            while (!stopSlicing) {
                //<editor-fold defaultstate="collapsed" desc="new PhysicianTimeSlot ">
                PhysicianTimeSlot newTimeSlot = new PhysicianTimeSlot();
                slotStatTime.setMillis(currentSlotStartTime);
                slotEndTime.setMillis(currentSlotStartTime + slotDuration);

                newTimeSlot.setStartTime(slotStatTime.toDateTime());
                newTimeSlot.setEndTime(slotEndTime.toDateTime());
                newTimeSlot.setIsAvailable(true);
                newTimeSlot.setPhysicianAvailability(this);
                newTimeSlot.setRelatedPhysician(this.relatedPhysician);

          //</editor-fold>
                
                this.physicianTimeSlots.add(newTimeSlot);
                availableDuration = availableDuration - slotDuration;
                currentSlotStartTime = currentSlotStartTime + slotDuration;
                if (availableDuration < slotDuration) { // I removed = because I want to add the last slot to the time slots.
                    stopSlicing = true;
                }
            }

        }
    }
}
