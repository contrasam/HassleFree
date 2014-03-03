/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.PhysicianAvailability;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@SessionScoped
public class AvailabilityBean implements Serializable {

    /**
     * Creates a new instance of availabilityBean
     */
    public AvailabilityBean() {
    }

    private ArrayList<PhysicianAvailability> availabilityList = PhysicianAvailability.getAllPhysicianAvailabilities();;

    public ArrayList<PhysicianAvailability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(ArrayList<PhysicianAvailability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public void deleteAvailibility(long id) {
        PhysicianAvailability tempAvailability = PhysicianAvailability.getPhysicianAvailabilityById(id);
        tempAvailability.deletePhysicianAvailability();
    }
}
