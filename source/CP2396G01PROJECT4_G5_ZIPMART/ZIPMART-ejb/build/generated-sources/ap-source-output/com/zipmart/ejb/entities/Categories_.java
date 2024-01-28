package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.ImportOrder;
import com.zipmart.ejb.entities.Products;
import com.zipmart.ejb.entities.ThresholdAdjustment;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-29T03:40:49")
@StaticMetamodel(Categories.class)
public class Categories_ { 

    public static volatile CollectionAttribute<Categories, ThresholdAdjustment> thresholdAdjustmentCollection;
    public static volatile SingularAttribute<Categories, String> modifieby;
    public static volatile SingularAttribute<Categories, String> createby;
    public static volatile CollectionAttribute<Categories, Products> productsCollection;
    public static volatile SingularAttribute<Categories, Integer> newAdjustment;
    public static volatile SingularAttribute<Categories, Date> createdate;
    public static volatile SingularAttribute<Categories, String> description;
    public static volatile SingularAttribute<Categories, Long> id;
    public static volatile SingularAttribute<Categories, Integer> restockThreshold;
    public static volatile CollectionAttribute<Categories, ImportOrder> importOrderCollection;
    public static volatile SingularAttribute<Categories, Date> modifiedate;
    public static volatile SingularAttribute<Categories, String> categoryName;

}