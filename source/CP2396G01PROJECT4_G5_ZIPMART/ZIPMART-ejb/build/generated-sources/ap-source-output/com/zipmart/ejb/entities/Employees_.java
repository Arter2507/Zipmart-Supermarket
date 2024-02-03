package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Permissions;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-02-02T08:47:42")
@StaticMetamodel(Employees.class)
public class Employees_ { 

    public static volatile SingularAttribute<Employees, String> modifieby;
    public static volatile SingularAttribute<Employees, String> address;
    public static volatile SingularAttribute<Employees, Date> createdate;
    public static volatile SingularAttribute<Employees, Short> employeeGender;
    public static volatile SingularAttribute<Employees, Date> birthDate;
    public static volatile SingularAttribute<Employees, String> password;
    public static volatile SingularAttribute<Employees, String> createby;
    public static volatile SingularAttribute<Employees, String> phone;
    public static volatile SingularAttribute<Employees, String> imageURL;
    public static volatile SingularAttribute<Employees, Permissions> employeeGroup;
    public static volatile SingularAttribute<Employees, Long> id;
    public static volatile SingularAttribute<Employees, String> fullname;
    public static volatile SingularAttribute<Employees, Date> modifiedate;
    public static volatile SingularAttribute<Employees, String> email;
    public static volatile SingularAttribute<Employees, String> username;
    public static volatile SingularAttribute<Employees, Boolean> status;

}