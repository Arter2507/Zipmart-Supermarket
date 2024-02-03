package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Branch;
import com.zipmart.ejb.entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-02-02T08:47:42")
@StaticMetamodel(ImportOrder.class)
public class ImportOrder_ { 

    public static volatile SingularAttribute<ImportOrder, Branch> branchID;
    public static volatile SingularAttribute<ImportOrder, String> modifieby;
    public static volatile SingularAttribute<ImportOrder, Products> productID;
    public static volatile SingularAttribute<ImportOrder, String> description;
    public static volatile SingularAttribute<ImportOrder, Date> createdate;
    public static volatile SingularAttribute<ImportOrder, Date> dateImport;
    public static volatile SingularAttribute<ImportOrder, String> createby;
    public static volatile SingularAttribute<ImportOrder, Integer> amountDelivery;
    public static volatile SingularAttribute<ImportOrder, Integer> hsCode;
    public static volatile SingularAttribute<ImportOrder, Integer> leadtime;
    public static volatile SingularAttribute<ImportOrder, Long> id;
    public static volatile SingularAttribute<ImportOrder, Date> modifiedate;
    public static volatile SingularAttribute<ImportOrder, String> status;

}