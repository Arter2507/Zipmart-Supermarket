package com.ZipMart.entities;

import com.ZipMart.entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-12-20T13:48:08")
@StaticMetamodel(ThresholdAdjustment.class)
public class ThresholdAdjustment_ { 

    public static volatile SingularAttribute<ThresholdAdjustment, String> reasonAdjustment;
    public static volatile SingularAttribute<ThresholdAdjustment, Products> productID;
    public static volatile SingularAttribute<ThresholdAdjustment, Integer> newrestockThreshold;
    public static volatile SingularAttribute<ThresholdAdjustment, String> statusThresholdAdjustments;
    public static volatile SingularAttribute<ThresholdAdjustment, Integer> id;
    public static volatile SingularAttribute<ThresholdAdjustment, Date> dateAdjusted;

}