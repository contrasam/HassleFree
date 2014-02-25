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
public class Payment {
    private Appointment associatedAppointment;

    public Appointment getAssociatedAppointment() {
        return associatedAppointment;
    }

    public void setAssociatedAppointment(Appointment associatedAppointment) {
        this.associatedAppointment = associatedAppointment;
    }
    
    
}
