package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.CustomerFeedback;
import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(Feedbacks.class)
public class Feedbacks_ { 

    public static volatile SingularAttribute<Feedbacks, Date> date;
    public static volatile SingularAttribute<Feedbacks, String> modifieby;
    public static volatile SingularAttribute<Feedbacks, String> createby;
    public static volatile SingularAttribute<Feedbacks, Products> productID;
    public static volatile SingularAttribute<Feedbacks, Integer> rate;
    public static volatile SingularAttribute<Feedbacks, Customers> customerID;
    public static volatile SingularAttribute<Feedbacks, Date> createdate;
    public static volatile CollectionAttribute<Feedbacks, CustomerFeedback> customerFeedbackCollection;
    public static volatile SingularAttribute<Feedbacks, Long> id;
    public static volatile SingularAttribute<Feedbacks, String> title;
    public static volatile SingularAttribute<Feedbacks, Date> modifiedate;
    public static volatile SingularAttribute<Feedbacks, String> content;

}