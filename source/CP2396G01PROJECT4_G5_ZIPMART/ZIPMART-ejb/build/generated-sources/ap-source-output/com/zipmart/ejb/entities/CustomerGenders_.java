package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.CustomerGendersPK;
import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.entities.Genders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-28T08:17:03")
@StaticMetamodel(CustomerGenders.class)
public class CustomerGenders_ { 

    public static volatile SingularAttribute<CustomerGenders, String> createby;
    public static volatile SingularAttribute<CustomerGenders, String> modifieby;
    public static volatile SingularAttribute<CustomerGenders, Date> createdate;
    public static volatile SingularAttribute<CustomerGenders, CustomerGendersPK> customerGendersPK;
    public static volatile SingularAttribute<CustomerGenders, Genders> genders;
    public static volatile SingularAttribute<CustomerGenders, Customers> customers;
    public static volatile SingularAttribute<CustomerGenders, Date> modifiedate;

}