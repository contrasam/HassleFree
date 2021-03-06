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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class PhysicianTimeSlot implements Serializable {

    @Id
    @GeneratedValue
    private long physicianTimeSlotID;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime startTime;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime endTime;

    @Column
    private boolean isAvailable = true;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PhysicianAvailability physicianAvailability;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Physician relatedPhysician;

   
    @OneToOne(mappedBy = "physicianTimeSlot",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RoomTimeSlot relatedRoomTimeSlot;

    public long getPhysicianTimeSlotID() {
        return physicianTimeSlotID;
    }

    public void setPhysicianTimeSlotID(long physicianTimeSlotID) {
        this.physicianTimeSlotID = physicianTimeSlotID;
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

    public PhysicianAvailability getPhysicianAvailability() {
        return physicianAvailability;
    }

    public void setPhysicianAvailability(PhysicianAvailability physicianAvailability) {
        this.physicianAvailability = physicianAvailability;
    }

    public Physician getRelatedPhysician() {
        return relatedPhysician;
    }

    public void setRelatedPhysician(Physician relatedPhysician) {
        this.relatedPhysician = relatedPhysician;
    }

    public RoomTimeSlot getRelatedRoomTimeSlot() {
        return relatedRoomTimeSlot;
    }

    public void setRelatedRoomTimeSlot(RoomTimeSlot relatedRoomTimeSlot) {
        this.relatedRoomTimeSlot = relatedRoomTimeSlot;
    }

   

    public void savePhysicianTimeSlot() {
        ObjectDao physicianTimeSlotDao = new ObjectDao();
        physicianTimeSlotDao.addObject(this);
    }

    public void updatePhysicianTimeSlot() {
        ObjectDao physicianTimeSlotDao = new ObjectDao();
        physicianTimeSlotDao.addOrUpdateObject(this);
    }

    public void deletePhysicianTimeSlot() throws IllegalAccessException, InvocationTargetException {
        ObjectDao physicianTimeSlotDao = new ObjectDao();
        physicianTimeSlotDao.deleteObject(this,this.getPhysicianTimeSlotID(),PhysicianTimeSlot.class);
    }

    public static PhysicianTimeSlot getPhysicianTimeSlotById(long id) {
        PhysicianTimeSlot physicianTimeSlotHolder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            physicianTimeSlotHolder = (PhysicianTimeSlot) session.get(Physician.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return physicianTimeSlotHolder;
    }

    public static ArrayList<PhysicianTimeSlot> getAllPhysicianTimeSlots() {
        ArrayList<PhysicianTimeSlot> physicianTimeSlots;
        ObjectDao physicianTimeSlotDao = new ObjectDao();
        physicianTimeSlots = physicianTimeSlotDao.getAllObjects("PhysicianTimeSlot");
        return physicianTimeSlots;
    }
}
