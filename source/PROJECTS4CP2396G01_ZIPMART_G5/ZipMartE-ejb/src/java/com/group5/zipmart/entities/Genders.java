/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group5.zipmart.entities;

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
@Table(name = "Genders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genders.findAll", query = "SELECT g FROM Genders g"),
    @NamedQuery(name = "Genders.findById", query = "SELECT g FROM Genders g WHERE g.id = :id"),
    @NamedQuery(name = "Genders.findByGenderName", query = "SELECT g FROM Genders g WHERE g.genderName = :genderName"),
    @NamedQuery(name = "Genders.findByDescription", query = "SELECT g FROM Genders g WHERE g.description = :description"),
    @NamedQuery(name = "Genders.findByCreatedate", query = "SELECT g FROM Genders g WHERE g.createdate = :createdate"),
    @NamedQuery(name = "Genders.findByModifiedate", query = "SELECT g FROM Genders g WHERE g.modifiedate = :modifiedate"),
    @NamedQuery(name = "Genders.findByCreateby", query = "SELECT g FROM Genders g WHERE g.createby = :createby"),
    @NamedQuery(name = "Genders.findByModifieby", query = "SELECT g FROM Genders g WHERE g.modifieby = :modifieby")})
public class Genders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 50)
    @Column(name = "genderName")
    private String genderName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genders")
    private Collection<ManagerGenders> managerGendersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genders")
    private Collection<EmployeeGenders> employeeGendersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genders")
    private Collection<CustomerGenders> customerGendersCollection;

    public Genders() {
    }

    public Genders(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
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
    public Collection<ManagerGenders> getManagerGendersCollection() {
        return managerGendersCollection;
    }

    public void setManagerGendersCollection(Collection<ManagerGenders> managerGendersCollection) {
        this.managerGendersCollection = managerGendersCollection;
    }

    @XmlTransient
    public Collection<EmployeeGenders> getEmployeeGendersCollection() {
        return employeeGendersCollection;
    }

    public void setEmployeeGendersCollection(Collection<EmployeeGenders> employeeGendersCollection) {
        this.employeeGendersCollection = employeeGendersCollection;
    }

    @XmlTransient
    public Collection<CustomerGenders> getCustomerGendersCollection() {
        return customerGendersCollection;
    }

    public void setCustomerGendersCollection(Collection<CustomerGenders> customerGendersCollection) {
        this.customerGendersCollection = customerGendersCollection;
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
        if (!(object instanceof Genders)) {
            return false;
        }
        Genders other = (Genders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.Genders[ id=" + id + " ]";
    }
    
}
