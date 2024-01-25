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
@Table(name = "Invoices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoices.findAll", query = "SELECT i FROM Invoices i"),
    @NamedQuery(name = "Invoices.findByInvoiceNumber", query = "SELECT i FROM Invoices i WHERE i.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "Invoices.findByOrderDate", query = "SELECT i FROM Invoices i WHERE i.orderDate = :orderDate"),
    @NamedQuery(name = "Invoices.findByCustomerName", query = "SELECT i FROM Invoices i WHERE i.customerName = :customerName"),
    @NamedQuery(name = "Invoices.findByCustomerAddress", query = "SELECT i FROM Invoices i WHERE i.customerAddress = :customerAddress"),
    @NamedQuery(name = "Invoices.findByCustomerPhone", query = "SELECT i FROM Invoices i WHERE i.customerPhone = :customerPhone"),
    @NamedQuery(name = "Invoices.findByProductID", query = "SELECT i FROM Invoices i WHERE i.productID = :productID"),
    @NamedQuery(name = "Invoices.findByProductName", query = "SELECT i FROM Invoices i WHERE i.productName = :productName"),
    @NamedQuery(name = "Invoices.findByUnitPrice", query = "SELECT i FROM Invoices i WHERE i.unitPrice = :unitPrice"),
    @NamedQuery(name = "Invoices.findByQuantity", query = "SELECT i FROM Invoices i WHERE i.quantity = :quantity"),
    @NamedQuery(name = "Invoices.findByDiscount", query = "SELECT i FROM Invoices i WHERE i.discount = :discount"),
    @NamedQuery(name = "Invoices.findByLineTotal", query = "SELECT i FROM Invoices i WHERE i.lineTotal = :lineTotal"),
    @NamedQuery(name = "Invoices.findByShippingAddress", query = "SELECT i FROM Invoices i WHERE i.shippingAddress = :shippingAddress"),
    @NamedQuery(name = "Invoices.findByNotes", query = "SELECT i FROM Invoices i WHERE i.notes = :notes"),
    @NamedQuery(name = "Invoices.findByOrderStatus", query = "SELECT i FROM Invoices i WHERE i.orderStatus = :orderStatus"),
    @NamedQuery(name = "Invoices.findByPaymentMethod", query = "SELECT i FROM Invoices i WHERE i.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "Invoices.findByCardName", query = "SELECT i FROM Invoices i WHERE i.cardName = :cardName"),
    @NamedQuery(name = "Invoices.findByCardNumberlast4digits", query = "SELECT i FROM Invoices i WHERE i.cardNumberlast4digits = :cardNumberlast4digits"),
    @NamedQuery(name = "Invoices.findByInvoiceTotal", query = "SELECT i FROM Invoices i WHERE i.invoiceTotal = :invoiceTotal")})
public class Invoices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Invoice Number")
    @Id
    private long invoiceNumber;
    @Column(name = "Order Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Size(max = 255)
    @Column(name = "Customer Name")
    private String customerName;
    @Size(max = 50)
    @Column(name = "Customer Address")
    private String customerAddress;
    @Size(max = 50)
    @Column(name = "Customer Phone")
    private String customerPhone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Product ID")
    private long productID;
    @Size(max = 255)
    @Column(name = "Product Name")
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Unit Price")
    private BigDecimal unitPrice;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Discount (%)")
    private Integer discount;
    @Column(name = "Line Total")
    private BigDecimal lineTotal;
    @Size(max = 255)
    @Column(name = "Shipping Address")
    private String shippingAddress;
    @Size(max = 2147483647)
    @Column(name = "Notes")
    private String notes;
    @Column(name = "Order Status")
    private Integer orderStatus;
    @Size(max = 255)
    @Column(name = "Payment Method")
    private String paymentMethod;
    @Size(max = 255)
    @Column(name = "Card Name")
    private String cardName;
    @Size(max = 16)
    @Column(name = "Card Number (last 4 digits)")
    private String cardNumberlast4digits;
    @Column(name = "Invoice Total")
    private BigDecimal invoiceTotal;

    public Invoices() {
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getCardNumberlast4digits() {
        return cardNumberlast4digits;
    }

    public void setCardNumberlast4digits(String cardNumberlast4digits) {
        this.cardNumberlast4digits = cardNumberlast4digits;
    }

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }
    
}
