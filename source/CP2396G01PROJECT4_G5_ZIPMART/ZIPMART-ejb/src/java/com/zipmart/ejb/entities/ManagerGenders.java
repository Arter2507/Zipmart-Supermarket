/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

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
@Table(name = "Manager_Genders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManagerGenders.findAll", query = "SELECT m FROM ManagerGenders m"),
    @NamedQuery(name = "ManagerGenders.findByManagerID", query = "SELECT m FROM ManagerGenders m WHERE m.managerGendersPK.managerID = :managerID"),
    @NamedQuery(name = "ManagerGenders.findByGenderID", query = "SELECT m FROM ManagerGenders m WHERE m.managerGendersPK.genderID = :genderID"),
    @NamedQuery(name = "ManagerGenders.findByCreatedate", query = "SELECT m FROM ManagerGenders m WHERE m.createdate = :createdate"),
    @NamedQuery(name = "ManagerGenders.findByModifiedate", query = "SELECT m FROM ManagerGenders m WHERE m.modifiedate = :modifiedate"),
    @NamedQuery(name = "ManagerGenders.findByCreateby", query = "SELECT m FROM ManagerGenders m WHERE m.createby = :createby"),
    @NamedQuery(name = "ManagerGenders.findByModifieby", query = "SELECT m FROM ManagerGenders m WHERE m.modifieby = :modifieby")})
public class ManagerGenders implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ManagerGendersPK managerGendersPK;
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
    @JoinColumn(name = "gender_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genders genders;
    @JoinColumn(name = "manager_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Managers managers;

    public ManagerGenders() {
    }

    public ManagerGenders(ManagerGendersPK managerGendersPK) {
        this.managerGendersPK = managerGendersPK;
    }

    public ManagerGenders(long managerID, long genderID) {
        this.managerGendersPK = new ManagerGendersPK(managerID, genderID);
    }

    public ManagerGendersPK getManagerGendersPK() {
        return managerGendersPK;
    }

    public void setManagerGendersPK(ManagerGendersPK managerGendersPK) {
        this.managerGendersPK = managerGendersPK;
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

    public Genders getGenders() {
        return genders;
    }

    public void setGenders(Genders genders) {
        this.genders = genders;
    }

    public Managers getManagers() {
        return managers;
    }

    public void setManagers(Managers managers) {
        this.managers = managers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (managerGendersPK != null ? managerGendersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManagerGenders)) {
            return false;
        }
        ManagerGenders other = (ManagerGenders) object;
        if ((this.managerGendersPK == null && other.managerGendersPK != null) || (this.managerGendersPK != null && !this.managerGendersPK.equals(other.managerGendersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.ManagerGenders[ managerGendersPK=" + managerGendersPK + " ]";
    }
    
}
