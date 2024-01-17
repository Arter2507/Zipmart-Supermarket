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
public class CustomerFeedbackPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_ID")
    private long customerID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feedback_ID")
    private long feedbackID;

    public CustomerFeedbackPK() {
    }

    public CustomerFeedbackPK(long customerID, long feedbackID) {
        this.customerID = customerID;
        this.feedbackID = feedbackID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(long feedbackID) {
        this.feedbackID = feedbackID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) customerID;
        hash += (int) feedbackID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerFeedbackPK)) {
            return false;
        }
        CustomerFeedbackPK other = (CustomerFeedbackPK) object;
        if (this.customerID != other.customerID) {
            return false;
        }
        if (this.feedbackID != other.feedbackID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.CustomerFeedbackPK[ customerID=" + customerID + ", feedbackID=" + feedbackID + " ]";
    }
    
}
