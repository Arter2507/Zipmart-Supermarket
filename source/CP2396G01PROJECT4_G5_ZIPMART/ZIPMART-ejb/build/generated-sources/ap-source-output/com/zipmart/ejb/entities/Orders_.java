package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.entities.PaymentMethod;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-29T03:40:49")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, String> note;
    public static volatile SingularAttribute<Orders, String> createby;
    public static volatile SingularAttribute<Orders, String> modifieby;
    public static volatile SingularAttribute<Orders, PaymentMethod> paymentMethodID;
    public static volatile SingularAttribute<Orders, Customers> customerID;
    public static volatile SingularAttribute<Orders, Date> createdate;
    public static volatile SingularAttribute<Orders, Employees> employeeID;
    public static volatile SingularAttribute<Orders, Long> id;
    public static volatile SingularAttribute<Orders, Date> modifiedate;
    public static volatile SingularAttribute<Orders, Integer> status;

}