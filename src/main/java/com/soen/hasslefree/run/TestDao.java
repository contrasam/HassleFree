/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.run;

import com.soen.hasslefree.dao.ObjectDao;
import com.soen.hasslefree.models.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
public class TestDao {

    public static void main(String[] args) {

//        DateTime dateOfBirth = new DateTime(1976, 12, 31, 11, 0);
//        DateTime joinedDate = new DateTime(2013, 1, 1, 9, 5);
//
//        Physician physician = new Physician();
//
//        physician.setFirstName("Richard");
//        physician.setLastName("Paul Francis");
//        physician.setDateOfBirth(dateOfBirth.toDateTime());
//        physician.setPassword("1111");
//        physician.setEmail("c@d.com");
//        physician.setGender("Male");
//        physician.setJoinedDate(joinedDate.toDateTime());
//        physician.setSpeciality("General");
//        physician.setPhoneNumber("514-44-8763");
//        physician.savePhysician();
////        
////        
////        
////        
//        DateTime statDate = new DateTime(2014, 3, 3, 8, 0);
//        DateTime endDate = new DateTime(2014, 3, 3, 17, 0);
//
//        PhysicianAvailability pa = new PhysicianAvailability();
//        pa.setStartTime(statDate);
//        pa.setEndTime(endDate);
//        pa.setRelatedPhysician(physician);
//        pa.savePhysicianAvailability();
//        DateTime statDate = new  DateTime(2014, 3, 1, 8, 0);
//        DateTime endDate = new DateTime(2014, 3, 1, 17, 0);
//        Set<Room> roomSet = new HashSet();
//        Clinic clinic = new Clinic();
//        
//        Address addressOfClinic = new Address();
//        addressOfClinic.setApartmentNumber("2020");
//        addressOfClinic.setCity("Montreal");
//        addressOfClinic.setCountry("Canada");
//        addressOfClinic.setPostalCode("H3H 2J2");
//        addressOfClinic.setProvince("Quebec");
//        addressOfClinic.setStreetName("St Marc");
//        addressOfClinic.setStreetNumber("1245");
//        addressOfClinic.saveAddress();
//
//        Room room1 = new Room();
//        room1.setRoomNumber("1A");
//        room1.setAssociatedClinic(clinic);
//        room1.saveRoom();
//
//        Room room2 = new Room();
//        room2.setRoomNumber("2A");
//        room2.setAssociatedClinic(clinic);
//        room2.saveRoom();
//
//        Room room3 = new Room();
//        room3.setRoomNumber("3A");
//        room3.setAssociatedClinic(clinic);
//        room3.saveRoom();
//
//        Room room4 = new Room();
//        room4.setRoomNumber("4A");
//        room4.setAssociatedClinic(clinic);
//        room4.saveRoom();
//
//        Room room5 = new Room();
//        room5.setRoomNumber("5A");
//        room5.setAssociatedClinic(clinic);
//        room5.saveRoom();
//
//        roomSet.add(room1);
//        roomSet.add(room2);
//        roomSet.add(room3);
//        roomSet.add(room4);
//        roomSet.add(room5);
//
//        clinic.setName("Hassle Free Clinic");
//        clinic.setAddress(addressOfClinic);
//        clinic.setRooms(roomSet);
//
//        clinic.saveClinic();
//        
//        ClinicHours hrs = new ClinicHours();
//        hrs.setRelatedClinic(clinic);
//        hrs.setStartTime(statDate);
//        hrs.setEndTime(endDate);
//        hrs.saveClinicHours();
//        ArrayList<ClinicHours> clinicHours = ClinicHours.getAllClinicHours();
//
//        ArrayList<PhysicianTimeSlot> timeSlotList = Appointment.getAvailableDropInByPhysicianID(3, clinicHours.get(0).getStartTime(), clinicHours.get(0).getEndTime());
//        for (PhysicianTimeSlot timeSlot : timeSlotList) {
//            String hour = Integer.toString(timeSlot.getStartTime().getHourOfDay());
//            String minutes = Integer.toString(timeSlot.getStartTime().getMinuteOfHour());
//            if (minutes.equals("0")) {
//                minutes = "00";
//            } else if (hour.length() <= 1) {
//                hour = "0" + hour;
//            }
//            System.out.println("For Drop In -> "+(hour + ":" + minutes));
//        }
//
//        ArrayList<ArrayList<PhysicianTimeSlot>> timeSlotListCU = Appointment.getallAvailableCheckUpsByPhysicianID(2, clinicHours.get(0).getStartTime(), clinicHours.get(0).getEndTime());
//        for (ArrayList<PhysicianTimeSlot> inner : timeSlotListCU) {
//            String hour = Integer.toString(inner.get(0).getStartTime().getHourOfDay());
//            String minutes = Integer.toString(inner.get(0).getStartTime().getMinuteOfHour());
//
//            if (minutes.equals("0")) {
//                minutes = "00";
//            }
//            if (hour.length() == 1) {
//                hour = "0" + hour;
//            }
//            System.out.println("For Check Up -> "+(hour + ":" + minutes));
//        }
//        ArrayList<ClinicHours> clinicHours = ClinicHours.getAllClinicHours();
//        ArrayList<ArrayList<PhysicianTimeSlot>> timeSlotListCU = Appointment.getallAvailableCheckUpsByPhysicianID(4, clinicHours.get(0).getStartTime(), clinicHours.get(0).getEndTime());
//        // Persisting values to access from request scoped (Appointment Bean)
//        for (int i = 0; i < timeSlotListCU.size(); i++) {
//
//            String hourCU = Integer.toString(timeSlotListCU.get(i).get(0).getStartTime().getHourOfDay());
//            String minutesCU = Integer.toString(timeSlotListCU.get(i).get(0).getStartTime().getMinuteOfHour());
//
//            if (minutesCU.equals("0")) {
//                minutesCU = "00";
//            }
//            if (hourCU.length() == 1) {
//                hourCU = "0" + hourCU;
//            }
//            System.out.println("=================================>" + hourCU + ":" + minutesCU);
//            i+=2;
//        }
        
        ArrayList<Appointment> allAppointments = null;
        ArrayList<Appointment> filteredAppointments = new ArrayList();
        ObjectDao appointmentDao = new ObjectDao();
        allAppointments = appointmentDao.getAllObjects("Appointment");
        for (Appointment appointment : allAppointments) {
            if (appointment.getRelatedPatient().getUserId() == 5) {
                filteredAppointments.add(appointment);
            }
        }
        
        System.out.println(filteredAppointments);
    }

}
