package com.ZipMart.entities;

import com.ZipMart.entities.Brand;
import com.ZipMart.entities.Import;
import com.ZipMart.entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Suppliers.class)
public class Suppliers_ { 

    public static volatile SingularAttribute<Suppliers, Integer> supplierID;
    public static volatile SingularAttribute<Suppliers, String> address;
    public static volatile SingularAttribute<Suppliers, String> city;
    public static volatile SingularAttribute<Suppliers, String> contactName;
    public static volatile SingularAttribute<Suppliers, String> companyName;
    public static volatile SingularAttribute<Suppliers, String> postalCode;
    public static volatile SingularAttribute<Suppliers, String> description;
    public static volatile SingularAttribute<Suppliers, String> homePage;
    public static volatile SingularAttribute<Suppliers, String> contactTitle;
    public static volatile SingularAttribute<Suppliers, String> phone;
    public static volatile CollectionAttribute<Suppliers, Products> productsCollection;
    public static volatile SingularAttribute<Suppliers, String> fax;
    public static volatile CollectionAttribute<Suppliers, Import> importCollection;
    public static volatile CollectionAttribute<Suppliers, Brand> brandCollection;

}