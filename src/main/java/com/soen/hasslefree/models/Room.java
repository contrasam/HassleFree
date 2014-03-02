/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.joda.time.MutableDateTime;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped

@Entity
@Table
public class Room implements Serializable{
   
    
    @Id
    @GeneratedValue
    private long roomId;
    
    @Column
    private String roomNumber;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Clinic associatedClinic;
    
     @OneToMany(mappedBy = "relatedRoom",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RoomTimeSlot> roomTimeSlots;
  
     
    public long getRoomId() {
        return roomId;
    }
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
   
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    

    public Clinic getAssociatedClinic() {
        return associatedClinic;
    }
    public void setAssociatedClinic(Clinic associatedClinic) {
        this.associatedClinic = associatedClinic;
    }

    public List<RoomTimeSlot> getRoomTimeSlots() {
        return roomTimeSlots;
    }

    public void setRoomTimeSlots(List<RoomTimeSlot> roomTimeSlots) {
        this.roomTimeSlots = roomTimeSlots;
    }
 
    
     public void saveRoom() {
        ObjectDao clinicDao = new ObjectDao();
        generateTimeSlots();
        clinicDao.addOrUpdateObject(this);
    }

    public void updateRoom() {
        ObjectDao roomDao = new ObjectDao();
        roomDao.updateObject(this);
    }

    public void deleteRoom() {
        ObjectDao roomDao = new ObjectDao();
        roomDao.deleteObject(this);
    }

    public static ArrayList<Room> getAllRooms() {
        ArrayList<Room> rooms;
        ObjectDao roomDao = new ObjectDao();
        rooms = roomDao.getAllObjects("Room");
        return rooms;
    }
    
     public void generateTimeSlots() {
        MutableDateTime slotStatTime = new MutableDateTime();
        MutableDateTime slotEndTime = new MutableDateTime();

        ArrayList<ClinicHours> clinicHoursList = ClinicHours.getAllClinicHours();
        ClinicHours clinicHours = clinicHoursList.get(0);
        
        long availabilityStartTime = clinicHours.getStartTime().getMillis();
        long availabilityEndTime = clinicHours.getEndTime().getMillis();

        long availableDuration = availabilityEndTime-availabilityStartTime  ;
        long slotDuration = 60 * 60 * 1000; // 60 min * 60 sec * 1000 millisecond

        if (availableDuration > 0) {

            long currentSlotStartTime = availabilityStartTime;
            boolean stopSlicing = false;
            while (!stopSlicing) {
                //<editor-fold defaultstate="collapsed" desc="new PhysicianTimeSlot ">
                RoomTimeSlot newTimeSlot = new RoomTimeSlot();
                slotStatTime.setMillis(currentSlotStartTime);
                slotEndTime.setMillis(currentSlotStartTime + slotDuration);

                newTimeSlot.setStartTime(slotStatTime.toDateTime());
                newTimeSlot.setEndTime(slotEndTime.toDateTime());
                newTimeSlot.setIsAvailable(true);
                newTimeSlot.setRelatedRoom(this);

          //</editor-fold>
                
                this.roomTimeSlots.add(newTimeSlot);
                availableDuration = availableDuration - slotDuration;
                currentSlotStartTime = currentSlotStartTime + slotDuration;
                if (availableDuration < slotDuration) { // I ti should be smaller than only to add the last slot to the time slots.
                    stopSlicing = true;
                }
            }

        }
    }
}
