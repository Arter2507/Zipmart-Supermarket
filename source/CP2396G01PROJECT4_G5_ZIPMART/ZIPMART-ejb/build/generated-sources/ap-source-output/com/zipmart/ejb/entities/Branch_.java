package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.ImportOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-02-02T08:47:42")
@StaticMetamodel(Branch.class)
public class Branch_ { 

    public static volatile SingularAttribute<Branch, String> modifieby;
    public static volatile SingularAttribute<Branch, String> address;
    public static volatile SingularAttribute<Branch, String> city;
    public static volatile SingularAttribute<Branch, String> companyName;
    public static volatile SingularAttribute<Branch, String> postalCode;
    public static volatile SingularAttribute<Branch, String> branchName;
    public static volatile SingularAttribute<Branch, String> description;
    public static volatile SingularAttribute<Branch, Date> createdate;
    public static volatile CollectionAttribute<Branch, ImportOrder> importOrderCollection;
    public static volatile SingularAttribute<Branch, String> createby;
    public static volatile SingularAttribute<Branch, String> phone;
    public static volatile SingularAttribute<Branch, Long> id;
    public static volatile SingularAttribute<Branch, String> fax;
    public static volatile SingularAttribute<Branch, Date> modifiedate;

}