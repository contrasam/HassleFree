package com.soen.hasslefree.models;


import com.soen.hasslefree.persistence.*;

/**
 *
 * @author Khalid
 */
public class AddressManagement {

    public static void main(String[] args) {

        System.out.println("start ");

        Persistence persistence = new Persistence();

        AddressManagement addressMng = new AddressManagement();
        persistence.beginSession();

        String streetNumber = "248";
        String streetName = "Guy St";
        String apartmentNumber = "406";
        String city = "Montreal";
        String province = "Quebec";
        String postalCode = "H5E 1C8";
        String country = "Canada";
        Address address = new Address(streetNumber, streetName, apartmentNumber, city, province, postalCode, country);

        persistence.commitSession();
    }
}
