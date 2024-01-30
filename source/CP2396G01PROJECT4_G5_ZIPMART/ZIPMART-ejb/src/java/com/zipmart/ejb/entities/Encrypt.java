/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Encrypt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encrypt.findAll", query = "SELECT e FROM Encrypt e"),
    @NamedQuery(name = "Encrypt.findById", query = "SELECT e FROM Encrypt e WHERE e.id = :id"),
    @NamedQuery(name = "Encrypt.findBySalt", query = "SELECT e FROM Encrypt e WHERE e.salt = :salt"),
    @NamedQuery(name = "Encrypt.findByPepper", query = "SELECT e FROM Encrypt e WHERE e.pepper = :pepper")})
public class Encrypt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "salt")
    private String salt;
    @Size(max = 35)
    @Column(name = "pepper")
    private String pepper;

    public Encrypt() {
    }

    public Encrypt(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPepper() {
        return pepper;
    }

    public void setPepper(String pepper) {
        this.pepper = pepper;
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
        if (!(object instanceof Encrypt)) {
            return false;
        }
        Encrypt other = (Encrypt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.Encrypt[ id=" + id + " ]";
    }
    
}
