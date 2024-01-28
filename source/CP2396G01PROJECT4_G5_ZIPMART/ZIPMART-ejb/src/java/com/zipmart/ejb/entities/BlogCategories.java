/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "blog_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BlogCategories.findAll", query = "SELECT b FROM BlogCategories b"),
    @NamedQuery(name = "BlogCategories.findById", query = "SELECT b FROM BlogCategories b WHERE b.id = :id"),
    @NamedQuery(name = "BlogCategories.findByName", query = "SELECT b FROM BlogCategories b WHERE b.name = :name"),
    @NamedQuery(name = "BlogCategories.findByDescription", query = "SELECT b FROM BlogCategories b WHERE b.description = :description"),
    @NamedQuery(name = "BlogCategories.findByCreatedate", query = "SELECT b FROM BlogCategories b WHERE b.createdate = :createdate"),
    @NamedQuery(name = "BlogCategories.findByModifiedate", query = "SELECT b FROM BlogCategories b WHERE b.modifiedate = :modifiedate"),
    @NamedQuery(name = "BlogCategories.findByCreateby", query = "SELECT b FROM BlogCategories b WHERE b.createby = :createby"),
    @NamedQuery(name = "BlogCategories.findByModifieby", query = "SELECT b FROM BlogCategories b WHERE b.modifieby = :modifieby")})
public class BlogCategories implements Serializable {

    @OneToMany(mappedBy = "category")
    private Collection<Blogs> blogsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 75)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "modifiedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedate;
    @Size(max = 80)
    @Column(name = "createby")
    private String createby;
    @Size(max = 80)
    @Column(name = "modifieby")
    private String modifieby;

    public BlogCategories() {
    }

    public BlogCategories(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlogCategories)) {
            return false;
        }
        BlogCategories other = (BlogCategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.BlogCategories[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Blogs> getBlogsCollection() {
        return blogsCollection;
    }

    public void setBlogsCollection(Collection<Blogs> blogsCollection) {
        this.blogsCollection = blogsCollection;
    }
    
}
