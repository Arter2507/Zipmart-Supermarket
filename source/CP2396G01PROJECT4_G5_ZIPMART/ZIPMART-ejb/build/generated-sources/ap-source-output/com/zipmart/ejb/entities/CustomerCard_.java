package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Cards;
import com.zipmart.ejb.entities.CustomerCardPK;
import com.zipmart.ejb.entities.Customers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-28T08:17:03")
@StaticMetamodel(CustomerCard.class)
public class CustomerCard_ { 

    public static volatile SingularAttribute<CustomerCard, String> createby;
    public static volatile SingularAttribute<CustomerCard, String> modifieby;
    public static volatile SingularAttribute<CustomerCard, Cards> cards;
    public static volatile SingularAttribute<CustomerCard, CustomerCardPK> customerCardPK;
    public static volatile SingularAttribute<CustomerCard, Date> createdate;
    public static volatile SingularAttribute<CustomerCard, Customers> customers;
    public static volatile SingularAttribute<CustomerCard, Date> modifiedate;

}