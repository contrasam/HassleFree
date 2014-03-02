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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
public class RoomTimeSlot implements Serializable {

    @Id
    @GeneratedValue
    private long roomTimeSlotID;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime startTime;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime endTime;
    
    @Column
    private boolean isAvailable = true;


    @ManyToOne(cascade = CascadeType.ALL)
    private Room relatedRoom;

    public long getRoomTimeSlotID() {
        return roomTimeSlotID;
    }

    public void setRoomTimeSlotID(long roomTimeSlotID) {
        this.roomTimeSlotID = roomTimeSlotID;
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

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Room getRelatedRoom() {
        return relatedRoom;
    }

    public void setRelatedRoom(Room relatedRoom) {
        this.relatedRoom = relatedRoom;
    }


 public void saveRoomTimeSlot() {
        ObjectDao roomTimeSlotDao = new ObjectDao();
        roomTimeSlotDao.addObject(this);
    }

    public void updateRoomTimeSlot() {
        ObjectDao roomTimeSlotDao = new ObjectDao();
        roomTimeSlotDao.updateObject(this);
    }

    public void deleteRoomTimeSlot() {
        ObjectDao roomTimeSlotDao = new ObjectDao();
        roomTimeSlotDao.deleteObject(this);
    }

    public static RoomTimeSlot getPhysicianTimeSlotById(int id) {
        RoomTimeSlot roomTimeSlotHolder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            roomTimeSlotHolder = (RoomTimeSlot) session.get(Room.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return roomTimeSlotHolder;
    }

    public static ArrayList<RoomTimeSlot> getAllRoomTimeSlots() {
        ArrayList<RoomTimeSlot> roomTimeSlots;
        ObjectDao roomTimeSlotDao = new ObjectDao();
        roomTimeSlots = roomTimeSlotDao.getAllObjects("RoomTimeSlot");
        return roomTimeSlots;
    }
}
