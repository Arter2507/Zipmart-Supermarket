package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.BlogCategories;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-28T08:17:03")
@StaticMetamodel(Blogs.class)
public class Blogs_ { 

    public static volatile SingularAttribute<Blogs, String> createby;
    public static volatile SingularAttribute<Blogs, String> modifieby;
    public static volatile SingularAttribute<Blogs, String> sortcontent;
    public static volatile SingularAttribute<Blogs, String> imageURL;
    public static volatile SingularAttribute<Blogs, Date> createdate;
    public static volatile SingularAttribute<Blogs, BigInteger> employeeID;
    public static volatile SingularAttribute<Blogs, Long> id;
    public static volatile SingularAttribute<Blogs, String> title;
    public static volatile SingularAttribute<Blogs, Date> modifiedate;
    public static volatile SingularAttribute<Blogs, BlogCategories> category;
    public static volatile SingularAttribute<Blogs, String> content;

}