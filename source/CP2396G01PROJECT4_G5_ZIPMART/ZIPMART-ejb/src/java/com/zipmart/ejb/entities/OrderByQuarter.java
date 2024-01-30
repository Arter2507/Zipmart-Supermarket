/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "OrderByQuarter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderByQuarter.findAll", query = "SELECT o FROM OrderByQuarter o"),
    @NamedQuery(name = "OrderByQuarter.findById", query = "SELECT o FROM OrderByQuarter o WHERE o.id = :id"),
    @NamedQuery(name = "OrderByQuarter.findByCustomerID", query = "SELECT o FROM OrderByQuarter o WHERE o.customerID = :customerID"),
    @NamedQuery(name = "OrderByQuarter.findByEmployeeID", query = "SELECT o FROM OrderByQuarter o WHERE o.employeeID = :employeeID"),
    @NamedQuery(name = "OrderByQuarter.findByOrderDate", query = "SELECT o FROM OrderByQuarter o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "OrderByQuarter.findByShipDate", query = "SELECT o FROM OrderByQuarter o WHERE o.shipDate = :shipDate"),
    @NamedQuery(name = "OrderByQuarter.findByShipAddress", query = "SELECT o FROM OrderByQuarter o WHERE o.shipAddress = :shipAddress"),
    @NamedQuery(name = "OrderByQuarter.findByNote", query = "SELECT o FROM OrderByQuarter o WHERE o.note = :note"),
    @NamedQuery(name = "OrderByQuarter.findByStatus", query = "SELECT o FROM OrderByQuarter o WHERE o.status = :status"),
    @NamedQuery(name = "OrderByQuarter.findByFullName", query = "SELECT o FROM OrderByQuarter o WHERE o.fullName = :fullName"),
    @NamedQuery(name = "OrderByQuarter.findByAddress", query = "SELECT o FROM OrderByQuarter o WHERE o.address = :address"),
    @NamedQuery(name = "OrderByQuarter.findByPhone", query = "SELECT o FROM OrderByQuarter o WHERE o.phone = :phone"),
    @NamedQuery(name = "OrderByQuarter.findByEmail", query = "SELECT o FROM OrderByQuarter o WHERE o.email = :email"),
    @NamedQuery(name = "OrderByQuarter.findByPoint", query = "SELECT o FROM OrderByQuarter o WHERE o.point = :point"),
    @NamedQuery(name = "OrderByQuarter.findByRank", query = "SELECT o FROM OrderByQuarter o WHERE o.rank = :rank"),
    @NamedQuery(name = "OrderByQuarter.findByQuarter", query = "SELECT o FROM OrderByQuarter o WHERE o.quarter = :quarter")})
public class OrderByQuarter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @Id
    private long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CustomerID")
    private long customerID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EmployeeID")
    private long employeeID;
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "ShipDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipDate;
    @Size(max = 255)
    @Column(name = "ShipAddress")
    private String shipAddress;
    @Size(max = 2147483647)
    @Column(name = "Note")
    private String note;
    @Column(name = "Status")
    private Integer status;
    @Size(max = 100)
    @Column(name = "FullName")
    private String fullName;
    @Size(max = 50)
    @Column(name = "Address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "Email")
    private String email;
    @Column(name = "Point")
    private BigInteger point;
    @Size(max = 50)
    @Column(name = "Rank")
    private String rank;
    @Size(max = 2)
    @Column(name = "Quarter")
    private String quarter;

    public OrderByQuarter() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public BigInteger getPoint() {
        return point;
    }

    public void setPoint(BigInteger point) {
        this.point = point;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
    
}
