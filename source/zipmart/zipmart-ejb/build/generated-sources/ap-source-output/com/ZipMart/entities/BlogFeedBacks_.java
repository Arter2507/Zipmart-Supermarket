package com.ZipMart.entities;

import com.ZipMart.entities.Customers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(BlogFeedBacks.class)
public class BlogFeedBacks_ { 

    public static volatile SingularAttribute<BlogFeedBacks, Date> date;
    public static volatile SingularAttribute<BlogFeedBacks, Customers> customerID;
    public static volatile SingularAttribute<BlogFeedBacks, Integer> feedbackID;
    public static volatile SingularAttribute<BlogFeedBacks, String> title;
    public static volatile SingularAttribute<BlogFeedBacks, String> content;

}