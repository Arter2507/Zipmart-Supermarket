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
@Table(name = "Warehouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouse.findAll", query = "SELECT w FROM Warehouse w"),
    @NamedQuery(name = "Warehouse.findByWarehouseID", query = "SELECT w FROM Warehouse w WHERE w.warehouseID = :warehouseID"),
    @NamedQuery(name = "Warehouse.findByCompanyName", query = "SELECT w FROM Warehouse w WHERE w.companyName = :companyName"),
    @NamedQuery(name = "Warehouse.findByWarehouseName", query = "SELECT w FROM Warehouse w WHERE w.warehouseName = :warehouseName"),
    @NamedQuery(name = "Warehouse.findByAddress", query = "SELECT w FROM Warehouse w WHERE w.address = :address"),
    @NamedQuery(name = "Warehouse.findByCity", query = "SELECT w FROM Warehouse w WHERE w.city = :city"),
    @NamedQuery(name = "Warehouse.findByPhone", query = "SELECT w FROM Warehouse w WHERE w.phone = :phone"),
    @NamedQuery(name = "Warehouse.findByFax", query = "SELECT w FROM Warehouse w WHERE w.fax = :fax"),
    @NamedQuery(name = "Warehouse.findByPostalCode", query = "SELECT w FROM Warehouse w WHERE w.postalCode = :postalCode"),
    @NamedQuery(name = "Warehouse.findByDescription", query = "SELECT w FROM Warehouse w WHERE w.description = :description")})
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "warehouseID")
    private Integer warehouseID;
    @Size(max = 40)
    @Column(name = "companyName")
    private String companyName;
    @Size(max = 50)
    @Column(name = "warehouseName")
    private String warehouseName;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 15)
    @Column(name = "city")
    private String city;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 24)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 24)
    @Column(name = "fax")
    private String fax;
    @Size(max = 10)
    @Column(name = "postalCode")
    private String postalCode;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseID")
    private Collection<Products> productsCollection;

    public Warehouse() {
    }

    public Warehouse(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (warehouseID != null ? warehouseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.warehouseID == null && other.warehouseID != null) || (this.warehouseID != null && !this.warehouseID.equals(other.warehouseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ZipMart.entities.Warehouse[ warehouseID=" + warehouseID + " ]";
    }
    
}
