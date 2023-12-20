package com.ZipMart.entities;

import com.ZipMart.entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Warehouse.class)
public class Warehouse_ { 

    public static volatile SingularAttribute<Warehouse, String> address;
    public static volatile SingularAttribute<Warehouse, Integer> warehouseID;
    public static volatile SingularAttribute<Warehouse, String> city;
    public static volatile SingularAttribute<Warehouse, String> phone;
    public static volatile CollectionAttribute<Warehouse, Products> productsCollection;
    public static volatile SingularAttribute<Warehouse, String> companyName;
    public static volatile SingularAttribute<Warehouse, String> postalCode;
    public static volatile SingularAttribute<Warehouse, String> description;
    public static volatile SingularAttribute<Warehouse, String> fax;
    public static volatile SingularAttribute<Warehouse, String> warehouseName;

}