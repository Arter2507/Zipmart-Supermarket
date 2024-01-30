package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Categories;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-30T17:34:12")
@StaticMetamodel(ThresholdAdjustment.class)
public class ThresholdAdjustment_ { 

    public static volatile SingularAttribute<ThresholdAdjustment, String> reasonAdjustment;
    public static volatile SingularAttribute<ThresholdAdjustment, String> createby;
    public static volatile SingularAttribute<ThresholdAdjustment, String> modifieby;
    public static volatile SingularAttribute<ThresholdAdjustment, Integer> newrestockThreshold;
    public static volatile SingularAttribute<ThresholdAdjustment, String> statusThresholdAdjustments;
    public static volatile SingularAttribute<ThresholdAdjustment, Date> createdate;
    public static volatile SingularAttribute<ThresholdAdjustment, Long> id;
    public static volatile SingularAttribute<ThresholdAdjustment, Date> dateAdjusted;
    public static volatile SingularAttribute<ThresholdAdjustment, Date> modifiedate;
    public static volatile SingularAttribute<ThresholdAdjustment, Categories> categoryID;

}