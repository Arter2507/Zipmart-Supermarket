package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.CustomerCard;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-29T03:40:49")
@StaticMetamodel(Cards.class)
public class Cards_ { 

    public static volatile SingularAttribute<Cards, String> modifieby;
    public static volatile SingularAttribute<Cards, String> cardName;
    public static volatile SingularAttribute<Cards, String> cardType;
    public static volatile SingularAttribute<Cards, Date> createdate;
    public static volatile CollectionAttribute<Cards, CustomerCard> customerCardCollection;
    public static volatile SingularAttribute<Cards, Date> valueFrom;
    public static volatile SingularAttribute<Cards, BigInteger> point;
    public static volatile SingularAttribute<Cards, Integer> cvvNumber;
    public static volatile SingularAttribute<Cards, String> createby;
    public static volatile SingularAttribute<Cards, String> rank;
    public static volatile SingularAttribute<Cards, Long> id;
    public static volatile SingularAttribute<Cards, Date> modifiedate;
    public static volatile SingularAttribute<Cards, String> cardNumber;
    public static volatile SingularAttribute<Cards, Date> expirationDate;

}