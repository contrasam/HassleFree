/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soen.hasslefree.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PradeepSamuel
 */
public class Staff extends User{

    private Date joinedDate;
  

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
    
}
