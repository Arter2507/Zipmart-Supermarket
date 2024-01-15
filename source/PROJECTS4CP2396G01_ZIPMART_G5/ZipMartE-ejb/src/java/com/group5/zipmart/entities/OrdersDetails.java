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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "OrdersDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersDetails.findAll", query = "SELECT o FROM OrdersDetails o"),
    @NamedQuery(name = "OrdersDetails.findById", query = "SELECT o FROM OrdersDetails o WHERE o.id = :id"),
    @NamedQuery(name = "OrdersDetails.findByUnitPrice", query = "SELECT o FROM OrdersDetails o WHERE o.unitPrice = :unitPrice"),
    @NamedQuery(name = "OrdersDetails.findByQuantity", query = "SELECT o FROM OrdersDetails o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrdersDetails.findByDiscount", query = "SELECT o FROM OrdersDetails o WHERE o.discount = :discount"),
    @NamedQuery(name = "OrdersDetails.findByTotalPrice", query = "SELECT o FROM OrdersDetails o WHERE o.totalPrice = :totalPrice"),
    @NamedQuery(name = "OrdersDetails.findByPaymentMethod", query = "SELECT o FROM OrdersDetails o WHERE o.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "OrdersDetails.findByCardName", query = "SELECT o FROM OrdersDetails o WHERE o.cardName = :cardName"),
    @NamedQuery(name = "OrdersDetails.findByCardNumber", query = "SELECT o FROM OrdersDetails o WHERE o.cardNumber = :cardNumber")})
public class OrdersDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unitPrice")
    private BigDecimal unitPrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "discount")
    private Integer discount;
    @Column(name = "totalPrice")
    private BigDecimal totalPrice;
    @Size(max = 255)
    @Column(name = "paymentMethod")
    private String paymentMethod;
    @Size(max = 255)
    @Column(name = "cardName")
    private String cardName;
    @Size(max = 16)
    @Column(name = "cardNumber")
    private String cardNumber;
    @JoinColumn(name = "orderID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Orders orderID;
    @JoinColumn(name = "productID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Products productID;

    public OrdersDetails() {
    }

    public OrdersDetails(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Orders getOrderID() {
        return orderID;
    }

    public void setOrderID(Orders orderID) {
        this.orderID = orderID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
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
        if (!(object instanceof OrdersDetails)) {
            return false;
        }
        OrdersDetails other = (OrdersDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group5.zipmart.entities.OrdersDetails[ id=" + id + " ]";
    }
    
}
