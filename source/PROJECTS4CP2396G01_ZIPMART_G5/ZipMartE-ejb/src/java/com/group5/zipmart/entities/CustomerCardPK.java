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
public class CustomerCardPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_ID")
    private long customerID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_ID")
    private long cardID;

    public CustomerCardPK() {
    }

    public CustomerCardPK(long customerID, long cardID) {
        this.customerID = customerID;
        this.cardID = cardID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) customerID;
        hash += (int) cardID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerCardPK)) {
            return false;
        }
        CustomerCardPK other = (CustomerCardPK) object;
        if (this.customerID != other.customerID) {
            return false;
        }
        if (this.cardID != other.cardID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.CustomerCardPK[ customerID=" + customerID + ", cardID=" + cardID + " ]";
    }
    
}
