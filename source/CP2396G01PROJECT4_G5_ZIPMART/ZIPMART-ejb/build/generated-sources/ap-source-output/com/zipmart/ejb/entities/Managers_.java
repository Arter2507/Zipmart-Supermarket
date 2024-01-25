package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.ManagerGenders;
import com.zipmart.ejb.entities.Permissions;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(Managers.class)
public class Managers_ { 

    public static volatile SingularAttribute<Managers, String> modifieby;
    public static volatile SingularAttribute<Managers, String> address;
    public static volatile SingularAttribute<Managers, String> saltPassword;
    public static volatile SingularAttribute<Managers, Date> createdate;
    public static volatile SingularAttribute<Managers, String> pepperPassword;
    public static volatile SingularAttribute<Managers, Long> managerGender;
    public static volatile SingularAttribute<Managers, Permissions> managerGroup;
    public static volatile SingularAttribute<Managers, String> password;
    public static volatile SingularAttribute<Managers, String> createby;
    public static volatile CollectionAttribute<Managers, ManagerGenders> managerGendersCollection;
    public static volatile SingularAttribute<Managers, String> phone;
    public static volatile SingularAttribute<Managers, String> imageURL;
    public static volatile SingularAttribute<Managers, Long> id;
    public static volatile SingularAttribute<Managers, String> fullname;
    public static volatile SingularAttribute<Managers, Date> modifiedate;
    public static volatile SingularAttribute<Managers, String> email;
    public static volatile SingularAttribute<Managers, String> username;
    public static volatile SingularAttribute<Managers, Boolean> status;

}