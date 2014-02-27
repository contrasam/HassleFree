/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import java.util.Set;


/**
 *
 * @author Khalid
 */
public class TimeAvailability {

    private String availabilityType;
    private Set<TimeSlot> availableSlots;

    public String getAvailabilityType() {
        return availabilityType;
    }

    public void setAvailabilityType(String availabilityType) {
        this.availabilityType = availabilityType;
    }

    public Set<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Set<TimeSlot> availableSlots) {
        this.availableSlots = availableSlots;
    }

}
