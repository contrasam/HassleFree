/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    
     public void saveClinic() {
        ObjectDao clinicDao = new ObjectDao();
        clinicDao.addObject(this);
    }

    public void updateClinic() {
        ObjectDao roomDao = new ObjectDao();
        roomDao.updateObject(this);
    }

    public void deleteClinic() {
        ObjectDao roomDao = new ObjectDao();
        roomDao.deleteObject(this);
    }

    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> rooms;
        ObjectDao roomDao = new ObjectDao();
        rooms = roomDao.getAllObjects("Room");
        return rooms;
    }
}
