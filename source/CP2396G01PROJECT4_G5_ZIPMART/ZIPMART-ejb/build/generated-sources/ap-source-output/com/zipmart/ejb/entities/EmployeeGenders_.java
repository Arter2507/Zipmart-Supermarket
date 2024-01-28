package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.EmployeeGendersPK;
import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.entities.Genders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-29T03:40:49")
@StaticMetamodel(EmployeeGenders.class)
public class EmployeeGenders_ { 

    public static volatile SingularAttribute<EmployeeGenders, String> createby;
    public static volatile SingularAttribute<EmployeeGenders, String> modifieby;
    public static volatile SingularAttribute<EmployeeGenders, Date> createdate;
    public static volatile SingularAttribute<EmployeeGenders, Genders> genders;
    public static volatile SingularAttribute<EmployeeGenders, EmployeeGendersPK> employeeGendersPK;
    public static volatile SingularAttribute<EmployeeGenders, Date> modifiedate;
    public static volatile SingularAttribute<EmployeeGenders, Employees> employees;

}