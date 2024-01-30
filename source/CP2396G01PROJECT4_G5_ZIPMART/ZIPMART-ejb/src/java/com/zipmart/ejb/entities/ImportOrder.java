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
@Table(name = "ImportOrder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImportOrder.findAll", query = "SELECT i FROM ImportOrder i"),
    @NamedQuery(name = "ImportOrder.findById", query = "SELECT i FROM ImportOrder i WHERE i.id = :id"),
    @NamedQuery(name = "ImportOrder.findByDateImport", query = "SELECT i FROM ImportOrder i WHERE i.dateImport = :dateImport"),
    @NamedQuery(name = "ImportOrder.findByHsCode", query = "SELECT i FROM ImportOrder i WHERE i.hsCode = :hsCode"),
    @NamedQuery(name = "ImportOrder.findByAmountDelivery", query = "SELECT i FROM ImportOrder i WHERE i.amountDelivery = :amountDelivery"),
    @NamedQuery(name = "ImportOrder.findByDescription", query = "SELECT i FROM ImportOrder i WHERE i.description = :description"),
    @NamedQuery(name = "ImportOrder.findByStatus", query = "SELECT i FROM ImportOrder i WHERE i.status = :status"),
    @NamedQuery(name = "ImportOrder.findByLeadtime", query = "SELECT i FROM ImportOrder i WHERE i.leadtime = :leadtime"),
    @NamedQuery(name = "ImportOrder.findByCreatedate", query = "SELECT i FROM ImportOrder i WHERE i.createdate = :createdate"),
    @NamedQuery(name = "ImportOrder.findByModifiedate", query = "SELECT i FROM ImportOrder i WHERE i.modifiedate = :modifiedate"),
    @NamedQuery(name = "ImportOrder.findByCreateby", query = "SELECT i FROM ImportOrder i WHERE i.createby = :createby"),
    @NamedQuery(name = "ImportOrder.findByModifieby", query = "SELECT i FROM ImportOrder i WHERE i.modifieby = :modifieby")})
public class ImportOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "dateImport")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateImport;
    @Column(name = "hsCode")
    private Integer hsCode;
    @Column(name = "amountDelivery")
    private Integer amountDelivery;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Column(name = "leadtime")
    private Integer leadtime;
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
    @JoinColumn(name = "branchID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Branch branchID;
    @JoinColumn(name = "productID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Products productID;

    public ImportOrder() {
    }

    public ImportOrder(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }

    public Integer getHsCode() {
        return hsCode;
    }

    public void setHsCode(Integer hsCode) {
        this.hsCode = hsCode;
    }

    public Integer getAmountDelivery() {
        return amountDelivery;
    }

    public void setAmountDelivery(Integer amountDelivery) {
        this.amountDelivery = amountDelivery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLeadtime() {
        return leadtime;
    }

    public void setLeadtime(Integer leadtime) {
        this.leadtime = leadtime;
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

    public Branch getBranchID() {
        return branchID;
    }

    public void setBranchID(Branch branchID) {
        this.branchID = branchID;
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
        if (!(object instanceof ImportOrder)) {
            return false;
        }
        ImportOrder other = (ImportOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.ImportOrder[ id=" + id + " ]";
    }
    
}
