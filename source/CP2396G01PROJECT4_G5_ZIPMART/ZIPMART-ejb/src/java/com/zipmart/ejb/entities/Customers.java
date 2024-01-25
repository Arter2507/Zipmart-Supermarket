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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Customers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
    @NamedQuery(name = "Customers.findById", query = "SELECT c FROM Customers c WHERE c.id = :id"),
    @NamedQuery(name = "Customers.findByCustomerGender", query = "SELECT c FROM Customers c WHERE c.customerGender = :customerGender"),
    @NamedQuery(name = "Customers.findByCustomerCard", query = "SELECT c FROM Customers c WHERE c.customerCard = :customerCard"),
    @NamedQuery(name = "Customers.findByUsername", query = "SELECT c FROM Customers c WHERE c.username = :username"),
    @NamedQuery(name = "Customers.findByPassword", query = "SELECT c FROM Customers c WHERE c.password = :password"),
    @NamedQuery(name = "Customers.findBySaltPassword", query = "SELECT c FROM Customers c WHERE c.saltPassword = :saltPassword"),
    @NamedQuery(name = "Customers.findByPepperPassword", query = "SELECT c FROM Customers c WHERE c.pepperPassword = :pepperPassword"),
    @NamedQuery(name = "Customers.findByFullname", query = "SELECT c FROM Customers c WHERE c.fullname = :fullname"),
    @NamedQuery(name = "Customers.findByAddress", query = "SELECT c FROM Customers c WHERE c.address = :address"),
    @NamedQuery(name = "Customers.findByPhone", query = "SELECT c FROM Customers c WHERE c.phone = :phone"),
    @NamedQuery(name = "Customers.findByEmail", query = "SELECT c FROM Customers c WHERE c.email = :email"),
    @NamedQuery(name = "Customers.findByBirthDate", query = "SELECT c FROM Customers c WHERE c.birthDate = :birthDate"),
    @NamedQuery(name = "Customers.findByImageURL", query = "SELECT c FROM Customers c WHERE c.imageURL = :imageURL"),
    @NamedQuery(name = "Customers.findByCreatedate", query = "SELECT c FROM Customers c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "Customers.findByModifiedate", query = "SELECT c FROM Customers c WHERE c.modifiedate = :modifiedate"),
    @NamedQuery(name = "Customers.findByCreateby", query = "SELECT c FROM Customers c WHERE c.createby = :createby"),
    @NamedQuery(name = "Customers.findByModifieby", query = "SELECT c FROM Customers c WHERE c.modifieby = :modifieby"),
    @NamedQuery(name = "Customers.findByStatus", query = "SELECT c FROM Customers c WHERE c.status = :status")})
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "customer_gender")
    private Long customerGender;
    @Column(name = "customer_card")
    private BigInteger customerCard;
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
    @Size(max = 255)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 50)
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
    @Column(name = "birthDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Size(max = 255)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerID")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private Collection<CustomerCard> customerCardCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private Collection<CustomerFeedback> customerFeedbackCollection;
    @JoinColumn(name = "customer_group", referencedColumnName = "ID")
    @ManyToOne
    private Permissions customerGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private Collection<CustomerGenders> customerGendersCollection;

    @Transient
    private String active;
    
    @Transient
    private String oldPass;
    
    @Transient
    private String newPass;
    
    public Customers() {
    }

    public Customers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(Long customerGender) {
        this.customerGender = customerGender;
    }

    public BigInteger getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(BigInteger customerCard) {
        this.customerCard = customerCard;
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

    @XmlTransient
    public Collection<CustomerCard> getCustomerCardCollection() {
        return customerCardCollection;
    }

    public void setCustomerCardCollection(Collection<CustomerCard> customerCardCollection) {
        this.customerCardCollection = customerCardCollection;
    }

    @XmlTransient
    public Collection<CustomerFeedback> getCustomerFeedbackCollection() {
        return customerFeedbackCollection;
    }

    public void setCustomerFeedbackCollection(Collection<CustomerFeedback> customerFeedbackCollection) {
        this.customerFeedbackCollection = customerFeedbackCollection;
    }

    public Permissions getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(Permissions customerGroup) {
        this.customerGroup = customerGroup;
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
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.Customers[ id=" + id + " ]";
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
