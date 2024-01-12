package com.ZipMart.entities;

import com.ZipMart.entities.Accounts;
import com.ZipMart.entities.Blogs;
import com.ZipMart.entities.Orders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(Employees.class)
public class Employees_ { 

    public static volatile SingularAttribute<Employees, Accounts> accountID;
    public static volatile SingularAttribute<Employees, String> address;
    public static volatile SingularAttribute<Employees, String> notes;
    public static volatile CollectionAttribute<Employees, Blogs> blogsCollection;
    public static volatile SingularAttribute<Employees, String> phone;
    public static volatile SingularAttribute<Employees, String> imageURL;
    public static volatile SingularAttribute<Employees, Integer> employeeID;
    public static volatile SingularAttribute<Employees, String> fullname;
    public static volatile CollectionAttribute<Employees, Orders> ordersCollection;
    public static volatile SingularAttribute<Employees, Date> birthDate;
    public static volatile SingularAttribute<Employees, String> email;

}