package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Blogs;
import com.zipmart.ejb.entities.EmployeeBlogPK;
import com.zipmart.ejb.entities.Employees;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-26T05:42:19")
@StaticMetamodel(EmployeeBlog.class)
public class EmployeeBlog_ { 

    public static volatile SingularAttribute<EmployeeBlog, String> createby;
    public static volatile SingularAttribute<EmployeeBlog, String> modifieby;
    public static volatile SingularAttribute<EmployeeBlog, Blogs> blogs;
    public static volatile SingularAttribute<EmployeeBlog, Date> createdate;
    public static volatile SingularAttribute<EmployeeBlog, EmployeeBlogPK> employeeBlogPK;
    public static volatile SingularAttribute<EmployeeBlog, Date> modifiedate;
    public static volatile SingularAttribute<EmployeeBlog, Employees> employees;

}