/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ZipMart.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "InventoryStatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventoryStatus.findAll", query = "SELECT i FROM InventoryStatus i"),
    @NamedQuery(name = "InventoryStatus.findByStatusID", query = "SELECT i FROM InventoryStatus i WHERE i.statusID = :statusID"),
    @NamedQuery(name = "InventoryStatus.findByStatusName", query = "SELECT i FROM InventoryStatus i WHERE i.statusName = :statusName")})
public class InventoryStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "statusID")
    private Integer statusID;
    @Size(max = 50)
    @Column(name = "statusName")
    private String statusName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avaliable")
    private Collection<Products> productsCollection;

    public InventoryStatus() {
    }

    public InventoryStatus(Integer statusID) {
        this.statusID = statusID;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @XmlTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusID != null ? statusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryStatus)) {
            return false;
        }
        InventoryStatus other = (InventoryStatus) object;
        if ((this.statusID == null && other.statusID != null) || (this.statusID != null && !this.statusID.equals(other.statusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ZipMart.entities.InventoryStatus[ statusID=" + statusID + " ]";
    }
    
}
