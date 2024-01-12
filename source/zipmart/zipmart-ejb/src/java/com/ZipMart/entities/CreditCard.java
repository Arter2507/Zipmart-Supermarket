/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ZipMart.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "CreditCard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditCard.findAll", query = "SELECT c FROM CreditCard c"),
    @NamedQuery(name = "CreditCard.findByCardID", query = "SELECT c FROM CreditCard c WHERE c.cardID = :cardID"),
    @NamedQuery(name = "CreditCard.findByCardName", query = "SELECT c FROM CreditCard c WHERE c.cardName = :cardName"),
    @NamedQuery(name = "CreditCard.findByCardNumber", query = "SELECT c FROM CreditCard c WHERE c.cardNumber = :cardNumber"),
    @NamedQuery(name = "CreditCard.findByValueFrom", query = "SELECT c FROM CreditCard c WHERE c.valueFrom = :valueFrom"),
    @NamedQuery(name = "CreditCard.findByExpirationDate", query = "SELECT c FROM CreditCard c WHERE c.expirationDate = :expirationDate"),
    @NamedQuery(name = "CreditCard.findByCvvNumber", query = "SELECT c FROM CreditCard c WHERE c.cvvNumber = :cvvNumber"),
    @NamedQuery(name = "CreditCard.findByCardType", query = "SELECT c FROM CreditCard c WHERE c.cardType = :cardType")})
public class CreditCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cardID")
    private Integer cardID;
    @Size(max = 255)
    @Column(name = "cardName")
    private String cardName;
    @Size(max = 16)
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "valueFrom")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valueFrom;
    @Column(name = "expirationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Column(name = "cvvNumber")
    private Integer cvvNumber;
    @Size(max = 20)
    @Column(name = "cardType")
    private String cardType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cardID")
    private Collection<Customers> customersCollection;

    public CreditCard() {
    }

    public CreditCard(Integer cardID) {
        this.cardID = cardID;
    }

    public Integer getCardID() {
        return cardID;
    }

    public void setCardID(Integer cardID) {
        this.cardID = cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(Date valueFrom) {
        this.valueFrom = valueFrom;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(Integer cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @XmlTransient
    public Collection<Customers> getCustomersCollection() {
        return customersCollection;
    }

    public void setCustomersCollection(Collection<Customers> customersCollection) {
        this.customersCollection = customersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardID != null ? cardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard other = (CreditCard) object;
        if ((this.cardID == null && other.cardID != null) || (this.cardID != null && !this.cardID.equals(other.cardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ZipMart.entities.CreditCard[ cardID=" + cardID + " ]";
    }
    
}
