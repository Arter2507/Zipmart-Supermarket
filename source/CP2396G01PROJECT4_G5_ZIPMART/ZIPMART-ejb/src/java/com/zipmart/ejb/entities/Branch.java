/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

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
@Table(name = "Branch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b"),
    @NamedQuery(name = "Branch.findById", query = "SELECT b FROM Branch b WHERE b.id = :id"),
    @NamedQuery(name = "Branch.findByCompanyName", query = "SELECT b FROM Branch b WHERE b.companyName = :companyName"),
    @NamedQuery(name = "Branch.findByBranchName", query = "SELECT b FROM Branch b WHERE b.branchName = :branchName"),
    @NamedQuery(name = "Branch.findByAddress", query = "SELECT b FROM Branch b WHERE b.address = :address"),
    @NamedQuery(name = "Branch.findByCity", query = "SELECT b FROM Branch b WHERE b.city = :city"),
    @NamedQuery(name = "Branch.findByPhone", query = "SELECT b FROM Branch b WHERE b.phone = :phone"),
    @NamedQuery(name = "Branch.findByFax", query = "SELECT b FROM Branch b WHERE b.fax = :fax"),
    @NamedQuery(name = "Branch.findByPostalCode", query = "SELECT b FROM Branch b WHERE b.postalCode = :postalCode"),
    @NamedQuery(name = "Branch.findByDescription", query = "SELECT b FROM Branch b WHERE b.description = :description"),
    @NamedQuery(name = "Branch.findByCreatedate", query = "SELECT b FROM Branch b WHERE b.createdate = :createdate"),
    @NamedQuery(name = "Branch.findByModifiedate", query = "SELECT b FROM Branch b WHERE b.modifiedate = :modifiedate"),
    @NamedQuery(name = "Branch.findByCreateby", query = "SELECT b FROM Branch b WHERE b.createby = :createby"),
    @NamedQuery(name = "Branch.findByModifieby", query = "SELECT b FROM Branch b WHERE b.modifieby = :modifieby")})
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branchID")
    private Collection<ImportOrder> importOrderCollection;

    public Branch() {
    }

    public Branch(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.Branch[ id=" + id + " ]";
    }
    
}
