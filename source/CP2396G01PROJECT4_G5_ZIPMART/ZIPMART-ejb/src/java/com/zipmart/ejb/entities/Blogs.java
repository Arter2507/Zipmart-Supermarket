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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Blogs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blogs.findAll", query = "SELECT b FROM Blogs b"),
    @NamedQuery(name = "Blogs.findById", query = "SELECT b FROM Blogs b WHERE b.id = :id"),
    @NamedQuery(name = "Blogs.findByEmployeeID", query = "SELECT b FROM Blogs b WHERE b.employeeID = :employeeID"),
    @NamedQuery(name = "Blogs.findByTitle", query = "SELECT b FROM Blogs b WHERE b.title = :title"),
    @NamedQuery(name = "Blogs.findByImageURL", query = "SELECT b FROM Blogs b WHERE b.imageURL = :imageURL"),
    @NamedQuery(name = "Blogs.findByContent", query = "SELECT b FROM Blogs b WHERE b.content = :content"),
    @NamedQuery(name = "Blogs.findBySortcontent", query = "SELECT b FROM Blogs b WHERE b.sortcontent = :sortcontent"),
    @NamedQuery(name = "Blogs.findByCreatedate", query = "SELECT b FROM Blogs b WHERE b.createdate = :createdate"),
    @NamedQuery(name = "Blogs.findByModifiedate", query = "SELECT b FROM Blogs b WHERE b.modifiedate = :modifiedate"),
    @NamedQuery(name = "Blogs.findByCreateby", query = "SELECT b FROM Blogs b WHERE b.createby = :createby"),
    @NamedQuery(name = "Blogs.findByModifieby", query = "SELECT b FROM Blogs b WHERE b.modifieby = :modifieby")})
public class Blogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "employee_ID")
    private BigInteger employeeID;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "imageURL")
    private String imageURL;
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @Size(max = 2147483647)
    @Column(name = "sortcontent")
    private String sortcontent;
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
    @JoinColumn(name = "category", referencedColumnName = "ID")
    @ManyToOne
    private BlogCategories category;

    public Blogs() {
    }

    public Blogs(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(BigInteger employeeID) {
        this.employeeID = employeeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSortcontent() {
        return sortcontent;
    }

    public void setSortcontent(String sortcontent) {
        this.sortcontent = sortcontent;
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

    public BlogCategories getCategory() {
        return category;
    }

    public void setCategory(BlogCategories category) {
        this.category = category;
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
        if (!(object instanceof Blogs)) {
            return false;
        }
        Blogs other = (Blogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.Blogs[ id=" + id + " ]";
    }
    
}
