///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.soen.hasslefree.run;
//
//import com.soen.hasslefree.persistence.HibernateUtil;
//import com.soen.hasslefree.models.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//import org.hibernate.Session;
//
///**
// *
// * @author Khalid
// */
public class TimeProblem {
//
//    /**
//     * @param args the command line arguments
//     */
//    @SuppressWarnings("empty-statement")
//    public static void main(String[] args) {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        TimeSlot ts;
//        ts = new TimeSlot(2014, 1, 15, 8, 0, 17, 0);
//        ArrayList<Long> timeList;
//
//        timeList = ts.splitTimeIntoIntervals(10);
//
//
//        Appointment appointment1 = new Appointment(2014, 1, 15, 10, 10, "DIA");
//        Appointment appointment2 = new Appointment(2014, 1, 15, 8, 0, "DIA");
//        ArrayList<Appointment> appointmentList = new ArrayList();
//        appointmentList.add(appointment1);
//        appointmentList.add(appointment2);
//        for (Appointment appointment : appointmentList) {
//            System.out.println("Start Time: " + appointment.getAppointmentSlot().getStart().getTime());
//            System.out.println("End Time: " + appointment.getAppointmentSlot().getEnd().getTime());
//        }
//
//        System.out.println(appointment1.getAppointmentSlot().getEnd().getTime());
//        System.out.println(appointment2.getAppointmentSlot().getEnd().getTime());
//        ArrayList<Long> excludeList;
//        excludeList = ts.excludeBlockedIntervals(timeList, appointmentList);
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        
//        // Creating address for the clinic
////        Address address = new Address();
////        address.setApartmentNumber("1502");
////        address.setCity("Montreal");
////        address.setCountry("Canada");
////        address.setPostalCode("H3H 2J2");
////        address.setProvince("Quebec");
////        address.setStreetName("St Mathieu");
////        address.setStreetNumber("2055");
//        
//        // Creating a room for the clinic
//        Room room = new Room();
//        room.setRoomNumber("905");
//        
//        Set<Room> roomList = new HashSet<Room>();;
//        roomList.add(room);
//        Clinic clinic = new Clinic();
////        clinic.setAddress(address);
//        clinic.setName("Ville Marie Clinic");
//        clinic.setRooms(roomList);
//        
//        room.setAssociatedClinic(clinic);
//        
//        session.getTransaction().commit();
//       // HibernateUtil.shutdown();
//
//    }
}
