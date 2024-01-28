package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Orders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-28T08:17:03")
@StaticMetamodel(PaymentMethod.class)
public class PaymentMethod_ { 

    public static volatile SingularAttribute<PaymentMethod, String> createby;
    public static volatile SingularAttribute<PaymentMethod, String> modifieby;
    public static volatile SingularAttribute<PaymentMethod, String> paymentMethod;
    public static volatile SingularAttribute<PaymentMethod, String> description;
    public static volatile SingularAttribute<PaymentMethod, Date> createdate;
    public static volatile CollectionAttribute<PaymentMethod, Orders> ordersCollection;
    public static volatile SingularAttribute<PaymentMethod, Long> id;
    public static volatile SingularAttribute<PaymentMethod, Date> modifiedate;

}