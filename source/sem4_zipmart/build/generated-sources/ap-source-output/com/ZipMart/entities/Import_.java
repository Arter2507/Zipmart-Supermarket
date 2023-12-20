package com.ZipMart.entities;

import com.ZipMart.entities.Branch;
import com.ZipMart.entities.Brand;
import com.ZipMart.entities.Categories;
import com.ZipMart.entities.Suppliers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Import.class)
public class Import_ { 

    public static volatile SingularAttribute<Import, Branch> branchID;
    public static volatile SingularAttribute<Import, String> address;
    public static volatile SingularAttribute<Import, Suppliers> supplierID;
    public static volatile SingularAttribute<Import, String> city;
    public static volatile SingularAttribute<Import, String> companyName;
    public static volatile SingularAttribute<Import, String> postalCode;
    public static volatile SingularAttribute<Import, String> description;
    public static volatile SingularAttribute<Import, Date> dateImport;
    public static volatile SingularAttribute<Import, Integer> importID;
    public static volatile SingularAttribute<Import, Integer> amountDelivery;
    public static volatile SingularAttribute<Import, Integer> hsCode;
    public static volatile SingularAttribute<Import, String> phone;
    public static volatile SingularAttribute<Import, Brand> brandID;
    public static volatile SingularAttribute<Import, Integer> leadtime;
    public static volatile SingularAttribute<Import, String> fax;
    public static volatile SingularAttribute<Import, String> nameProduct;
    public static volatile SingularAttribute<Import, Categories> categoryID;
    public static volatile SingularAttribute<Import, String> status;

}