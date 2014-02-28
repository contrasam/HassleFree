/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.run;

import com.soen.hasslefree.models.Address;
import com.soen.hasslefree.models.User;
import java.util.Date;

/**
 *
 * @author Khalid
 */
public class TestDao {

    public static void main(String[] args) {

//        Address homeAddress = new Address ();
//        homeAddress.setStreetNumber("233");
//        homeAddress.setStreetName("Guy");
//        homeAddress.setApartmentNumber("416");
//        homeAddress.setCity("Verdun");
//        homeAddress.setPostalCode("HHH");
//        homeAddress.setProvince("QC");
//        homeAddress.setCountry("Canada");
//        homeAddress.saveAddress();
        
        
        User user = new User();
        user.setFirstName("Pradeep");
        user.setLastName("Altoum");
        user.setEmail("a@b.com");
        user.setPassword("112233");
        user.setGender("Male");
        user.setPhoneNumber("514-999-9999");
        user.setDateOfBirth(new Date());
        user.saveUser();
    }

}
