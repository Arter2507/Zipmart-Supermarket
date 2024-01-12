package com.ZipMart.entities;

import com.ZipMart.entities.Branch;
import com.ZipMart.entities.Categories;
import com.ZipMart.entities.Suppliers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(ImportOrder.class)
public class ImportOrder_ { 

    public static volatile SingularAttribute<ImportOrder, Branch> branchID;
    public static volatile SingularAttribute<ImportOrder, String> address;
    public static volatile SingularAttribute<ImportOrder, Suppliers> supplierID;
    public static volatile SingularAttribute<ImportOrder, String> city;
    public static volatile SingularAttribute<ImportOrder, String> companyName;
    public static volatile SingularAttribute<ImportOrder, String> postalCode;
    public static volatile SingularAttribute<ImportOrder, String> description;
    public static volatile SingularAttribute<ImportOrder, Date> dateImport;
    public static volatile SingularAttribute<ImportOrder, Integer> importID;
    public static volatile SingularAttribute<ImportOrder, Integer> amountDelivery;
    public static volatile SingularAttribute<ImportOrder, Integer> hsCode;
    public static volatile SingularAttribute<ImportOrder, String> phone;
    public static volatile SingularAttribute<ImportOrder, Integer> leadtime;
    public static volatile SingularAttribute<ImportOrder, String> fax;
    public static volatile SingularAttribute<ImportOrder, String> nameProduct;
    public static volatile SingularAttribute<ImportOrder, Categories> categoryID;
    public static volatile SingularAttribute<ImportOrder, String> status;

}