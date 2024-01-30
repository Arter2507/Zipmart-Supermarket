package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Orders;
import com.zipmart.ejb.entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-30T17:34:12")
@StaticMetamodel(OrderDetails.class)
public class OrderDetails_ { 

    public static volatile SingularAttribute<OrderDetails, Double> unitPrice;
    public static volatile SingularAttribute<OrderDetails, Integer> quantity;
    public static volatile SingularAttribute<OrderDetails, Products> productID;
    public static volatile SingularAttribute<OrderDetails, Double> totalPrice;
    public static volatile SingularAttribute<OrderDetails, Orders> orderID;
    public static volatile SingularAttribute<OrderDetails, Integer> discount;
    public static volatile SingularAttribute<OrderDetails, Long> id;
    public static volatile SingularAttribute<OrderDetails, Date> shipDate;
    public static volatile SingularAttribute<OrderDetails, Date> orderDate;
    public static volatile SingularAttribute<OrderDetails, String> shipAddress;

}