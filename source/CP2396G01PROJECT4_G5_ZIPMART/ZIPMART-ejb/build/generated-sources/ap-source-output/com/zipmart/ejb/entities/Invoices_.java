package com.zipmart.ejb.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-30T17:34:12")
@StaticMetamodel(Invoices.class)
public class Invoices_ { 

    public static volatile SingularAttribute<Invoices, String> customerAddress;
    public static volatile SingularAttribute<Invoices, BigDecimal> unitPrice;
    public static volatile SingularAttribute<Invoices, Integer> quantity;
    public static volatile SingularAttribute<Invoices, String> notes;
    public static volatile SingularAttribute<Invoices, Long> productID;
    public static volatile SingularAttribute<Invoices, String> cardName;
    public static volatile SingularAttribute<Invoices, BigDecimal> lineTotal;
    public static volatile SingularAttribute<Invoices, Integer> discount;
    public static volatile SingularAttribute<Invoices, Integer> orderStatus;
    public static volatile SingularAttribute<Invoices, String> customerName;
    public static volatile SingularAttribute<Invoices, String> productName;
    public static volatile SingularAttribute<Invoices, BigDecimal> invoiceTotal;
    public static volatile SingularAttribute<Invoices, String> customerPhone;
    public static volatile SingularAttribute<Invoices, Long> invoiceNumber;
    public static volatile SingularAttribute<Invoices, String> shippingAddress;
    public static volatile SingularAttribute<Invoices, Short> paymentMethod;
    public static volatile SingularAttribute<Invoices, String> cardNumberlast4digits;
    public static volatile SingularAttribute<Invoices, Date> orderDate;

}