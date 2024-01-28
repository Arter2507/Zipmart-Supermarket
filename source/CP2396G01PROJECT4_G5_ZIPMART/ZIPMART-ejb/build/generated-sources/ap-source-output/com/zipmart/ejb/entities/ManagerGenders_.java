package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Genders;
import com.zipmart.ejb.entities.ManagerGendersPK;
import com.zipmart.ejb.entities.Managers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-29T03:40:49")
@StaticMetamodel(ManagerGenders.class)
public class ManagerGenders_ { 

    public static volatile SingularAttribute<ManagerGenders, ManagerGendersPK> managerGendersPK;
    public static volatile SingularAttribute<ManagerGenders, String> createby;
    public static volatile SingularAttribute<ManagerGenders, String> modifieby;
    public static volatile SingularAttribute<ManagerGenders, Date> createdate;
    public static volatile SingularAttribute<ManagerGenders, Genders> genders;
    public static volatile SingularAttribute<ManagerGenders, Date> modifiedate;
    public static volatile SingularAttribute<ManagerGenders, Managers> managers;

}