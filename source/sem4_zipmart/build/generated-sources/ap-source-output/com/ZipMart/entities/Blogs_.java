package com.ZipMart.entities;

import com.ZipMart.entities.Employees;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Blogs.class)
public class Blogs_ { 

    public static volatile SingularAttribute<Blogs, String> imageURL;
    public static volatile SingularAttribute<Blogs, Employees> employeeID;
    public static volatile SingularAttribute<Blogs, Integer> viewCount;
    public static volatile SingularAttribute<Blogs, String> title;
    public static volatile SingularAttribute<Blogs, Integer> blogID;
    public static volatile SingularAttribute<Blogs, String> content;

}