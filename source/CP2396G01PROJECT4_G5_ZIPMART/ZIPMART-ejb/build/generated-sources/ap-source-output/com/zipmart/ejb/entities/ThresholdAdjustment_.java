package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Categories;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(ThresholdAdjustment.class)
public class ThresholdAdjustment_ { 

    public static volatile SingularAttribute<ThresholdAdjustment, String> reasonAdjustment;
    public static volatile SingularAttribute<ThresholdAdjustment, Integer> newrestockThreshold;
    public static volatile SingularAttribute<ThresholdAdjustment, String> statusThresholdAdjustments;
    public static volatile SingularAttribute<ThresholdAdjustment, Long> id;
    public static volatile SingularAttribute<ThresholdAdjustment, Date> dateAdjusted;
    public static volatile SingularAttribute<ThresholdAdjustment, Categories> categoryID;

}