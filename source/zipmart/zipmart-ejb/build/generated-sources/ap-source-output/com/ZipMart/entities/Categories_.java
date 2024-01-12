package com.ZipMart.entities;

import com.ZipMart.entities.ImportOrder;
import com.ZipMart.entities.Products;
import com.ZipMart.entities.ThresholdAdjustment;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(Categories.class)
public class Categories_ { 

    public static volatile CollectionAttribute<Categories, ThresholdAdjustment> thresholdAdjustmentCollection;
    public static volatile CollectionAttribute<Categories, Products> productsCollection;
    public static volatile SingularAttribute<Categories, Integer> newAdjustment;
    public static volatile SingularAttribute<Categories, String> description;
    public static volatile SingularAttribute<Categories, Integer> restockThreshold;
    public static volatile CollectionAttribute<Categories, ImportOrder> importOrderCollection;
    public static volatile SingularAttribute<Categories, String> categoryName;
    public static volatile SingularAttribute<Categories, Integer> categoryID;

}