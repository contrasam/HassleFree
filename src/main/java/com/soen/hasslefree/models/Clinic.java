/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped

@Entity
@Table
public class Clinic implements Serializable {

    @Id
    @GeneratedValue
    private long clinicId;
    
    @Column
    private String name;
    
    
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
    
    
    @OneToMany(mappedBy = "associatedClinic",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Set<Room> rooms;

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
    
    public void saveClinic() {
        ObjectDao clinicDao = new ObjectDao();
        clinicDao.addObject(this);
    }

    public void updateClinic() throws IllegalAccessException, InvocationTargetException {
        ObjectDao clinicDao = new ObjectDao();
        clinicDao.updateObject(this,this.clinicId,Clinic.class);
    }

    public void deleteClinic() throws IllegalAccessException, InvocationTargetException {
        ObjectDao clinicDao = new ObjectDao();
        clinicDao.deleteObject(this,this.clinicId,Clinic.class);
    }

    public static ArrayList<Clinic> getAllClinics() {
        ArrayList<Clinic> clinics;
        ObjectDao AppointmentDao = new ObjectDao();
        clinics = AppointmentDao.getAllObjects("Clinic");
        return clinics;
    }
    
    public static void  generateRoomTimeSlots(DateTime clinicStartTime,DateTime clinicEndTime){
    ArrayList<Room> rooms = Room.getAllRooms();
    for(Room room:rooms){
    room.saveRoom(clinicStartTime,clinicEndTime);
    }
    
    }
}
