/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.UserDao;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@RequestScoped
public class User implements Serializable {

    private long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private Address homeAddress;
    private String phoneNumber;

    private String simpleDate, message, selectedname;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm");

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void saveUser() {
//        try {
//            Date dateOfBirth = simpleDateFormat.parse(simpleDate);
//            this.dateOfBirth = dateOfBirth;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        try {
            UserDao dao = new UserDao();
            dao.addUser(this);
            this.message = "User Info Saved Successfull!";
            clearAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearAll();
    }

    public void updateUser() {
             try {
            Date dateOfBirth = simpleDateFormat.parse(simpleDate);
            this.dateOfBirth = dateOfBirth;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            UserDao dao = new UserDao();
            dao.updateUser(this);
            this.message = "User Info Update Successfull!";
            clearAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
            clearAll();
        }
    

    public void deleteUser() {
        try {
        UserDao dao = new UserDao();
        dao.deleteUser(userId);
         this.message = "User Info Deleted Successfull!";
         } catch (Exception e) {
            e.printStackTrace();
        }
            clearAll(); 
    }

    public void setSelectedname(String selectedname) {
        this.selectedname = selectedname;
    }

    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<User>();
        UserDao dao = new UserDao();
        users = dao.getAllUsers();
        return users;
    }

//    public void fullInfo() {
//        UserDao dao = new UserDao();
//        Set<User> lc = dao.getUserById(selectedname);
//        System.out.println(lc.get(0).firstName);
//        this.userId = lc get(0).custId;
//        this.firstName = lc.get(0).firstName;
//        this.lastName = lc.get(0).lastName;
//        this.email = lc.get(0).email;
//        this.dateOfBirth = lc.get(0).dob;
//        this.simpleDate = sdf.format(dob);
//    }

    private void clearAll() {
        this.firstName = "";
        this.lastName = "";
        this.simpleDate = "";
        this.email = "";
        this.userId = 0;
    }

}
