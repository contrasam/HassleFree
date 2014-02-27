/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

/**
 *
 * @author PradeepSamuel
 */
public class Patient extends User {

    private long patientId;
    private String healthCardNumber;
    private Physician familyDoctor;

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

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

    // TODO: Credit Card information is not yet relatedto the patient
}
