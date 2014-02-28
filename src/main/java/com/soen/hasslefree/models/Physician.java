/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@RequestScoped

@Entity
@Table
@PrimaryKeyJoinColumn(name = "userId")
public class Physician extends User implements Serializable {

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date joinedDate;

    @Column
    private String speciality;

   
    @OneToMany(mappedBy = "familyDoctor",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Set<Patient> associatedPatients = new HashSet<Patient>(0);

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Set<Patient> getAssociatedPatients() {
        return associatedPatients;
    }

    public void setAssociatedPatients(Set<Patient> associatedPatients) {
        this.associatedPatients = associatedPatients;
    }
    public void savePhysician() {
        ObjectDao physicianDao = new ObjectDao();
        physicianDao.addObject(this);
    }

    public void updatePhysician() {
        ObjectDao physicianDao = new ObjectDao();
        physicianDao.updateObject(this);
    }

    public void deletePhysician() {
        ObjectDao physicianDao = new ObjectDao();
        physicianDao.deleteObject(this);
    }

    public ArrayList<Physician> getAllPhysicians() {
        ArrayList<Physician> physicians;
        ObjectDao physicianDao = new ObjectDao();
        physicians = physicianDao.getAllObjects("Physician");
        return physicians;
    }
}