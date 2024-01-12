package com.ZipMart.entities;

import com.ZipMart.entities.Orders;
import com.ZipMart.entities.Products;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(OrdersDetails.class)
public class OrdersDetails_ { 

    public static volatile SingularAttribute<OrdersDetails, BigDecimal> unitPrice;
    public static volatile SingularAttribute<OrdersDetails, Integer> quantity;
    public static volatile SingularAttribute<OrdersDetails, String> cardName;
    public static volatile SingularAttribute<OrdersDetails, Products> productID;
    public static volatile SingularAttribute<OrdersDetails, BigDecimal> totalPrice;
    public static volatile SingularAttribute<OrdersDetails, Orders> orderID;
    public static volatile SingularAttribute<OrdersDetails, Integer> discount;
    public static volatile SingularAttribute<OrdersDetails, String> paymentMethod;
    public static volatile SingularAttribute<OrdersDetails, Integer> id;
    public static volatile SingularAttribute<OrdersDetails, String> cardNumber;

}