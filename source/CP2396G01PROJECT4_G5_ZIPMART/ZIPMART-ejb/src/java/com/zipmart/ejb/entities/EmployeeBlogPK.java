/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TUONG
 */
@Embeddable
public class EmployeeBlogPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "employee_ID")
    private long employeeID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "blog_ID")
    private long blogID;

    public EmployeeBlogPK() {
    }

    public EmployeeBlogPK(long employeeID, long blogID) {
        this.employeeID = employeeID;
        this.blogID = blogID;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public long getBlogID() {
        return blogID;
    }

    public void setBlogID(long blogID) {
        this.blogID = blogID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) employeeID;
        hash += (int) blogID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeBlogPK)) {
            return false;
        }
        EmployeeBlogPK other = (EmployeeBlogPK) object;
        if (this.employeeID != other.employeeID) {
            return false;
        }
        if (this.blogID != other.blogID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.EmployeeBlogPK[ employeeID=" + employeeID + ", blogID=" + blogID + " ]";
    }
    
}
