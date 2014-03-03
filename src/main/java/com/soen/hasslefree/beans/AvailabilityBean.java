/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.PhysicianAvailability;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

    private ArrayList<PhysicianAvailability> availabilityList = PhysicianAvailability.getAllPhysicianAvailabilities();

    ;

    public ArrayList<PhysicianAvailability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(ArrayList<PhysicianAvailability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public String deleteAvailibility() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long id = Long.parseLong(params.get("availID"));
        PhysicianAvailability tempAvailability = PhysicianAvailability.getPhysicianAvailabilityById(id);
        tempAvailability.deletePhysicianAvailability();
        return "myAvailabilities";
    }
}
