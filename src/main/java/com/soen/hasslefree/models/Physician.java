/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author PradeepSamuel
 */
public class Physician extends User {
    private Date joinedDate;
    private String speciality;
    private Set<Patient> associatedPatients;
    
   

   
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
    
    
    
    
    
}
