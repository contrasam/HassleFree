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
public class PhysicianAvailability implements Serializable {

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

    public boolean savePhysicianAvailability() {
        boolean hasCommted = false;
        AppointmentType dropIn = AppointmentType.searchForAppointmentType("drop");
        if (dropIn != null) {
            hasCommted = generateTimeSlots(this.startTime, this.endTime, dropIn.getDuration());
        }
        return hasCommted;
    }

    public void updatePhysicianAvailability() throws IllegalAccessException, InvocationTargetException {
        ObjectDao physicianAvailabilityDao = new ObjectDao();
        physicianAvailabilityDao.updateObject(this,this.getPhysicianAvailabilityID(),PhysicianAvailability.class);
    }

    public void deletePhysicianAvailability() throws IllegalAccessException, InvocationTargetException {
        ObjectDao physicianAvailabilityDao = new ObjectDao();
        physicianAvailabilityDao.deleteObject(this,this.getPhysicianAvailabilityID(),PhysicianAvailability.class);
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

    public boolean generateTimeSlots(DateTime availabilityStartTime, DateTime availabilityEndTime, int dropInDurationInMinutes) {
        MutableDateTime slotStatTime = new MutableDateTime();
        MutableDateTime slotEndTime = new MutableDateTime();

        long availabilityStartTimeInMillis = availabilityStartTime.getMillis();
        long availabilityEndTimeInMillis = availabilityEndTime.getMillis();

        long availableDuration = availabilityEndTimeInMillis - availabilityStartTimeInMillis;
        long slotDuration = dropInDurationInMinutes * 60 * 1000; // 20 min * 60 sec * 1000 millisecond
        ArrayList<RoomTimeSlot> roomSlots = RoomTimeSlot.getFilteredAvailableRoomSlotsForDate(startTime, endTime);

        if (availableDuration > 0) {

            long currentSlotStartTime = availabilityStartTimeInMillis;
            boolean stopSlicing = false;

            while (!stopSlicing) {
                //<editor-fold defaultstate="collapsed" desc="new PhysicianTimeSlot ">
                int roomSlotIndex = hasFoundFreeRoomSlot(currentSlotStartTime, roomSlots);
                if (roomSlotIndex < 0) {
                    return false;
                } else {
                    PhysicianTimeSlot newTimeSlot = new PhysicianTimeSlot();
                    slotStatTime.setMillis(currentSlotStartTime);
                    slotEndTime.setMillis(currentSlotStartTime + slotDuration);

                    newTimeSlot.setStartTime(slotStatTime.toDateTime());
                    newTimeSlot.setEndTime(slotEndTime.toDateTime());
                    newTimeSlot.setIsAvailable(true);
                    newTimeSlot.setPhysicianAvailability(this);
                    newTimeSlot.setRelatedPhysician(this.relatedPhysician);
                    RoomTimeSlot roomTime = roomSlots.get(roomSlotIndex);
                    newTimeSlot.setRelatedRoomTimeSlot(roomTime);

                    roomTime.setPhysicianTimeSlot(newTimeSlot);
                    roomTime.setIsAvailable(false);
               
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

        ObjectDao physicianAvailabilityDao = new ObjectDao();
        reserveRoomSlot(roomSlots);
        physicianAvailabilityDao.addOrUpdateObject(this);
        return true;
    }

    public boolean reserveRoomSlot(ArrayList<RoomTimeSlot> roomSlots) {
        boolean hasReserved = false;
        for (RoomTimeSlot roomSlot : roomSlots) {
            roomSlot.updateRoomTimeSlot();
        }
        return hasReserved;
    }

    private int hasFoundFreeRoomSlot(long startSlotTimeInMillis, ArrayList<RoomTimeSlot> roomSlots) {
        int index = -1;
        for (int i = 0; i < roomSlots.size(); i++) {
            RoomTimeSlot roomSlot = roomSlots.get(i);
            if (roomSlot.getStartTime().getMillis() == startSlotTimeInMillis) {
                index = i;
            }
        }
        return index;
    }

}
