package com.zipmart.ejb.entities;

import com.zipmart.ejb.entities.Blogs;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-01-28T08:17:03")
@StaticMetamodel(BlogCategories.class)
public class BlogCategories_ { 

    public static volatile SingularAttribute<BlogCategories, String> createby;
    public static volatile SingularAttribute<BlogCategories, String> modifieby;
    public static volatile CollectionAttribute<BlogCategories, Blogs> blogsCollection;
    public static volatile SingularAttribute<BlogCategories, String> name;
    public static volatile SingularAttribute<BlogCategories, String> description;
    public static volatile SingularAttribute<BlogCategories, Date> createdate;
    public static volatile SingularAttribute<BlogCategories, Long> id;
    public static volatile SingularAttribute<BlogCategories, Date> modifiedate;

}