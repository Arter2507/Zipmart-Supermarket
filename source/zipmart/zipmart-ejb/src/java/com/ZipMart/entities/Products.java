/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ZipMart.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByProductID", query = "SELECT p FROM Products p WHERE p.productID = :productID"),
    @NamedQuery(name = "Products.findByProductName", query = "SELECT p FROM Products p WHERE p.productName = :productName"),
    @NamedQuery(name = "Products.findByImageURL", query = "SELECT p FROM Products p WHERE p.imageURL = :imageURL"),
    @NamedQuery(name = "Products.findByUnitPrice", query = "SELECT p FROM Products p WHERE p.unitPrice = :unitPrice"),
    @NamedQuery(name = "Products.findByQuantity", query = "SELECT p FROM Products p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Products.findByQuantitySold", query = "SELECT p FROM Products p WHERE p.quantitySold = :quantitySold"),
    @NamedQuery(name = "Products.findByQuantityInStock", query = "SELECT p FROM Products p WHERE p.quantityInStock = :quantityInStock"),
    @NamedQuery(name = "Products.findByUnit", query = "SELECT p FROM Products p WHERE p.unit = :unit"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description"),
    @NamedQuery(name = "Products.findByViewCount", query = "SELECT p FROM Products p WHERE p.viewCount = :viewCount"),
    @NamedQuery(name = "Products.findByDiscount", query = "SELECT p FROM Products p WHERE p.discount = :discount")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productID")
    private Integer productID;
    @Size(max = 255)
    @Column(name = "productName")
    private String productName;
    @Size(max = 255)
    @Column(name = "imageURL")
    private String imageURL;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unitPrice")
    private BigDecimal unitPrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "quantitySold")
    private Integer quantitySold;
    @Column(name = "quantityInStock")
    private Integer quantityInStock;
    @Size(max = 50)
    @Column(name = "unit")
    private String unit;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Column(name = "viewCount")
    private Integer viewCount;
    @Column(name = "discount")
    private Integer discount;
    @JoinColumn(name = "brandID", referencedColumnName = "brandID")
    @ManyToOne(optional = false)
    private Brand brandID;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @ManyToOne(optional = false)
    private Categories categoryID;
    @JoinColumn(name = "avaliable", referencedColumnName = "statusID")
    @ManyToOne(optional = false)
    private InventoryStatus avaliable;
    @JoinColumn(name = "supplierID", referencedColumnName = "supplierID")
    @ManyToOne(optional = false)
    private Suppliers supplierID;
    @JoinColumn(name = "warehouseID", referencedColumnName = "warehouseID")
    @ManyToOne(optional = false)
    private Warehouse warehouseID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<OrdersDetails> ordersDetailsCollection;

    public Products() {
    }

    public Products(Integer productID) {
        this.productID = productID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }

    public Categories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
    }

    public InventoryStatus getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(InventoryStatus avaliable) {
        this.avaliable = avaliable;
    }

    public Suppliers getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Suppliers supplierID) {
        this.supplierID = supplierID;
    }

    public Warehouse getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Warehouse warehouseID) {
        this.warehouseID = warehouseID;
    }

    @XmlTransient
    public Collection<OrdersDetails> getOrdersDetailsCollection() {
        return ordersDetailsCollection;
    }

    public void setOrdersDetailsCollection(Collection<OrdersDetails> ordersDetailsCollection) {
        this.ordersDetailsCollection = ordersDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ZipMart.entities.Products[ productID=" + productID + " ]";
    }
    
}
