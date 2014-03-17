/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import static org.richfaces.application.push.TopicKey.factory;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Room relatedRoom;

    @OneToOne(fetch = FetchType.EAGER)
    private PhysicianTimeSlot physicianTimeSlot;

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

    public PhysicianTimeSlot getPhysicianTimeSlot() {
        return physicianTimeSlot;
    }

    public void setPhysicianTimeSlot(PhysicianTimeSlot physicianTimeSlot) {
        this.physicianTimeSlot = physicianTimeSlot;
    }

    public void saveRoomTimeSlot() {
        ObjectDao roomTimeSlotDao = new ObjectDao();
        roomTimeSlotDao.addOrUpdateObject(this);
    }

//    public void updateRoomTimeSlot(RoomTimeSlot roomSlot ) {
//        ObjectDao roomTimeSlotDao = new ObjectDao();
//        roomTimeSlotDao.updateObject(this);
//    
//      Session session = session = HibernateUtil.getSessionFactory().openSession();
//      Transaction tx = null;
//      try{
//         tx = session.beginTransaction();
//         RoomTimeSlot dbRoomSlot = (RoomTimeSlot)session.get(RoomTimeSlot.class, roomSlot.getRoomTimeSlotID()); 
//         session.merge(roomSlot);
//         session.update(roomSlot); 
//         tx.commit();
//      }catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//   }
    public void updateRoomTimeSlot() {
        ObjectDao roomTimeSlotDao = new ObjectDao();
        roomTimeSlotDao.addOrUpdateObject(this);
    }
//    public void updateRoomTimeSlot() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            //RoomTimeSlot dbRoomSlot = (RoomTimeSlot) session.get(RoomTimeSlot.class, this.roomTimeSlotID);
//            // Update Object
//            session.merge(this);
//            //object = session.merge(object); // Success! 
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }

    public void deleteRoomTimeSlot() throws IllegalAccessException, InvocationTargetException {
        ObjectDao roomTimeSlotDao = new ObjectDao();
        roomTimeSlotDao.deleteObject(this,this.roomTimeSlotID,RoomTimeSlot.class);
    }

    public static RoomTimeSlot getRoomTimeSlotById(int id) {
        RoomTimeSlot roomTimeSlotHolder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            roomTimeSlotHolder
                    = (RoomTimeSlot) session.get(Room.class, id);
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

    public static ArrayList getAvailableRoomSlotsForDate(DateTime startTime, DateTime endTime) {
        ArrayList<RoomTimeSlot> roomTimeSlos = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM RoomTimeSlot Where (startTime between :startTime  and :endTime) and (physicianTimeSlot_physicianTimeSlotID is null) order by startTime");
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);
            //query.setParameter("isAvailable", true);
            roomTimeSlos = (ArrayList) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return roomTimeSlos;

    }

    public static ArrayList getFilteredAvailableRoomSlotsForDate(DateTime startTime, DateTime endTime) {
        ArrayList<RoomTimeSlot> roomSlots = RoomTimeSlot.getAvailableRoomSlotsForDate(startTime, endTime);
        ArrayList<RoomTimeSlot> filteredRoomSlots = new ArrayList<RoomTimeSlot>();
        Set<DateTime> timeSet = new HashSet<DateTime>();
        for (RoomTimeSlot slot : roomSlots) {
            if (timeSet.add(slot.getStartTime())) {
                filteredRoomSlots.add(slot);
            }
        }

        return filteredRoomSlots;
    }

}
