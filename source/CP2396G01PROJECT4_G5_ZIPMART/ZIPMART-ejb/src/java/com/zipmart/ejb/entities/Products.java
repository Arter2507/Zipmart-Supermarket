/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
    @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id"),
    @NamedQuery(name = "Products.findByProductName", query = "SELECT p FROM Products p WHERE p.productName = :productName"),
    @NamedQuery(name = "Products.findByImageURL", query = "SELECT p FROM Products p WHERE p.imageURL = :imageURL"),
    @NamedQuery(name = "Products.findByUnitPrice", query = "SELECT p FROM Products p WHERE p.unitPrice = :unitPrice"),
    @NamedQuery(name = "Products.findByQuantity", query = "SELECT p FROM Products p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Products.findByQuantityInStock", query = "SELECT p FROM Products p WHERE p.quantityInStock = :quantityInStock"),
    @NamedQuery(name = "Products.findByUnit", query = "SELECT p FROM Products p WHERE p.unit = :unit"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description"),
    @NamedQuery(name = "Products.findBySortdescription", query = "SELECT p FROM Products p WHERE p.sortdescription = :sortdescription"),
    @NamedQuery(name = "Products.findByDiscount", query = "SELECT p FROM Products p WHERE p.discount = :discount"),
    @NamedQuery(name = "Products.findByAvaliable", query = "SELECT p FROM Products p WHERE p.avaliable = :avaliable"),
    @NamedQuery(name = "Products.findByCreatedate", query = "SELECT p FROM Products p WHERE p.createdate = :createdate"),
    @NamedQuery(name = "Products.findByModifiedate", query = "SELECT p FROM Products p WHERE p.modifiedate = :modifiedate"),
    @NamedQuery(name = "Products.findByCreateby", query = "SELECT p FROM Products p WHERE p.createby = :createby"),
    @NamedQuery(name = "Products.findByModifieby", query = "SELECT p FROM Products p WHERE p.modifieby = :modifieby"),
    @NamedQuery(name = "Products.findByManufacturedPlace", query = "SELECT p FROM Products p WHERE p.manufacturedPlace = :manufacturedPlace"),
    @NamedQuery(name = "Products.findBySku", query = "SELECT p FROM Products p WHERE p.sku = :sku"),
    @NamedQuery(name = "Products.findByStorageInstruction", query = "SELECT p FROM Products p WHERE p.storageInstruction = :storageInstruction"),
    @NamedQuery(name = "Products.findByUsageNotes", query = "SELECT p FROM Products p WHERE p.usageNotes = :usageNotes"),
    @NamedQuery(name = "Products.findByWeight", query = "SELECT p FROM Products p WHERE p.weight = :weight")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "productName")
    private String productName;
    @Size(max = 2147483647)
    @Column(name = "imageURL")
    private String imageURL;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unitPrice")
    private Double unitPrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "quantityInStock")
    private Integer quantityInStock;
    @Size(max = 50)
    @Column(name = "unit")
    private String unit;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 2147483647)
    @Column(name = "sortdescription")
    private String sortdescription;
    @Column(name = "discount")
    private Integer discount;
    @Column(name = "avaliable")
    private Boolean avaliable;
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
    @Size(max = 255)
    @Column(name = "manufactured_place")
    private String manufacturedPlace;
    @Size(max = 25)
    @Column(name = "sku")
    private String sku;
    @Size(max = 255)
    @Column(name = "storage_instruction")
    private String storageInstruction;
    @Size(max = 100)
    @Column(name = "usage_notes")
    private String usageNotes;
    @Size(max = 50)
    @Column(name = "weight")
    private String weight;
    @JoinColumn(name = "brandID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Brand brandID;
    @JoinColumn(name = "categoryID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Categories categoryID;
    @JoinColumn(name = "inventoryStatus", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private InventoryStatus inventoryStatus;
    @JoinColumn(name = "supplierID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Suppliers supplierID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<FeedbacksPro> feedbacksProCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<OrderDetails> orderDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<ImportOrder> importOrderCollection;

    @Transient
    private Double price_discout;
    
    @Transient
    private String active;

    public Products() {
    }

    public Products(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getSortdescription() {
        return sortdescription;
    }

    public void setSortdescription(String sortdescription) {
        this.sortdescription = sortdescription;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
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

    public String getManufacturedPlace() {
        return manufacturedPlace;
    }

    public void setManufacturedPlace(String manufacturedPlace) {
        this.manufacturedPlace = manufacturedPlace;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getStorageInstruction() {
        return storageInstruction;
    }

    public void setStorageInstruction(String storageInstruction) {
        this.storageInstruction = storageInstruction;
    }

    public String getUsageNotes() {
        return usageNotes;
    }

    public void setUsageNotes(String usageNotes) {
        this.usageNotes = usageNotes;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Suppliers getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Suppliers supplierID) {
        this.supplierID = supplierID;
    }

    @XmlTransient
    public Collection<FeedbacksPro> getFeedbacksProCollection() {
        return feedbacksProCollection;
    }

    public void setFeedbacksProCollection(Collection<FeedbacksPro> feedbacksProCollection) {
        this.feedbacksProCollection = feedbacksProCollection;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    @XmlTransient
    public Collection<ImportOrder> getImportOrderCollection() {
        return importOrderCollection;
    }

    public void setImportOrderCollection(Collection<ImportOrder> importOrderCollection) {
        this.importOrderCollection = importOrderCollection;
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
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.Products[ id=" + id + " ]";
    }
    
    public Double getPrice_discout() {
        return price_discout;
}

    public void setPrice_discout(Double price_discout) {
        this.price_discout = price_discout;
    }

    public String getActive() {
        return avaliable == null ? "null" : avaliable ? "Is Selling" : "Stop Selling";
    }

    public void setActive(String active) {
        this.active = active;
    }

}
