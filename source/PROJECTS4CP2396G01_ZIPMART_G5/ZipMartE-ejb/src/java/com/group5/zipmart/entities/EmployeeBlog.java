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
@Table(name = "Employee_Blog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeBlog.findAll", query = "SELECT e FROM EmployeeBlog e"),
    @NamedQuery(name = "EmployeeBlog.findByEmployeeID", query = "SELECT e FROM EmployeeBlog e WHERE e.employeeBlogPK.employeeID = :employeeID"),
    @NamedQuery(name = "EmployeeBlog.findByBlogID", query = "SELECT e FROM EmployeeBlog e WHERE e.employeeBlogPK.blogID = :blogID"),
    @NamedQuery(name = "EmployeeBlog.findByCreatedate", query = "SELECT e FROM EmployeeBlog e WHERE e.createdate = :createdate"),
    @NamedQuery(name = "EmployeeBlog.findByModifiedate", query = "SELECT e FROM EmployeeBlog e WHERE e.modifiedate = :modifiedate"),
    @NamedQuery(name = "EmployeeBlog.findByCreateby", query = "SELECT e FROM EmployeeBlog e WHERE e.createby = :createby"),
    @NamedQuery(name = "EmployeeBlog.findByModifieby", query = "SELECT e FROM EmployeeBlog e WHERE e.modifieby = :modifieby")})
public class EmployeeBlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeeBlogPK employeeBlogPK;
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
    @JoinColumn(name = "blog_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Blogs blogs;
    @JoinColumn(name = "employee_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employees employees;

    public EmployeeBlog() {
    }

    public EmployeeBlog(EmployeeBlogPK employeeBlogPK) {
        this.employeeBlogPK = employeeBlogPK;
    }

    public EmployeeBlog(long employeeID, long blogID) {
        this.employeeBlogPK = new EmployeeBlogPK(employeeID, blogID);
    }

    public EmployeeBlogPK getEmployeeBlogPK() {
        return employeeBlogPK;
    }

    public void setEmployeeBlogPK(EmployeeBlogPK employeeBlogPK) {
        this.employeeBlogPK = employeeBlogPK;
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

    public Blogs getBlogs() {
        return blogs;
    }

    public void setBlogs(Blogs blogs) {
        this.blogs = blogs;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeBlogPK != null ? employeeBlogPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeBlog)) {
            return false;
        }
        EmployeeBlog other = (EmployeeBlog) object;
        if ((this.employeeBlogPK == null && other.employeeBlogPK != null) || (this.employeeBlogPK != null && !this.employeeBlogPK.equals(other.employeeBlogPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.EmployeeBlog[ employeeBlogPK=" + employeeBlogPK + " ]";
    }
    
}
