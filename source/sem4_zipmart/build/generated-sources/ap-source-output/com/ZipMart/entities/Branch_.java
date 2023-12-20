package com.ZipMart.entities;

import com.ZipMart.entities.Import;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(Branch.class)
public class Branch_ { 

    public static volatile SingularAttribute<Branch, Integer> branchID;
    public static volatile SingularAttribute<Branch, String> address;
    public static volatile SingularAttribute<Branch, String> city;
    public static volatile SingularAttribute<Branch, String> phone;
    public static volatile SingularAttribute<Branch, String> companyName;
    public static volatile SingularAttribute<Branch, String> postalCode;
    public static volatile SingularAttribute<Branch, String> branchName;
    public static volatile SingularAttribute<Branch, String> description;
    public static volatile SingularAttribute<Branch, String> fax;
    public static volatile CollectionAttribute<Branch, Import> importCollection;

}