/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group5.zipmart.entities;

import java.io.Serializable;
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
@Table(name = "ImportOrder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImportOrder.findAll", query = "SELECT i FROM ImportOrder i"),
    @NamedQuery(name = "ImportOrder.findById", query = "SELECT i FROM ImportOrder i WHERE i.id = :id"),
    @NamedQuery(name = "ImportOrder.findByCompanyName", query = "SELECT i FROM ImportOrder i WHERE i.companyName = :companyName"),
    @NamedQuery(name = "ImportOrder.findByDateImport", query = "SELECT i FROM ImportOrder i WHERE i.dateImport = :dateImport"),
    @NamedQuery(name = "ImportOrder.findByHsCode", query = "SELECT i FROM ImportOrder i WHERE i.hsCode = :hsCode"),
    @NamedQuery(name = "ImportOrder.findByNameProduct", query = "SELECT i FROM ImportOrder i WHERE i.nameProduct = :nameProduct"),
    @NamedQuery(name = "ImportOrder.findByAmountDelivery", query = "SELECT i FROM ImportOrder i WHERE i.amountDelivery = :amountDelivery"),
    @NamedQuery(name = "ImportOrder.findByAddress", query = "SELECT i FROM ImportOrder i WHERE i.address = :address"),
    @NamedQuery(name = "ImportOrder.findByCity", query = "SELECT i FROM ImportOrder i WHERE i.city = :city"),
    @NamedQuery(name = "ImportOrder.findByPhone", query = "SELECT i FROM ImportOrder i WHERE i.phone = :phone"),
    @NamedQuery(name = "ImportOrder.findByFax", query = "SELECT i FROM ImportOrder i WHERE i.fax = :fax"),
    @NamedQuery(name = "ImportOrder.findByPostalCode", query = "SELECT i FROM ImportOrder i WHERE i.postalCode = :postalCode"),
    @NamedQuery(name = "ImportOrder.findByDescription", query = "SELECT i FROM ImportOrder i WHERE i.description = :description"),
    @NamedQuery(name = "ImportOrder.findByStatus", query = "SELECT i FROM ImportOrder i WHERE i.status = :status"),
    @NamedQuery(name = "ImportOrder.findByLeadtime", query = "SELECT i FROM ImportOrder i WHERE i.leadtime = :leadtime"),
    @NamedQuery(name = "ImportOrder.findByCreatedate", query = "SELECT i FROM ImportOrder i WHERE i.createdate = :createdate"),
    @NamedQuery(name = "ImportOrder.findByModifiedate", query = "SELECT i FROM ImportOrder i WHERE i.modifiedate = :modifiedate"),
    @NamedQuery(name = "ImportOrder.findByCreateby", query = "SELECT i FROM ImportOrder i WHERE i.createby = :createby"),
    @NamedQuery(name = "ImportOrder.findByModifieby", query = "SELECT i FROM ImportOrder i WHERE i.modifieby = :modifieby")})
public class ImportOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 40)
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "dateImport")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateImport;
    @Column(name = "hsCode")
    private Integer hsCode;
    @Size(max = 255)
    @Column(name = "nameProduct")
    private String nameProduct;
    @Column(name = "amountDelivery")
    private Integer amountDelivery;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 15)
    @Column(name = "city")
    private String city;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 24)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 24)
    @Column(name = "fax")
    private String fax;
    @Size(max = 10)
    @Column(name = "postalCode")
    private String postalCode;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Column(name = "leadtime")
    private Integer leadtime;
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
    @JoinColumn(name = "branchID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Branch branchID;
    @JoinColumn(name = "categoryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Categories categoryID;
    @JoinColumn(name = "supplierID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Suppliers supplierID;

    public ImportOrder() {
    }

    public ImportOrder(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }

    public Integer getHsCode() {
        return hsCode;
    }

    public void setHsCode(Integer hsCode) {
        this.hsCode = hsCode;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getAmountDelivery() {
        return amountDelivery;
    }

    public void setAmountDelivery(Integer amountDelivery) {
        this.amountDelivery = amountDelivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLeadtime() {
        return leadtime;
    }

    public void setLeadtime(Integer leadtime) {
        this.leadtime = leadtime;
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

    public Branch getBranchID() {
        return branchID;
    }

    public void setBranchID(Branch branchID) {
        this.branchID = branchID;
    }

    public Categories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
    }

    public Suppliers getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Suppliers supplierID) {
        this.supplierID = supplierID;
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
        if (!(object instanceof ImportOrder)) {
            return false;
        }
        ImportOrder other = (ImportOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.ImportOrder[ id=" + id + " ]";
    }
    
}
