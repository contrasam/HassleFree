/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soen.hasslefree.models;

/**
 *
 * @author Khalid
 */
public class Room {
    
    private long roomId;
    private String roomNumber;
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
}
