package com.ZipMart.entities;

import com.ZipMart.entities.Products;
import com.ZipMart.entities.Suppliers;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(Brand.class)
public class Brand_ { 

    public static volatile SingularAttribute<Brand, String> brandName;
    public static volatile SingularAttribute<Brand, String> address;
    public static volatile SingularAttribute<Brand, Suppliers> supplierID;
    public static volatile CollectionAttribute<Brand, Products> productsCollection;
    public static volatile SingularAttribute<Brand, Integer> brandID;

}