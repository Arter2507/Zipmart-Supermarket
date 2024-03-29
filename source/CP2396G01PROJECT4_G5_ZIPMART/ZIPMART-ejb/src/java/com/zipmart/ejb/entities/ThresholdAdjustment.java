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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "ThresholdAdjustment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ThresholdAdjustment.findAll", query = "SELECT t FROM ThresholdAdjustment t"),
    @NamedQuery(name = "ThresholdAdjustment.findById", query = "SELECT t FROM ThresholdAdjustment t WHERE t.id = :id"),
    @NamedQuery(name = "ThresholdAdjustment.findByReasonAdjustment", query = "SELECT t FROM ThresholdAdjustment t WHERE t.reasonAdjustment = :reasonAdjustment"),
    @NamedQuery(name = "ThresholdAdjustment.findByNewrestockThreshold", query = "SELECT t FROM ThresholdAdjustment t WHERE t.newrestockThreshold = :newrestockThreshold"),
    @NamedQuery(name = "ThresholdAdjustment.findByDateAdjusted", query = "SELECT t FROM ThresholdAdjustment t WHERE t.dateAdjusted = :dateAdjusted"),
    @NamedQuery(name = "ThresholdAdjustment.findByStatusThresholdAdjustments", query = "SELECT t FROM ThresholdAdjustment t WHERE t.statusThresholdAdjustments = :statusThresholdAdjustments"),
    @NamedQuery(name = "ThresholdAdjustment.findByCreatedate", query = "SELECT t FROM ThresholdAdjustment t WHERE t.createdate = :createdate"),
    @NamedQuery(name = "ThresholdAdjustment.findByModifiedate", query = "SELECT t FROM ThresholdAdjustment t WHERE t.modifiedate = :modifiedate"),
    @NamedQuery(name = "ThresholdAdjustment.findByCreateby", query = "SELECT t FROM ThresholdAdjustment t WHERE t.createby = :createby"),
    @NamedQuery(name = "ThresholdAdjustment.findByModifieby", query = "SELECT t FROM ThresholdAdjustment t WHERE t.modifieby = :modifieby")})
public class ThresholdAdjustment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 50)
    @Column(name = "reasonAdjustment")
    private String reasonAdjustment;
    @Column(name = "new_restockThreshold")
    private Integer newrestockThreshold;
    @Column(name = "dateAdjusted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdjusted;
    @Size(max = 50)
    @Column(name = "statusThresholdAdjustments")
    private String statusThresholdAdjustments;
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
    @JoinColumn(name = "categoryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Categories categoryID;

    public ThresholdAdjustment() {
    }

    public ThresholdAdjustment(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReasonAdjustment() {
        return reasonAdjustment;
    }

    public void setReasonAdjustment(String reasonAdjustment) {
        this.reasonAdjustment = reasonAdjustment;
    }

    public Integer getNewrestockThreshold() {
        return newrestockThreshold;
    }

    public void setNewrestockThreshold(Integer newrestockThreshold) {
        this.newrestockThreshold = newrestockThreshold;
    }

    public Date getDateAdjusted() {
        return dateAdjusted;
    }

    public void setDateAdjusted(Date dateAdjusted) {
        this.dateAdjusted = dateAdjusted;
    }

    public String getStatusThresholdAdjustments() {
        return statusThresholdAdjustments;
    }

    public void setStatusThresholdAdjustments(String statusThresholdAdjustments) {
        this.statusThresholdAdjustments = statusThresholdAdjustments;
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

    public Categories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
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
        if (!(object instanceof ThresholdAdjustment)) {
            return false;
        }
        ThresholdAdjustment other = (ThresholdAdjustment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.ThresholdAdjustment[ id=" + id + " ]";
    }
    
}
