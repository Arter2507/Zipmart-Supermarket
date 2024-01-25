/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Customer_Card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerCard.findAll", query = "SELECT c FROM CustomerCard c"),
    @NamedQuery(name = "CustomerCard.findByCustomerID", query = "SELECT c FROM CustomerCard c WHERE c.customerCardPK.customerID = :customerID"),
    @NamedQuery(name = "CustomerCard.findByCardID", query = "SELECT c FROM CustomerCard c WHERE c.customerCardPK.cardID = :cardID"),
    @NamedQuery(name = "CustomerCard.findByCreatedate", query = "SELECT c FROM CustomerCard c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CustomerCard.findByModifiedate", query = "SELECT c FROM CustomerCard c WHERE c.modifiedate = :modifiedate"),
    @NamedQuery(name = "CustomerCard.findByCreateby", query = "SELECT c FROM CustomerCard c WHERE c.createby = :createby"),
    @NamedQuery(name = "CustomerCard.findByModifieby", query = "SELECT c FROM CustomerCard c WHERE c.modifieby = :modifieby")})
public class CustomerCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerCardPK customerCardPK;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "modifiedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedate;
    @Size(max = 255)
    @Column(name = "createby")
    private String createby;
    @Size(max = 255)
    @Column(name = "modifieby")
    private String modifieby;
    @JoinColumn(name = "card_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cards cards;
    @JoinColumn(name = "customer_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customers customers;

    public CustomerCard() {
    }

    public CustomerCard(CustomerCardPK customerCardPK) {
        this.customerCardPK = customerCardPK;
    }

    public CustomerCard(long customerID, long cardID) {
        this.customerCardPK = new CustomerCardPK(customerID, cardID);
    }

    public CustomerCardPK getCustomerCardPK() {
        return customerCardPK;
    }

    public void setCustomerCardPK(CustomerCardPK customerCardPK) {
        this.customerCardPK = customerCardPK;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifiedate() {
        return modifiedate;
    }

    public void setModifiedate(Date modifiedate) {
        this.modifiedate = modifiedate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getModifieby() {
        return modifieby;
    }

    public void setModifieby(String modifieby) {
        this.modifieby = modifieby;
    }

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerCardPK != null ? customerCardPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerCard)) {
            return false;
        }
        CustomerCard other = (CustomerCard) object;
        if ((this.customerCardPK == null && other.customerCardPK != null) || (this.customerCardPK != null && !this.customerCardPK.equals(other.customerCardPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.CustomerCard[ customerCardPK=" + customerCardPK + " ]";
    }
    
}
