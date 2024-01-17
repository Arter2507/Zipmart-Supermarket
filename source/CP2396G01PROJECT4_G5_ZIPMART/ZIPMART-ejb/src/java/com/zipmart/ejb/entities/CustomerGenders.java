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
@Table(name = "Customer_Genders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerGenders.findAll", query = "SELECT c FROM CustomerGenders c"),
    @NamedQuery(name = "CustomerGenders.findByCustomerID", query = "SELECT c FROM CustomerGenders c WHERE c.customerGendersPK.customerID = :customerID"),
    @NamedQuery(name = "CustomerGenders.findByGenderID", query = "SELECT c FROM CustomerGenders c WHERE c.customerGendersPK.genderID = :genderID"),
    @NamedQuery(name = "CustomerGenders.findByCreatedate", query = "SELECT c FROM CustomerGenders c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CustomerGenders.findByModifiedate", query = "SELECT c FROM CustomerGenders c WHERE c.modifiedate = :modifiedate"),
    @NamedQuery(name = "CustomerGenders.findByCreateby", query = "SELECT c FROM CustomerGenders c WHERE c.createby = :createby"),
    @NamedQuery(name = "CustomerGenders.findByModifieby", query = "SELECT c FROM CustomerGenders c WHERE c.modifieby = :modifieby")})
public class CustomerGenders implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerGendersPK customerGendersPK;
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
    @JoinColumn(name = "customer_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customers customers;
    @JoinColumn(name = "gender_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genders genders;

    public CustomerGenders() {
    }

    public CustomerGenders(CustomerGendersPK customerGendersPK) {
        this.customerGendersPK = customerGendersPK;
    }

    public CustomerGenders(long customerID, long genderID) {
        this.customerGendersPK = new CustomerGendersPK(customerID, genderID);
    }

    public CustomerGendersPK getCustomerGendersPK() {
        return customerGendersPK;
    }

    public void setCustomerGendersPK(CustomerGendersPK customerGendersPK) {
        this.customerGendersPK = customerGendersPK;
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

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Genders getGenders() {
        return genders;
    }

    public void setGenders(Genders genders) {
        this.genders = genders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerGendersPK != null ? customerGendersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerGenders)) {
            return false;
        }
        CustomerGenders other = (CustomerGenders) object;
        if ((this.customerGendersPK == null && other.customerGendersPK != null) || (this.customerGendersPK != null && !this.customerGendersPK.equals(other.customerGendersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.CustomerGenders[ customerGendersPK=" + customerGendersPK + " ]";
    }
    
}
