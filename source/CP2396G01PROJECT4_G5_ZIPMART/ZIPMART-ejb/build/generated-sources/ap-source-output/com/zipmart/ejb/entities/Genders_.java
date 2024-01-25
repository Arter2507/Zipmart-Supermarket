package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.CustomerGenders;
import com.zipmart.ejb.entities.EmployeeGenders;
import com.zipmart.ejb.entities.ManagerGenders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(Genders.class)
public class Genders_ { 

    public static volatile CollectionAttribute<Genders, EmployeeGenders> employeeGendersCollection;
    public static volatile CollectionAttribute<Genders, CustomerGenders> customerGendersCollection;
    public static volatile SingularAttribute<Genders, String> createby;
    public static volatile SingularAttribute<Genders, String> modifieby;
    public static volatile CollectionAttribute<Genders, ManagerGenders> managerGendersCollection;
    public static volatile SingularAttribute<Genders, String> description;
    public static volatile SingularAttribute<Genders, Date> createdate;
    public static volatile SingularAttribute<Genders, Long> id;
    public static volatile SingularAttribute<Genders, Date> modifiedate;
    public static volatile SingularAttribute<Genders, String> genderName;

}