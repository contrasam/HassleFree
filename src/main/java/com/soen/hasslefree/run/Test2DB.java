/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.run;

import com.soen.hasslefree.models.*;
import java.util.ArrayList;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
public class Test2DB {

    public static void main(String[] args) {

//        Clinic.generateRoomTimeSlots();
//        DateTime startDate = new DateTime(2014, 3, 2, 8, 0);
//        DateTime endDate = new DateTime(2014, 3, 2, 10, 0);
//
//        Physician physician = Physician.getPhysicianById(2);
//        PhysicianAvailability physicianAvailability = new PhysicianAvailability();
//        physicianAvailability.setStartTime(startDate);
//        physicianAvailability.setEndTime(endDate);
//        physicianAvailability.setRelatedPhysician(physician);
//        physicianAvailability.savePhysicianAvailability();
        
        
//        DateTime startDate = new DateTime(2014, 3, 2, 8, 0);
//        DateTime endDate = new DateTime(2014, 3, 2, 10, 0);
//        ArrayList<PhysicianTimeSlot> dropInSlots;
//        Physician physician = Physician.getPhysicianById(2);
//        Appointment appointment = new Appointment();
//
//        dropInSlots = appointment.getAvailableDropInByPhysicianID(physician.getUserId(), startDate, endDate);
//
//        System.out.println(dropInSlots.size());
//        for (PhysicianTimeSlot slot : dropInSlots) {
//            System.out.println("Slot Id:" + slot.getPhysicianTimeSlotID() + " Stat Time:" + slot.getStartTime() + " End Time:" + slot.getEndTime());
//        }

        
        
//        DateTime startDate = new DateTime(2014, 3, 2, 8, 0);
//        DateTime endDate = new DateTime(2014, 3, 2, 10, 0);
//        ArrayList<PhysicianTimeSlot> dropInSlots;
//        Appointment appointment = new Appointment();
//        Physician physician;
//        dropInSlots = appointment.getAvailableDropInForAnyPhysician( startDate, endDate);
//
//        System.out.println(dropInSlots.size());
//        for (PhysicianTimeSlot slot : dropInSlots) {
//            physician = slot.getRelatedPhysician();
//            System.out.println("Slot Id:" + slot.getPhysicianTimeSlotID() + "        Stat Time:" + slot.getStartTime() + " End Time:" + slot.getEndTime()+ "          Physician Name:" + physician.getFirstName()+" "+ physician.getLastName() );
//        }
        
        
        
        DateTime startDate = new DateTime(2014, 3, 2, 8, 0);
        DateTime endDate = new DateTime(2014, 3, 2, 10, 0);
        ArrayList<ArrayList<PhysicianTimeSlot>> allCheckUps;
        
        Physician physician = Physician.getPhysicianById(2);
        allCheckUps=Appointment.getallAvailableCheckUpsByPhysicianID (physician.getUserId(),startDate, endDate);
        
        System.out.println(allCheckUps.size());
        for (ArrayList<PhysicianTimeSlot> checkUp : allCheckUps) {
            System.out.println("                                                   ");
            System.out.println("----------  Bigin of First Check UP --------------------------");
         
        for (PhysicianTimeSlot slot : checkUp) {
            physician = slot.getRelatedPhysician();
            System.out.println("Slot Id:" + slot.getPhysicianTimeSlotID() + "        Stat Time:" + slot.getStartTime() + " End Time:" + slot.getEndTime()+ "          Physician Name:" + physician.getFirstName()+" "+ physician.getLastName() );
        }
          System.out.println("----------  End of First Check UP --------------------------");   
          System.out.println("                                                   ");  
        }
        
    
        
        
        
        
        
    }
}
