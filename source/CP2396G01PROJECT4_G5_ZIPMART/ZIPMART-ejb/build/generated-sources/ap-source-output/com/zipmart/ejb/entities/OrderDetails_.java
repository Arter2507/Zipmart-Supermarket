package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.OrderDetailsPK;
import com.zipmart.ejb.entities.Orders;
import com.zipmart.ejb.entities.Products;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-28T08:17:03")
@StaticMetamodel(OrderDetails.class)
public class OrderDetails_ { 

    public static volatile SingularAttribute<OrderDetails, BigDecimal> unitPrice;
    public static volatile SingularAttribute<OrderDetails, OrderDetailsPK> orderDetailsPK;
    public static volatile SingularAttribute<OrderDetails, Integer> quantity;
    public static volatile SingularAttribute<OrderDetails, BigDecimal> totalPrice;
    public static volatile SingularAttribute<OrderDetails, Integer> discount;
    public static volatile SingularAttribute<OrderDetails, String> paymentMethod;
    public static volatile SingularAttribute<OrderDetails, Orders> orders;
    public static volatile SingularAttribute<OrderDetails, Date> shipDate;
    public static volatile SingularAttribute<OrderDetails, Date> orderDate;
    public static volatile SingularAttribute<OrderDetails, String> shipAddress;
    public static volatile SingularAttribute<OrderDetails, Products> products;

}