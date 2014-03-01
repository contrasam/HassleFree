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

    
    @ManyToOne(cascade = CascadeType.ALL)
    private PhysicianAvailability physicianAvailability;

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
 public void savePhysicianTimeSlot() {
        ObjectDao physicianTimeSlotDao = new ObjectDao();
        physicianTimeSlotDao.addObject(this);
    }

    public void updatePhysicianTimeSlot() {
        ObjectDao physicianTimeSlotDao = new ObjectDao();
        physicianTimeSlotDao.updateObject(this);
    }

    public void deletePhysicianTimeSlot() {
        ObjectDao physicianTimeSlotDao = new ObjectDao();
        physicianTimeSlotDao.deleteObject(this);
    }

    public static PhysicianTimeSlot getPhysicianTimeSlotById(int id) {
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
