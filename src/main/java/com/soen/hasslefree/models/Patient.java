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

    private String healthCardNumber;
    private Doctor familyDoctor;

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public Doctor getFamilyDoctor() {
        return familyDoctor;
    }

    public void setFamilyDoctor(Doctor familyDoctor) {
        this.familyDoctor = familyDoctor;
    }

    // TODO: Credit Card information is not yet relatedto the patient
}
