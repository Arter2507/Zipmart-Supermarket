/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Sales Totals by Amount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesTotalsbyAmount.findAll", query = "SELECT s FROM SalesTotalsbyAmount s"),
    @NamedQuery(name = "SalesTotalsbyAmount.findBySaleAmount", query = "SELECT s FROM SalesTotalsbyAmount s WHERE s.saleAmount = :saleAmount"),
    @NamedQuery(name = "SalesTotalsbyAmount.findById", query = "SELECT s FROM SalesTotalsbyAmount s WHERE s.id = :id"),
    @NamedQuery(name = "SalesTotalsbyAmount.findByFullname", query = "SELECT s FROM SalesTotalsbyAmount s WHERE s.fullname = :fullname"),
    @NamedQuery(name = "SalesTotalsbyAmount.findByShipDate", query = "SELECT s FROM SalesTotalsbyAmount s WHERE s.shipDate = :shipDate"),
    @NamedQuery(name = "SalesTotalsbyAmount.findByQuarter", query = "SELECT s FROM SalesTotalsbyAmount s WHERE s.quarter = :quarter")})
public class SalesTotalsbyAmount implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SaleAmount")
    private BigDecimal saleAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @Id
    private long id;
    @Size(max = 100)
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "ShipDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipDate;
    @Column(name = "Quarter")
    private Integer quarter;

    public SalesTotalsbyAmount() {
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
    
}
