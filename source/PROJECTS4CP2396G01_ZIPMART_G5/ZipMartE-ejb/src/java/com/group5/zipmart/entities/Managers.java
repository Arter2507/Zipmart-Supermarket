/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group5.zipmart.entities;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "Managers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Managers.findAll", query = "SELECT m FROM Managers m"),
    @NamedQuery(name = "Managers.findById", query = "SELECT m FROM Managers m WHERE m.id = :id"),
    @NamedQuery(name = "Managers.findByManagerGender", query = "SELECT m FROM Managers m WHERE m.managerGender = :managerGender"),
    @NamedQuery(name = "Managers.findByUsername", query = "SELECT m FROM Managers m WHERE m.username = :username"),
    @NamedQuery(name = "Managers.findByFullname", query = "SELECT m FROM Managers m WHERE m.fullname = :fullname"),
    @NamedQuery(name = "Managers.findByAddress", query = "SELECT m FROM Managers m WHERE m.address = :address"),
    @NamedQuery(name = "Managers.findByPhone", query = "SELECT m FROM Managers m WHERE m.phone = :phone"),
    @NamedQuery(name = "Managers.findByEmail", query = "SELECT m FROM Managers m WHERE m.email = :email"),
    @NamedQuery(name = "Managers.findByImageURL", query = "SELECT m FROM Managers m WHERE m.imageURL = :imageURL"),
    @NamedQuery(name = "Managers.findByCreatedate", query = "SELECT m FROM Managers m WHERE m.createdate = :createdate"),
    @NamedQuery(name = "Managers.findByModifiedate", query = "SELECT m FROM Managers m WHERE m.modifiedate = :modifiedate"),
    @NamedQuery(name = "Managers.findByCreateby", query = "SELECT m FROM Managers m WHERE m.createby = :createby"),
    @NamedQuery(name = "Managers.findByModifieby", query = "SELECT m FROM Managers m WHERE m.modifieby = :modifieby"),
    @NamedQuery(name = "Managers.findByStatus", query = "SELECT m FROM Managers m WHERE m.status = :status")})
public class Managers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "manager_gender")
    private BigInteger managerGender;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Lob
    @Column(name = "password")
    private byte[] password;
    @Lob
    @Column(name = "salt_password")
    private byte[] saltPassword;
    @Lob
    @Column(name = "pepper_password")
    private byte[] pepperPassword;
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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "managers")
    private Collection<ManagerGenders> managerGendersCollection;
    @JoinColumn(name = "manager_group", referencedColumnName = "ID")
    @ManyToOne
    private Permissions managerGroup;

    public Managers() {
    }

    public Managers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getManagerGender() {
        return managerGender;
    }

    public void setManagerGender(BigInteger managerGender) {
        this.managerGender = managerGender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getSaltPassword() {
        return saltPassword;
    }

    public void setSaltPassword(byte[] saltPassword) {
        this.saltPassword = saltPassword;
    }

    public byte[] getPepperPassword() {
        return pepperPassword;
    }

    public void setPepperPassword(byte[] pepperPassword) {
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
    public Collection<ManagerGenders> getManagerGendersCollection() {
        return managerGendersCollection;
    }

    public void setManagerGendersCollection(Collection<ManagerGenders> managerGendersCollection) {
        this.managerGendersCollection = managerGendersCollection;
    }

    public Permissions getManagerGroup() {
        return managerGroup;
    }

    public void setManagerGroup(Permissions managerGroup) {
        this.managerGroup = managerGroup;
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
        if (!(object instanceof Managers)) {
            return false;
        }
        Managers other = (Managers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.Managers[ id=" + id + " ]";
    }
    
}
