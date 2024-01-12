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
@Table(name = "Branch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b"),
    @NamedQuery(name = "Branch.findByBranchID", query = "SELECT b FROM Branch b WHERE b.branchID = :branchID"),
    @NamedQuery(name = "Branch.findByCompanyName", query = "SELECT b FROM Branch b WHERE b.companyName = :companyName"),
    @NamedQuery(name = "Branch.findByBranchName", query = "SELECT b FROM Branch b WHERE b.branchName = :branchName"),
    @NamedQuery(name = "Branch.findByAddress", query = "SELECT b FROM Branch b WHERE b.address = :address"),
    @NamedQuery(name = "Branch.findByCity", query = "SELECT b FROM Branch b WHERE b.city = :city"),
    @NamedQuery(name = "Branch.findByPhone", query = "SELECT b FROM Branch b WHERE b.phone = :phone"),
    @NamedQuery(name = "Branch.findByFax", query = "SELECT b FROM Branch b WHERE b.fax = :fax"),
    @NamedQuery(name = "Branch.findByPostalCode", query = "SELECT b FROM Branch b WHERE b.postalCode = :postalCode"),
    @NamedQuery(name = "Branch.findByDescription", query = "SELECT b FROM Branch b WHERE b.description = :description")})
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "branchID")
    private Integer branchID;
    @Size(max = 40)
    @Column(name = "companyName")
    private String companyName;
    @Size(max = 50)
    @Column(name = "branchName")
    private String branchName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branchID")
    private Collection<ImportOrder> importOrderCollection;

    public Branch() {
    }

    public Branch(Integer branchID) {
        this.branchID = branchID;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
    public Collection<ImportOrder> getImportOrderCollection() {
        return importOrderCollection;
    }

    public void setImportOrderCollection(Collection<ImportOrder> importOrderCollection) {
        this.importOrderCollection = importOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (branchID != null ? branchID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) object;
        if ((this.branchID == null && other.branchID != null) || (this.branchID != null && !this.branchID.equals(other.branchID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ZipMart.entities.Branch[ branchID=" + branchID + " ]";
    }
    
}
