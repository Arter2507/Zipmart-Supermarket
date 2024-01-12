package com.ZipMart.entities;

import com.ZipMart.entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(InventoryStatus.class)
public class InventoryStatus_ { 

    public static volatile SingularAttribute<InventoryStatus, Integer> statusID;
    public static volatile CollectionAttribute<InventoryStatus, Products> productsCollection;
    public static volatile SingularAttribute<InventoryStatus, String> statusName;

}