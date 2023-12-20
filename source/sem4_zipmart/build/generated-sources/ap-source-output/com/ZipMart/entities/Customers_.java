package com.ZipMart.entities;

import com.ZipMart.entities.Accounts;
import com.ZipMart.entities.BlogFeedBacks;
import com.ZipMart.entities.CreditCard;
import com.ZipMart.entities.Feedbacks;
import com.ZipMart.entities.Orders;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, String> address;
    public static volatile SingularAttribute<Customers, String> cardName;
    public static volatile CollectionAttribute<Customers, Orders> ordersCollection;
    public static volatile CollectionAttribute<Customers, Feedbacks> feedbacksCollection;
    public static volatile SingularAttribute<Customers, Date> birthDate;
    public static volatile SingularAttribute<Customers, BigInteger> point;
    public static volatile CollectionAttribute<Customers, BlogFeedBacks> blogFeedBacksCollection;
    public static volatile SingularAttribute<Customers, Accounts> accountID;
    public static volatile SingularAttribute<Customers, String> phone;
    public static volatile SingularAttribute<Customers, String> imageURL;
    public static volatile SingularAttribute<Customers, CreditCard> cardID;
    public static volatile SingularAttribute<Customers, Integer> customerID;
    public static volatile SingularAttribute<Customers, String> rank;
    public static volatile SingularAttribute<Customers, String> fullname;
    public static volatile SingularAttribute<Customers, String> email;
    public static volatile SingularAttribute<Customers, String> cardNumber;

}