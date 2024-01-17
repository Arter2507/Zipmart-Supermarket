/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

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
public class EmployeeGendersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "employee_ID")
    private long employeeID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender_ID")
    private long genderID;

    public EmployeeGendersPK() {
    }

    public EmployeeGendersPK(long employeeID, long genderID) {
        this.employeeID = employeeID;
        this.genderID = genderID;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
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
        hash += (int) employeeID;
        hash += (int) genderID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeGendersPK)) {
            return false;
        }
        EmployeeGendersPK other = (EmployeeGendersPK) object;
        if (this.employeeID != other.employeeID) {
            return false;
        }
        if (this.genderID != other.genderID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.EmployeeGendersPK[ employeeID=" + employeeID + ", genderID=" + genderID + " ]";
    }
    
}
