package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-28T08:17:03")
@StaticMetamodel(InventoryStatus.class)
public class InventoryStatus_ { 

    public static volatile SingularAttribute<InventoryStatus, String> createby;
    public static volatile SingularAttribute<InventoryStatus, String> modifieby;
    public static volatile CollectionAttribute<InventoryStatus, Products> productsCollection;
    public static volatile SingularAttribute<InventoryStatus, String> statusName;
    public static volatile SingularAttribute<InventoryStatus, Date> createdate;
    public static volatile SingularAttribute<InventoryStatus, Long> id;
    public static volatile SingularAttribute<InventoryStatus, Date> modifiedate;

}