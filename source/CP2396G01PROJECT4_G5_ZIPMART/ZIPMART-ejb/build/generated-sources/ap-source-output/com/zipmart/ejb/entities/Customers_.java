package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.CustomerCard;
import com.zipmart.ejb.entities.CustomerFeedback;
import com.zipmart.ejb.entities.CustomerGenders;
import com.zipmart.ejb.entities.Orders;
import com.zipmart.ejb.entities.Permissions;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-29T03:40:49")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile CollectionAttribute<Customers, CustomerGenders> customerGendersCollection;
    public static volatile SingularAttribute<Customers, String> modifieby;
    public static volatile SingularAttribute<Customers, String> address;
    public static volatile SingularAttribute<Customers, String> saltPassword;
    public static volatile SingularAttribute<Customers, Permissions> customerGroup;
    public static volatile SingularAttribute<Customers, Long> customerGender;
    public static volatile SingularAttribute<Customers, Date> createdate;
    public static volatile CollectionAttribute<Customers, CustomerFeedback> customerFeedbackCollection;
    public static volatile SingularAttribute<Customers, String> pepperPassword;
    public static volatile CollectionAttribute<Customers, Orders> ordersCollection;
    public static volatile CollectionAttribute<Customers, CustomerCard> customerCardCollection;
    public static volatile SingularAttribute<Customers, BigInteger> customerCard;
    public static volatile SingularAttribute<Customers, Date> birthDate;
    public static volatile SingularAttribute<Customers, String> password;
    public static volatile SingularAttribute<Customers, String> createby;
    public static volatile SingularAttribute<Customers, String> phone;
    public static volatile SingularAttribute<Customers, String> imageURL;
    public static volatile SingularAttribute<Customers, Long> id;
    public static volatile SingularAttribute<Customers, String> fullname;
    public static volatile SingularAttribute<Customers, Date> modifiedate;
    public static volatile SingularAttribute<Customers, String> email;
    public static volatile SingularAttribute<Customers, String> username;
    public static volatile SingularAttribute<Customers, Boolean> status;

}