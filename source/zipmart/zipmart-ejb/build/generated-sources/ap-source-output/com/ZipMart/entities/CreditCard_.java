package com.ZipMart.entities;

import com.ZipMart.entities.Customers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(CreditCard.class)
public class CreditCard_ { 

    public static volatile SingularAttribute<CreditCard, Integer> cvvNumber;
    public static volatile SingularAttribute<CreditCard, String> cardName;
    public static volatile SingularAttribute<CreditCard, Integer> cardID;
    public static volatile SingularAttribute<CreditCard, String> cardType;
    public static volatile CollectionAttribute<CreditCard, Customers> customersCollection;
    public static volatile SingularAttribute<CreditCard, String> cardNumber;
    public static volatile SingularAttribute<CreditCard, Date> valueFrom;
    public static volatile SingularAttribute<CreditCard, Date> expirationDate;

}