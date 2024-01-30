package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Products;
import com.zipmart.ejb.entities.Suppliers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-30T17:34:12")
@StaticMetamodel(Brand.class)
public class Brand_ { 

    public static volatile SingularAttribute<Brand, String> brandName;
    public static volatile SingularAttribute<Brand, String> createby;
    public static volatile SingularAttribute<Brand, String> modifieby;
    public static volatile SingularAttribute<Brand, String> address;
    public static volatile SingularAttribute<Brand, Suppliers> supplierID;
    public static volatile CollectionAttribute<Brand, Products> productsCollection;
    public static volatile SingularAttribute<Brand, Date> createdate;
    public static volatile SingularAttribute<Brand, Long> id;
    public static volatile SingularAttribute<Brand, Date> modifiedate;

}