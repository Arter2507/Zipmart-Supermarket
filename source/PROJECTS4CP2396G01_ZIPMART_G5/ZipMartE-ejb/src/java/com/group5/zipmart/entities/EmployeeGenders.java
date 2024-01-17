/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group5.zipmart.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "Employee_Genders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeGenders.findAll", query = "SELECT e FROM EmployeeGenders e"),
    @NamedQuery(name = "EmployeeGenders.findByEmployeeID", query = "SELECT e FROM EmployeeGenders e WHERE e.employeeGendersPK.employeeID = :employeeID"),
    @NamedQuery(name = "EmployeeGenders.findByGenderID", query = "SELECT e FROM EmployeeGenders e WHERE e.employeeGendersPK.genderID = :genderID"),
    @NamedQuery(name = "EmployeeGenders.findByCreatedate", query = "SELECT e FROM EmployeeGenders e WHERE e.createdate = :createdate"),
    @NamedQuery(name = "EmployeeGenders.findByModifiedate", query = "SELECT e FROM EmployeeGenders e WHERE e.modifiedate = :modifiedate"),
    @NamedQuery(name = "EmployeeGenders.findByCreateby", query = "SELECT e FROM EmployeeGenders e WHERE e.createby = :createby"),
    @NamedQuery(name = "EmployeeGenders.findByModifieby", query = "SELECT e FROM EmployeeGenders e WHERE e.modifieby = :modifieby")})
public class EmployeeGenders implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeeGendersPK employeeGendersPK;
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
    @JoinColumn(name = "employee_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employees employees;
    @JoinColumn(name = "gender_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genders genders;

    public EmployeeGenders() {
    }

    public EmployeeGenders(EmployeeGendersPK employeeGendersPK) {
        this.employeeGendersPK = employeeGendersPK;
    }

    public EmployeeGenders(long employeeID, long genderID) {
        this.employeeGendersPK = new EmployeeGendersPK(employeeID, genderID);
    }

    public EmployeeGendersPK getEmployeeGendersPK() {
        return employeeGendersPK;
    }

    public void setEmployeeGendersPK(EmployeeGendersPK employeeGendersPK) {
        this.employeeGendersPK = employeeGendersPK;
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

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public Genders getGenders() {
        return genders;
    }

    public void setGenders(Genders genders) {
        this.genders = genders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeGendersPK != null ? employeeGendersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeGenders)) {
            return false;
        }
        EmployeeGenders other = (EmployeeGenders) object;
        if ((this.employeeGendersPK == null && other.employeeGendersPK != null) || (this.employeeGendersPK != null && !this.employeeGendersPK.equals(other.employeeGendersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.EmployeeGenders[ employeeGendersPK=" + employeeGendersPK + " ]";
    }
    
}
