package com.ZipMart.entities;

import com.ZipMart.entities.Customers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Feedbacks.class)
public class Feedbacks_ { 

    public static volatile SingularAttribute<Feedbacks, Date> date;
    public static volatile SingularAttribute<Feedbacks, Integer> rate;
    public static volatile SingularAttribute<Feedbacks, Customers> customerID;
    public static volatile SingularAttribute<Feedbacks, Integer> feedbackID;
    public static volatile SingularAttribute<Feedbacks, String> title;
    public static volatile SingularAttribute<Feedbacks, String> content;

}