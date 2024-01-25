package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Brand;
import com.zipmart.ejb.entities.ImportOrder;
import com.zipmart.ejb.entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(Suppliers.class)
public class Suppliers_ { 

    public static volatile SingularAttribute<Suppliers, String> modifieby;
    public static volatile SingularAttribute<Suppliers, String> address;
    public static volatile SingularAttribute<Suppliers, String> city;
    public static volatile SingularAttribute<Suppliers, String> contactName;
    public static volatile SingularAttribute<Suppliers, String> companyName;
    public static volatile SingularAttribute<Suppliers, String> postalCode;
    public static volatile SingularAttribute<Suppliers, String> description;
    public static volatile SingularAttribute<Suppliers, Date> createdate;
    public static volatile CollectionAttribute<Suppliers, ImportOrder> importOrderCollection;
    public static volatile SingularAttribute<Suppliers, String> homePage;
    public static volatile SingularAttribute<Suppliers, String> createby;
    public static volatile SingularAttribute<Suppliers, String> contactTitle;
    public static volatile SingularAttribute<Suppliers, String> phone;
    public static volatile CollectionAttribute<Suppliers, Products> productsCollection;
    public static volatile SingularAttribute<Suppliers, Long> id;
    public static volatile SingularAttribute<Suppliers, String> fax;
    public static volatile SingularAttribute<Suppliers, Date> modifiedate;
    public static volatile CollectionAttribute<Suppliers, Brand> brandCollection;

}