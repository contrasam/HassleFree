/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import java.util.Date;

/**
 *
 * @author Khalid
 */
public class AddressManagement {

    public static void main(String[] args) {

        System.out.println("start ");
        String streetNumber = "248";
        String streetName = "Guy St";
        String apartmentNumber = "406";
        String city = "Montreal";
        String province = "Quebec";
        String postalCode = "H5E 1C8";
        String country = "Canada";
        Address address = new Address(streetNumber, streetName, apartmentNumber, city, province, postalCode, country);
        address.saveAddress();
        User user = new User();
        user.setEmail("pradeep.samuel90@gmail.com");
        user.setFirstName("Pradeep Samuel");
        user.setGender("Male");
        user.setHomeAddress(address);
        user.setLastName("Daniel");
        user.setPassword("quarkuds123");
        user.setPhoneNumber("514-430-8730");
        user.setDateOfBirth(new Date());
        user.saveUser();
        
    }
}
