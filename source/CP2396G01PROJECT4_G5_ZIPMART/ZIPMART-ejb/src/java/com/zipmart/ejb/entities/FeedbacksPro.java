/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Feedbacks_Pro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeedbacksPro.findAll", query = "SELECT f FROM FeedbacksPro f"),
    @NamedQuery(name = "FeedbacksPro.findById", query = "SELECT f FROM FeedbacksPro f WHERE f.id = :id"),
    @NamedQuery(name = "FeedbacksPro.findByTitle", query = "SELECT f FROM FeedbacksPro f WHERE f.title = :title"),
    @NamedQuery(name = "FeedbacksPro.findByContent", query = "SELECT f FROM FeedbacksPro f WHERE f.content = :content"),
    @NamedQuery(name = "FeedbacksPro.findByDate", query = "SELECT f FROM FeedbacksPro f WHERE f.date = :date"),
    @NamedQuery(name = "FeedbacksPro.findByRate", query = "SELECT f FROM FeedbacksPro f WHERE f.rate = :rate"),
    @NamedQuery(name = "FeedbacksPro.findByCreatedate", query = "SELECT f FROM FeedbacksPro f WHERE f.createdate = :createdate"),
    @NamedQuery(name = "FeedbacksPro.findByModifiedate", query = "SELECT f FROM FeedbacksPro f WHERE f.modifiedate = :modifiedate"),
    @NamedQuery(name = "FeedbacksPro.findByCreateby", query = "SELECT f FROM FeedbacksPro f WHERE f.createby = :createby"),
    @NamedQuery(name = "FeedbacksPro.findByModifieby", query = "SELECT f FROM FeedbacksPro f WHERE f.modifieby = :modifieby")})
public class FeedbacksPro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "rate")
    private Integer rate;
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
    @JoinColumn(name = "customerID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Customers customerID;
    @JoinColumn(name = "productID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Products productID;

    public FeedbacksPro() {
    }

    public FeedbacksPro(Long id) {
        this.id = id;
    }

    public FeedbacksPro(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
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

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbacksPro)) {
            return false;
        }
        FeedbacksPro other = (FeedbacksPro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.FeedbacksPro[ id=" + id + " ]";
    }
    
}
