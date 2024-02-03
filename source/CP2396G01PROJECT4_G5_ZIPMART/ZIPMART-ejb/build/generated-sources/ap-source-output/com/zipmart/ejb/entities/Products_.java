package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Brand;
import com.zipmart.ejb.entities.Categories;
import com.zipmart.ejb.entities.FeedbacksPro;
import com.zipmart.ejb.entities.ImportOrder;
import com.zipmart.ejb.entities.InventoryStatus;
import com.zipmart.ejb.entities.OrderDetails;
import com.zipmart.ejb.entities.Suppliers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-02-02T08:47:42")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile CollectionAttribute<Products, FeedbacksPro> feedbacksProCollection;
    public static volatile CollectionAttribute<Products, OrderDetails> orderDetailsCollection;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, Integer> discount;
    public static volatile SingularAttribute<Products, Date> createdate;
    public static volatile SingularAttribute<Products, String> productName;
    public static volatile SingularAttribute<Products, String> createby;
    public static volatile SingularAttribute<Products, String> sortdescription;
    public static volatile SingularAttribute<Products, Boolean> avaliable;
    public static volatile SingularAttribute<Products, String> imageURL;
    public static volatile SingularAttribute<Products, Brand> brandID;
    public static volatile SingularAttribute<Products, Long> id;
    public static volatile SingularAttribute<Products, Date> modifiedate;
    public static volatile SingularAttribute<Products, String> sku;
    public static volatile SingularAttribute<Products, String> manufacturedPlace;
    public static volatile SingularAttribute<Products, Categories> categoryID;
    public static volatile SingularAttribute<Products, Double> unitPrice;
    public static volatile SingularAttribute<Products, Integer> quantityInStock;
    public static volatile SingularAttribute<Products, String> modifieby;
    public static volatile SingularAttribute<Products, Integer> quantity;
    public static volatile SingularAttribute<Products, Suppliers> supplierID;
    public static volatile SingularAttribute<Products, InventoryStatus> inventoryStatus;
    public static volatile SingularAttribute<Products, String> weight;
    public static volatile CollectionAttribute<Products, ImportOrder> importOrderCollection;
    public static volatile SingularAttribute<Products, String> unit;
    public static volatile SingularAttribute<Products, String> usageNotes;
    public static volatile SingularAttribute<Products, String> storageInstruction;

}