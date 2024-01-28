/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Employees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e"),
    @NamedQuery(name = "Employees.findById", query = "SELECT e FROM Employees e WHERE e.id = :id"),
    @NamedQuery(name = "Employees.findByEmployeeGender", query = "SELECT e FROM Employees e WHERE e.employeeGender = :employeeGender"),
    @NamedQuery(name = "Employees.findByUsername", query = "SELECT e FROM Employees e WHERE e.username = :username"),
    @NamedQuery(name = "Employees.findByPassword", query = "SELECT e FROM Employees e WHERE e.password = :password"),
    @NamedQuery(name = "Employees.findBySaltPassword", query = "SELECT e FROM Employees e WHERE e.saltPassword = :saltPassword"),
    @NamedQuery(name = "Employees.findByPepperPassword", query = "SELECT e FROM Employees e WHERE e.pepperPassword = :pepperPassword"),
    @NamedQuery(name = "Employees.findByFullname", query = "SELECT e FROM Employees e WHERE e.fullname = :fullname"),
    @NamedQuery(name = "Employees.findByAddress", query = "SELECT e FROM Employees e WHERE e.address = :address"),
    @NamedQuery(name = "Employees.findByPhone", query = "SELECT e FROM Employees e WHERE e.phone = :phone"),
    @NamedQuery(name = "Employees.findByEmail", query = "SELECT e FROM Employees e WHERE e.email = :email"),
    @NamedQuery(name = "Employees.findByBirthDate", query = "SELECT e FROM Employees e WHERE e.birthDate = :birthDate"),
    @NamedQuery(name = "Employees.findByNotes", query = "SELECT e FROM Employees e WHERE e.notes = :notes"),
    @NamedQuery(name = "Employees.findByImageURL", query = "SELECT e FROM Employees e WHERE e.imageURL = :imageURL"),
    @NamedQuery(name = "Employees.findByCreatedate", query = "SELECT e FROM Employees e WHERE e.createdate = :createdate"),
    @NamedQuery(name = "Employees.findByModifiedate", query = "SELECT e FROM Employees e WHERE e.modifiedate = :modifiedate"),
    @NamedQuery(name = "Employees.findByCreateby", query = "SELECT e FROM Employees e WHERE e.createby = :createby"),
    @NamedQuery(name = "Employees.findByModifieby", query = "SELECT e FROM Employees e WHERE e.modifieby = :modifieby"),
    @NamedQuery(name = "Employees.findByStatus", query = "SELECT e FROM Employees e WHERE e.status = :status")})
public class Employees implements Serializable {

    @Column(name = "employee_gender")
    private Long employeeGender;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 2147483647)
    @Column(name = "password")
    private String password;
    @Size(max = 2147483647)
    @Column(name = "salt_password")
    private String saltPassword;
    @Size(max = 16)
    @Column(name = "pepper_password")
    private String pepperPassword;
    @Size(max = 50)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "phone")
    private String phone;
    //@Pattern(regexp="[a-z][a-z0-9]+(?:[.][a-z0-9]+)*@[a-z]+(?:[.][a-z]+)?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Column(name = "birthDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Size(max = 2147483647)
    @Column(name = "notes")
    private String notes;
    @Size(max = 2147483647)
    @Column(name = "imageURL")
    private String imageURL;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "modifiedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedate;
    @Size(max = 50)
    @Column(name = "createby")
    private String createby;
    @Size(max = 50)
    @Column(name = "modifieby")
    private String modifieby;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeID")
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "employee_group", referencedColumnName = "ID")
    @ManyToOne
    private Permissions employeeGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employees")
    private Collection<EmployeeGenders> employeeGendersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employees")
    private Collection<EmployeeBlog> employeeBlogCollection;

    @Transient
    private String active;
    
    @Transient
    private String oldPass;
    
    @Transient
    private String newPass;

    public Employees() {
    }

    public Employees(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(Long employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSaltPassword() {
        return saltPassword;
    }

    public void setSaltPassword(String saltPassword) {
        this.saltPassword = saltPassword;
    }

    public String getPepperPassword() {
        return pepperPassword;
    }

    public void setPepperPassword(String pepperPassword) {
        this.pepperPassword = pepperPassword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public Permissions getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(Permissions employeeGroup) {
        this.employeeGroup = employeeGroup;
    }

    @XmlTransient
    public Collection<EmployeeGenders> getEmployeeGendersCollection() {
        return employeeGendersCollection;
    }

    public void setEmployeeGendersCollection(Collection<EmployeeGenders> employeeGendersCollection) {
        this.employeeGendersCollection = employeeGendersCollection;
    }

    @XmlTransient
    public Collection<EmployeeBlog> getEmployeeBlogCollection() {
        return employeeBlogCollection;
    }

    public void setEmployeeBlogCollection(Collection<EmployeeBlog> employeeBlogCollection) {
        this.employeeBlogCollection = employeeBlogCollection;
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
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.Employees[ id=" + id + " ]";
    }

    public String getActive() {
        return status == null ? "null" : status ? "Enabled" : "Disabled";
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

}
