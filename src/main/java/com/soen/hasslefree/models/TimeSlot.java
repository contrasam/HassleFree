/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This abstracts the time slot
 * @author Khalid
 */
public class TimeSlot {

    private GregorianCalendar start;
    private GregorianCalendar end;

    public TimeSlot(int year, int month, int day, int startHour, int startMinutes, int endHour, int endMinutes) {
        this.start = new GregorianCalendar( year, month, day, startHour,startMinutes);
        this.end = new GregorianCalendar( year, month, day, endHour,endMinutes);
    }

    TimeSlot(int year, int month, int day, int startHour, int startMinutes) {
       this.start = new GregorianCalendar( year, month, day, startHour,startMinutes);
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(GregorianCalendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
    }

    public ArrayList<Long> splitTimeIntoIntervals(int minutesInterval) {
        GregorianCalendar tempCalendar = new GregorianCalendar();
        tempCalendar.setTime(this.start.getTime());
        ArrayList<Long> timeList = new ArrayList();
        
        while (tempCalendar.get(Calendar.HOUR_OF_DAY) < this.end.get(Calendar.HOUR_OF_DAY)) {
            timeList.add(tempCalendar.getTimeInMillis());
            tempCalendar.add(Calendar.MINUTE, minutesInterval);
        }
        
        return timeList;
    }

//    public ArrayList<Long> excludeBlockedIntervals(ArrayList<Long> timeList, ArrayList<Appointment> appointmentList) {
//        // time.before(appointment.getApointmentSlot().getEnd()) && time.after(appointment.getApointmentSlot().getStart())
//        ArrayList<Long> excludedTimes = new ArrayList();
//        for (long time : timeList) {
//            for (Appointment appointment : appointmentList) {
//                long startTimeInMilSeconds =  appointment.getAppointmentSlot().start.getTimeInMillis();
//                long endTimeInMilSeconds =  appointment.getAppointmentSlot().end.getTimeInMillis();
//                
//                if (time >= startTimeInMilSeconds && time <= endTimeInMilSeconds) {
//                    excludedTimes.add(time);
//                }
//            }
//        }
//        return excludedTimes;
//    }
    
    
    
    public ArrayList<Long> nonConflictTime (ArrayList<Long> clinicTimeList, ArrayList<Long> excludeList){
         ArrayList<Long> nonConflictTimeList = new ArrayList();
        
        for (long clinicTime : clinicTimeList) {
            boolean isFound = false;
            for (long appointmentTime:excludeList )
             if(clinicTime == appointmentTime){
               isFound=true;
             }   
            if (!isFound){
                nonConflictTimeList.add(clinicTime);
            }
        }
        return nonConflictTimeList;
    }
}
