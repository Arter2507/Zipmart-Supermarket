package com.zipmart.ejb.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(OrderSubtotals.class)
public class OrderSubtotals_ { 

    public static volatile SingularAttribute<OrderSubtotals, Long> orderID;
    public static volatile SingularAttribute<OrderSubtotals, BigDecimal> subtotal;

}