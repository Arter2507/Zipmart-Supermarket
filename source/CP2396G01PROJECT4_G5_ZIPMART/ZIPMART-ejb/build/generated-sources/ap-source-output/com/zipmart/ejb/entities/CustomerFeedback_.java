package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.CustomerFeedbackPK;
import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.entities.Feedbacks;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(CustomerFeedback.class)
public class CustomerFeedback_ { 

    public static volatile SingularAttribute<CustomerFeedback, CustomerFeedbackPK> customerFeedbackPK;
    public static volatile SingularAttribute<CustomerFeedback, String> createby;
    public static volatile SingularAttribute<CustomerFeedback, String> modifieby;
    public static volatile SingularAttribute<CustomerFeedback, Date> createdate;
    public static volatile SingularAttribute<CustomerFeedback, Feedbacks> feedbacks;
    public static volatile SingularAttribute<CustomerFeedback, Long> id;
    public static volatile SingularAttribute<CustomerFeedback, Customers> customers;
    public static volatile SingularAttribute<CustomerFeedback, Date> modifiedate;

}