/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime dateOfBirth;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "relatedPatient")
    private List<Appointment> appointments;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

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

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
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

    public static boolean setUserRole(String emailAddress, String roleName) {

        String Jdbc_Driver = "com.mysql.jdbc.Driver";
        String Db_Url = "jdbc:mysql://localhost:3306/hasslefree";
        String Db_UserName = "root";
        String db_Password = "quarkuds123";

        String query = "INSERT INTO user_roles(email,role_name) VALUES (?,?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(Jdbc_Driver);
            conn = DriverManager.getConnection(Db_Url, Db_UserName, db_Password);
            stmt = conn.prepareStatement(query);
            stmt.setString(1, emailAddress);
            stmt.setString(2, roleName);
            int result = stmt.executeUpdate();
            return result >= 1;
        } catch (SQLException se) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, se);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, se);
                return false;
            }
        }
    }
}
