/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Khalid
 */
public class Appointment {

    private TimeSlot appointmentSlot;
    private AppointmentType appointmentType;

    public Appointment(int year, int month, int day, int startHour, int startMinutes, String appointmentType) {
        this.appointmentType = new AppointmentType();
        this.appointmentType.setTypeName(appointmentType);
        this.appointmentType.setDuration(20);
        this.appointmentSlot = new TimeSlot(year, month, day, startHour, startMinutes);
        GregorianCalendar tempCalendar =(GregorianCalendar) appointmentSlot.getStart().clone() ;
        tempCalendar.add(Calendar.MINUTE, this.appointmentType.getDuration());
        this.appointmentSlot.setEnd(tempCalendar);
    }

    public TimeSlot getAppointmentSlot() {
        return appointmentSlot;
    }

    public void setAppointmentSlot(TimeSlot apointmentSlot) {
        this.appointmentSlot = apointmentSlot;
    }
}
