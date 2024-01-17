/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group5.zipmart.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Order Subtotals")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderSubtotals.findAll", query = "SELECT o FROM OrderSubtotals o"),
    @NamedQuery(name = "OrderSubtotals.findByOrderID", query = "SELECT o FROM OrderSubtotals o WHERE o.orderID = :orderID"),
    @NamedQuery(name = "OrderSubtotals.findBySubtotal", query = "SELECT o FROM OrderSubtotals o WHERE o.subtotal = :subtotal")})
public class OrderSubtotals implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderID")
    @Id
    private long orderID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Subtotal")
    private BigDecimal subtotal;

    public OrderSubtotals() {
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
}
