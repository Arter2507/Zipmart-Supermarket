package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-30T17:34:12")
@StaticMetamodel(InventoryStatus.class)
public class InventoryStatus_ { 

    public static volatile CollectionAttribute<InventoryStatus, Products> productsCollection;
    public static volatile SingularAttribute<InventoryStatus, String> statusName;
    public static volatile SingularAttribute<InventoryStatus, Long> id;

}