/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped
        
@Entity
@Table
@PrimaryKeyJoinColumn(name = "userId")
public class Nurse extends User implements Serializable {

    @Column
    private String degree;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    public void saveNurse() {
        ObjectDao nurseDao = new ObjectDao();
        nurseDao.addObject(this);
    }

    public void updateNurse() throws IllegalAccessException, InvocationTargetException {
        ObjectDao nurseDao = new ObjectDao();
        nurseDao.updateObject(this,this.getUserId(),Nurse.class);
    }

    public void deleteNurse() throws IllegalAccessException, InvocationTargetException {
        ObjectDao nurseDao = new ObjectDao();
        nurseDao.deleteObject(this,this.getUserId(),Nurse.class);
    }

    public ArrayList<Nurse> getAllNurses() {
        ArrayList<Nurse> nurses;
        ObjectDao nurseDao = new ObjectDao();
        nurses = nurseDao.getAllObjects("Nurse");
        return nurses;

}
}