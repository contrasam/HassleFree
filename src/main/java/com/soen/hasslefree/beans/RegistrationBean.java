/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Address;
import com.soen.hasslefree.models.Patient;
import com.soen.hasslefree.models.User;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.joda.time.MutableDateTime;

/**
 *
 * @author pr_danie
 */
@ManagedBean
@RequestScoped
public class RegistrationBean implements Serializable {
    
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String password;
    private String emailAddress;
    private String gender;
    private String phoneNumber;
    private String healthCardNumber;
    private String aptNumber;
    private String streetName;
    private String streetNumber;
    private String city;
    private String postalCode;
    private String province;
    private String country;
    private ArrayList<String> genderList;

    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean() {
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getHealthCardNumber() {
        return healthCardNumber;
    }
    
    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }
    
    public String getAptNumber() {
        return aptNumber;
    }
    
    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }
    
    public String getStreetName() {
        return streetName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
    public String getStreetNumber() {
        return streetNumber;
    }
    
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<String> getGenderList() {
        genderList = new ArrayList();
        genderList.add("Male");
        genderList.add("Female");
        return genderList;
    }

    public void setGenderList(ArrayList<String> genderList) {
        this.genderList = genderList;
    }
    
    
    public String registerPatient() {
        Address patientAddress = new Address();
        MutableDateTime dateOfBirthMod = new MutableDateTime(dateOfBirth);
             
        patientAddress.setApartmentNumber(aptNumber);
        patientAddress.setCity(city);
        patientAddress.setCountry(country);
        patientAddress.setPostalCode(postalCode);
        patientAddress.setProvince(province);
        patientAddress.setStreetName(streetName);
        patientAddress.setStreetNumber(streetNumber);
        patientAddress.saveAddress();
        
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setGender(gender);
        patient.setPassword(encryptPassword(password));
        patient.setPhoneNumber(phoneNumber);
        patient.setDateOfBirth(dateOfBirthMod.toDateTime());
        patient.setEmail(emailAddress);
        patient.setHealthCardNumber(healthCardNumber);
        patient.setHomeAddress(patientAddress);
        patient.savePatient();
        
        User.setUserRole(emailAddress, "patient");
        
        return "index";
    }
    
    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
