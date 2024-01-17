/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group5.zipmart.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TUONG
 */
@Embeddable
public class ManagerGendersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "manager_ID")
    private long managerID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender_ID")
    private long genderID;

    public ManagerGendersPK() {
    }

    public ManagerGendersPK(long managerID, long genderID) {
        this.managerID = managerID;
        this.genderID = genderID;
    }

    public long getManagerID() {
        return managerID;
    }

    public void setManagerID(long managerID) {
        this.managerID = managerID;
    }

    public long getGenderID() {
        return genderID;
    }

    public void setGenderID(long genderID) {
        this.genderID = genderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) managerID;
        hash += (int) genderID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManagerGendersPK)) {
            return false;
        }
        ManagerGendersPK other = (ManagerGendersPK) object;
        if (this.managerID != other.managerID) {
            return false;
        }
        if (this.genderID != other.genderID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.ManagerGendersPK[ managerID=" + managerID + ", genderID=" + genderID + " ]";
    }
    
}
