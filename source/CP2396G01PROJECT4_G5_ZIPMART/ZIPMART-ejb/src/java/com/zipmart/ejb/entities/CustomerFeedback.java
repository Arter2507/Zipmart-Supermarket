/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "Customer_Feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerFeedback.findAll", query = "SELECT c FROM CustomerFeedback c"),
    @NamedQuery(name = "CustomerFeedback.findById", query = "SELECT c FROM CustomerFeedback c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerFeedback.findByCustomerID", query = "SELECT c FROM CustomerFeedback c WHERE c.customerFeedbackPK.customerID = :customerID"),
    @NamedQuery(name = "CustomerFeedback.findByFeedbackID", query = "SELECT c FROM CustomerFeedback c WHERE c.customerFeedbackPK.feedbackID = :feedbackID"),
    @NamedQuery(name = "CustomerFeedback.findByCreatedate", query = "SELECT c FROM CustomerFeedback c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "CustomerFeedback.findByModifiedate", query = "SELECT c FROM CustomerFeedback c WHERE c.modifiedate = :modifiedate"),
    @NamedQuery(name = "CustomerFeedback.findByCreateby", query = "SELECT c FROM CustomerFeedback c WHERE c.createby = :createby"),
    @NamedQuery(name = "CustomerFeedback.findByModifieby", query = "SELECT c FROM CustomerFeedback c WHERE c.modifieby = :modifieby")})
public class CustomerFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerFeedbackPK customerFeedbackPK;
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
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
    @JoinColumn(name = "feedback_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Feedbacks feedbacks;

    public CustomerFeedback() {
    }

    public CustomerFeedback(CustomerFeedbackPK customerFeedbackPK) {
        this.customerFeedbackPK = customerFeedbackPK;
    }

    public CustomerFeedback(CustomerFeedbackPK customerFeedbackPK, long id) {
        this.customerFeedbackPK = customerFeedbackPK;
        this.id = id;
    }

    public CustomerFeedback(long customerID, long feedbackID) {
        this.customerFeedbackPK = new CustomerFeedbackPK(customerID, feedbackID);
    }

    public CustomerFeedbackPK getCustomerFeedbackPK() {
        return customerFeedbackPK;
    }

    public void setCustomerFeedbackPK(CustomerFeedbackPK customerFeedbackPK) {
        this.customerFeedbackPK = customerFeedbackPK;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Feedbacks getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedbacks feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerFeedbackPK != null ? customerFeedbackPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerFeedback)) {
            return false;
        }
        CustomerFeedback other = (CustomerFeedback) object;
        if ((this.customerFeedbackPK == null && other.customerFeedbackPK != null) || (this.customerFeedbackPK != null && !this.customerFeedbackPK.equals(other.customerFeedbackPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.CustomerFeedback[ customerFeedbackPK=" + customerFeedbackPK + " ]";
    }
    
}
