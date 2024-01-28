/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.blog;

import com.zipmart.ejb.entities.BlogCategories;
import com.zipmart.ejb.entities.Blogs;
import com.zipmart.ejb.session_beans.BlogCategoriesFacadeLocal;
import com.zipmart.ejb.session_beans.BlogsFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author TUONG
 */
@Named(value = "blogBean")
@RequestScoped
public class BlogBean {

    @EJB
    private BlogsFacadeLocal blogsFacade;

    @EJB
    private BlogCategoriesFacadeLocal blogCategoriesFacade;

    private Blogs blog = new Blogs();

    public BlogBean() {
    }

    public List<Blogs> showAllBlog() {
        return blogsFacade.findAll();
    }

    public List<BlogCategories> showAllCategory() {
        return blogCategoriesFacade.findAll();
    }

    public Blogs getBlog() {
        return blog;
    }

    public void setBlog(Blogs blog) {
        this.blog = blog;
    }

    public BlogsFacadeLocal getBlogsFacade() {
        return blogsFacade;
    }

    public void setBlogsFacade(BlogsFacadeLocal blogsFacade) {
        this.blogsFacade = blogsFacade;
    }

    public BlogCategoriesFacadeLocal getBlogCategoriesFacade() {
        return blogCategoriesFacade;
    }

    public void setBlogCategoriesFacade(BlogCategoriesFacadeLocal blogCategoriesFacade) {
        this.blogCategoriesFacade = blogCategoriesFacade;
    }
}
