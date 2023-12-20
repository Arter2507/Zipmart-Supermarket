package com.ZipMart.entities;

import com.ZipMart.entities.Brand;
import com.ZipMart.entities.Categories;
import com.ZipMart.entities.InventoryStatus;
import com.ZipMart.entities.OrdersDetails;
import com.ZipMart.entities.Suppliers;
import com.ZipMart.entities.ThresholdAdjustment;
import com.ZipMart.entities.Warehouse;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, BigDecimal> unitPrice;
    public static volatile SingularAttribute<Products, Integer> quantityInStock;
    public static volatile CollectionAttribute<Products, ThresholdAdjustment> thresholdAdjustmentCollection;
    public static volatile SingularAttribute<Products, Integer> quantity;
    public static volatile SingularAttribute<Products, Suppliers> supplierID;
    public static volatile SingularAttribute<Products, Integer> productID;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, Integer> discount;
    public static volatile SingularAttribute<Products, String> productName;
    public static volatile SingularAttribute<Products, String> unit;
    public static volatile SingularAttribute<Products, Warehouse> warehouseID;
    public static volatile SingularAttribute<Products, Integer> newAdjustment;
    public static volatile SingularAttribute<Products, InventoryStatus> avaliable;
    public static volatile SingularAttribute<Products, String> imageURL;
    public static volatile SingularAttribute<Products, Brand> brandID;
    public static volatile SingularAttribute<Products, Integer> quantitySold;
    public static volatile SingularAttribute<Products, Integer> viewCount;
    public static volatile SingularAttribute<Products, Categories> categoryID;
    public static volatile CollectionAttribute<Products, OrdersDetails> ordersDetailsCollection;

}