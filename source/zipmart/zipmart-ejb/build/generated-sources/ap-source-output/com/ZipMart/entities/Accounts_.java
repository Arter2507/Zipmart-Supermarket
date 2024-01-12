package com.ZipMart.entities;

import com.ZipMart.entities.Customers;
import com.ZipMart.entities.Employees;
import com.ZipMart.entities.Managers;
import com.ZipMart.entities.Permission;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(Accounts.class)
public class Accounts_ { 

    public static volatile CollectionAttribute<Accounts, Employees> employeesCollection;
    public static volatile SingularAttribute<Accounts, Integer> accountID;
    public static volatile SingularAttribute<Accounts, Permission> permissionID;
    public static volatile SingularAttribute<Accounts, String> password;
    public static volatile SingularAttribute<Accounts, String> description;
    public static volatile CollectionAttribute<Accounts, Customers> customersCollection;
    public static volatile CollectionAttribute<Accounts, Managers> managersCollection;
    public static volatile SingularAttribute<Accounts, String> username;

}