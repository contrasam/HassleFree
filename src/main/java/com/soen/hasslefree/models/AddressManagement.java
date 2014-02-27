/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.persistence.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Khalid
 */
public class AddressManagement {

    public static void main(String[] args) {

//        System.out.println("start ");
//        String streetNumber = "248";
//        String streetName = "Guy St";
//        String apartmentNumber = "406";
//        String city = "Montreal";
//        String province = "Quebec";
//        String postalCode = "H5E 1C8";
//        String country = "Canada";
//        Address address = new Address(streetNumber, streetName, apartmentNumber, city, province, postalCode, country);
//        address.saveAddress();
//        User user = new User();
//        user.setEmail("pradeep.samuel90@gmail.com");
//        user.setFirstName("Pradeep Samuel");
//        user.setGender("Male");
//        user.setHomeAddress(address);
//        user.setLastName("Daniel");
//        user.setPassword("quarkuds123");
//        user.setPhoneNumber("514-430-8730");
//        user.setDateOfBirth(new Date());
//        user.saveUser();
        
//        AppointmentType appointmentType1 = new AppointmentType();
//        appointmentType1.setCost((float)60.00);
//        appointmentType1.setDuration((int)20);
//        appointmentType1.setTypeName("Drop In");
//        appointmentType1.saveAppointmentType();
//        AppointmentType appointmentType2 = new AppointmentType();
//        appointmentType2.setCost((float)120.00);
//        appointmentType2.setDuration((int)60);
//        appointmentType2.setTypeName("Annual Check Up");
//        appointmentType2.saveAppointmentType();
        List<String> appointmentTypes = new ArrayList<String>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            appointmentTypes = session.createQuery("SELECT typeName FROM AppointmentType").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        System.out.println(appointmentTypes);
        
    }
}
