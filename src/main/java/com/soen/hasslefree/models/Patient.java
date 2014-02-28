/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@RequestScoped

@Entity
@Table
@PrimaryKeyJoinColumn(name = "userId")
public class Patient extends User implements Serializable {

    @Column
    private String healthCardNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Physician familyDoctor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Address homeAddress;

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public Physician getFamilyDoctor() {
        return familyDoctor;
    }

    public void setFamilyDoctor(Physician familyDoctor) {
        this.familyDoctor = familyDoctor;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
    // TODO: Credit Card information is not yet relatedto the patient

    public void savePatient() {
        ObjectDao patientDao = new ObjectDao();
        patientDao.addObject(this);
    }

    public void updatePatient() {
        ObjectDao patientDao = new ObjectDao();
        patientDao.updateObject(this);
    }

    public void deletePatient() {
        ObjectDao patientDao = new ObjectDao();
        patientDao.deleteObject(this);
    }

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patients;
        ObjectDao patientDao = new ObjectDao();
        patients = patientDao.getAllObjects("Patient");
        return patients;
    }
}
