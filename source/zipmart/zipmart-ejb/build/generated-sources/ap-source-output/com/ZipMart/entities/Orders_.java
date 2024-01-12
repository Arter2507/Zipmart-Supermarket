package com.ZipMart.entities;

import com.ZipMart.entities.Customers;
import com.ZipMart.entities.Employees;
import com.ZipMart.entities.OrdersDetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, String> note;
    public static volatile SingularAttribute<Orders, Integer> orderID;
    public static volatile SingularAttribute<Orders, Customers> customerID;
    public static volatile SingularAttribute<Orders, Employees> employeeID;
    public static volatile SingularAttribute<Orders, Date> shipDate;
    public static volatile SingularAttribute<Orders, Date> orderDate;
    public static volatile SingularAttribute<Orders, String> shipAddress;
    public static volatile SingularAttribute<Orders, String> status;
    public static volatile CollectionAttribute<Orders, OrdersDetails> ordersDetailsCollection;

}