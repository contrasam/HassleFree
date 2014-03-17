/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.beans;

import com.soen.hasslefree.models.Patient;
import com.soen.hasslefree.models.PhysicianAvailability;
import com.soen.hasslefree.models.User;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PradeepSamuel
 */
@ManagedBean
@SessionScoped
public class AvailabilityFromDbBean implements Serializable {

    private ArrayList<PhysicianAvailability> availabilityList;

    /**
     * Creates a new instance of availabilityBean
     */
    public AvailabilityFromDbBean() {
    }

    public ArrayList<PhysicianAvailability> getAvailabilityList() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        String loginId = (String) session.getAttribute("loginId");
        User physician = Patient.getPatientByEmail(loginId);
        availabilityList = PhysicianAvailability.getAllPhysicianAvailabilitiesforId(physician.getUserId());
        return availabilityList;
    }

    public void setAvailabilityList(ArrayList<PhysicianAvailability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public String deleteAvailibility() throws IllegalAccessException, InvocationTargetException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long id = Long.parseLong(params.get("availID"));
        PhysicianAvailability tempAvailability = PhysicianAvailability.getPhysicianAvailabilityById(id);
        tempAvailability.deletePhysicianAvailability();
        return "myAvailabilities";
    }
}
