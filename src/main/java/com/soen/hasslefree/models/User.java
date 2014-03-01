/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@RequestScoped

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long userId;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String gender;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;

    @Column
    private String phoneNumber;

//    private String simpleDate;
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm");

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
        ObjectDao userDao = new ObjectDao();
        userDao.addObject(this);
    }

    public void updateUser() {
        ObjectDao userDao = new ObjectDao();
        userDao.updateObject(this);
    }

    public void deleteUser() {
        ObjectDao userDao = new ObjectDao();
        userDao.deleteObject(this);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users; 
        ObjectDao userDao = new ObjectDao();
        users = userDao.getAllObjects("user");
        return users;
    }

    private void clearAll() {
        this.userId = 0;
        this.firstName = "";
        this.lastName = "";
        //this.simpleDate = "";
        this.email = "";
        this.password = "";
        this.gender = "";
        this.phoneNumber = "";
        this.dateOfBirth = null;
        this.email = "";

    }
}
