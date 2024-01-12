package com.ZipMart.entities;

import com.ZipMart.entities.Accounts;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-08T23:22:10")
@StaticMetamodel(Permission.class)
public class Permission_ { 

    public static volatile SingularAttribute<Permission, Integer> permissionID;
    public static volatile CollectionAttribute<Permission, Accounts> accountsCollection;
    public static volatile SingularAttribute<Permission, String> permissionName;

}