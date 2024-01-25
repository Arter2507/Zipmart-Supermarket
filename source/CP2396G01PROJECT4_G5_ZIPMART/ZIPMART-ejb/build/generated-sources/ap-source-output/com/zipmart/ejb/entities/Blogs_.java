package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.EmployeeBlog;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(Blogs.class)
public class Blogs_ { 

    public static volatile SingularAttribute<Blogs, String> modifieby;
    public static volatile SingularAttribute<Blogs, String> createby;
    public static volatile SingularAttribute<Blogs, String> sortcontent;
    public static volatile SingularAttribute<Blogs, String> imageURL;
    public static volatile SingularAttribute<Blogs, Date> createdate;
    public static volatile SingularAttribute<Blogs, BigInteger> employeeID;
    public static volatile SingularAttribute<Blogs, Long> id;
    public static volatile SingularAttribute<Blogs, String> title;
    public static volatile SingularAttribute<Blogs, String> category;
    public static volatile SingularAttribute<Blogs, Date> modifiedate;
    public static volatile SingularAttribute<Blogs, String> content;
    public static volatile CollectionAttribute<Blogs, EmployeeBlog> employeeBlogCollection;

}