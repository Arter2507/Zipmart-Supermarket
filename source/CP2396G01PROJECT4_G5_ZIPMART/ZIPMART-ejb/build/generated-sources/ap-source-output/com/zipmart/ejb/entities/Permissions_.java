package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.entities.Managers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-02-02T08:47:42")
@StaticMetamodel(Permissions.class)
public class Permissions_ { 

    public static volatile CollectionAttribute<Permissions, Employees> employeesCollection;
    public static volatile SingularAttribute<Permissions, String> createby;
    public static volatile SingularAttribute<Permissions, String> modifieby;
    public static volatile SingularAttribute<Permissions, Date> createdate;
    public static volatile CollectionAttribute<Permissions, Customers> customersCollection;
    public static volatile SingularAttribute<Permissions, Long> id;
    public static volatile SingularAttribute<Permissions, Date> modifiedate;
    public static volatile CollectionAttribute<Permissions, Managers> managersCollection;
    public static volatile SingularAttribute<Permissions, String> permissionName;

}