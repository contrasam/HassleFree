/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.run;

import com.soen.hasslefree.models.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
public class Test2DB {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

//        DateTime clinicStartTime = new DateTime(2014, 3, 14, 8, 0);
//        DateTime clinicEndTime = new DateTime(2014, 3, 14, 10, 0);
//        Clinic.generateRoomTimeSlots(clinicStartTime, clinicEndTime);
        
//        ArrayList <RoomTimeSlot> roomSlots =     RoomTimeSlot.getAvailableRoomSlotsForDate(clinicStartTime, clinicEndTime);

//        for(RoomTimeSlot slot:roomSlots){
//        System.out.println("Slot Id:" + slot.getRoomTimeSlotID()+ " Stat Time:" + slot.getStartTime() + " End Time:" + slot.getEndTime());
//        }
//        double d = 3.0;    
//	 
//        System.out.println(Math.ceil(d));
        
        
         Physician physician = Physician.getPhysicianById(4);
         physician.deletePhysician();
        
        
        
//        DateTime startDate = new DateTime(2014, 3, 14,9, 0);
//        DateTime endDate = new DateTime(2014, 3, 14, 10, 0);
//
//        Physician physician = Physician.getPhysicianById(4);
//        PhysicianAvailability physicianAvailability = new PhysicianAvailability();
//        physicianAvailability.setStartTime(startDate);
//        physicianAvailability.setEndTime(endDate);
//        physicianAvailability.setRelatedPhysician(physician);
//        boolean hasCommitted =physicianAvailability.savePhysicianAvailability();
//        System.out.println(hasCommitted);
        
        
//        DateTime date = new DateTime(2014, 3, 14, 10, 0);
//        Physician physician = new Physician();
//        physician.setFirstName("First Name999");
//        physician.setLastName("Last Name999");
//        physician.setJoinedDate(date);
//        physician.setDateOfBirth(date);
//        physician.setSpeciality("Heart999");
//        physician.savePhysician();
//        
//        Physician physician2 = new Physician();
//         BeanUtils.copyProperties(physician2,physician );
//        physician2.setFirstName("Updated FirstName999");
//        physician2.setLastName("Updated LastName999");
//        physician2.setSpeciality("updated sp");
//        physician2.updatePhysician();
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
//        DateTime startDate = new DateTime(2014, 3, 2, 8, 0);
//        DateTime endDate = new DateTime(2014, 3, 2, 10, 0);
//        ArrayList<ArrayList<PhysicianTimeSlot>> allCheckUps;
//        
//        Physician physician = Physician.getPhysicianById(2);
//        allCheckUps=Appointment.getallAvailableCheckUpsByPhysicianID (physician.getUserId(),startDate, endDate);
//        
//        System.out.println(allCheckUps.size());
//        for (ArrayList<PhysicianTimeSlot> checkUp : allCheckUps) {
//            System.out.println("                                                   ");
//            System.out.println("----------  Bigin of First Check UP --------------------------");
//         
//        for (PhysicianTimeSlot slot : checkUp) {
//            physician = slot.getRelatedPhysician();
//            System.out.println("Slot Id:" + slot.getPhysicianTimeSlotID() + "        Stat Time:" + slot.getStartTime() + " End Time:" + slot.getEndTime()+ "          Physician Name:" + physician.getFirstName()+" "+ physician.getLastName() );
//        }
//          System.out.println("----------  End of First Check UP --------------------------");   
//          System.out.println("                                                   ");  
//        }
    }
}
