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
public class CustomerGendersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_ID")
    private long customerID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender_ID")
    private long genderID;

    public CustomerGendersPK() {
    }

    public CustomerGendersPK(long customerID, long genderID) {
        this.customerID = customerID;
        this.genderID = genderID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
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
        hash += (int) customerID;
        hash += (int) genderID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerGendersPK)) {
            return false;
        }
        CustomerGendersPK other = (CustomerGendersPK) object;
        if (this.customerID != other.customerID) {
            return false;
        }
        if (this.genderID != other.genderID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.CustomerGendersPK[ customerID=" + customerID + ", genderID=" + genderID + " ]";
    }
    
}
