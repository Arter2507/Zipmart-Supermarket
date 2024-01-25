package com.zipmart.ejb.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(SalesTotalsbyAmount.class)
public class SalesTotalsbyAmount_ { 

    public static volatile SingularAttribute<SalesTotalsbyAmount, BigDecimal> saleAmount;
    public static volatile SingularAttribute<SalesTotalsbyAmount, Long> id;
    public static volatile SingularAttribute<SalesTotalsbyAmount, String> fullname;
    public static volatile SingularAttribute<SalesTotalsbyAmount, Date> shipDate;
    public static volatile SingularAttribute<SalesTotalsbyAmount, Integer> quarter;

}