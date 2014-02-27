/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;


import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Khalid
 */


public class Clinic implements Serializable {

    private long clinicId;
    private String name;
    private Address address;
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
}
