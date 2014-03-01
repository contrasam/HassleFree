/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import com.soen.hasslefree.dao.ObjectDao;
import com.soen.hasslefree.persistence.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.annotations.Type;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped

@Entity
@Table
public class ClinicHours implements Serializable {

    @Id
    @GeneratedValue
    private long clinicHoursID;

    @OneToOne(cascade = CascadeType.ALL)
    private Clinic relatedClinic;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime startTime;

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime endTime;

    public long getClinicHoursID() {
        return clinicHoursID;
    }

    public void setClinicHoursID(long clinicHoursID) {
        this.clinicHoursID = clinicHoursID;
    }

    public Clinic getRelatedClinic() {
        return relatedClinic;
    }

    public void setRelatedClinic(Clinic relatedClinic) {
        this.relatedClinic = relatedClinic;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public void saveClinicHours() {
        ObjectDao clinicHoursDao = new ObjectDao();
        clinicHoursDao.addObject(this);
    }
    
    public static ArrayList<ClinicHours> getAllClinicHours() {
        ArrayList<ClinicHours> clinicHours;
        ObjectDao clinicHoursDao = new ObjectDao();
        clinicHours = clinicHoursDao.getAllObjects("ClinicHours");
        return clinicHours;
    }
    
     public static ClinicHours getClinicHoursByClinicId(long id) {
        ClinicHours ClinicHoursHolder = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            ClinicHoursHolder = (ClinicHours) session.createCriteria(ClinicHours.class).
                    add(Restrictions.eq("relatedClinic_clinicId", id)).
                    uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return ClinicHoursHolder;
    }

}
