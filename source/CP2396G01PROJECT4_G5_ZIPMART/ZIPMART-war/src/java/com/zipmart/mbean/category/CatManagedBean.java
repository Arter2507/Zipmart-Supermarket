/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.category;

import com.zipmart.ejb.entities.Categories;
import com.zipmart.ejb.session_beans.CategoriesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author phnha
 */
@Named(value = "catMB")
@SessionScoped
public class CatManagedBean implements Serializable {

    @EJB
    private CategoriesFacadeLocal categoriesFacade;
    
    private Categories categories;
    private List <Categories> listCategories = new ArrayList<>();
    private Date createdate;
    public CatManagedBean() {
        categories = new Categories();
        listCategories = new ArrayList<>();
        categories.setCreatedate(new Date(System.currentTimeMillis()));
    }
    
    //showall
    public List<Categories> showAllCategories(){
        return categoriesFacade.findAll();
    }
    
    //Create
    public String createCat() {
        categoriesFacade.create(categories);
        return "displaycategory?faces-redirect=true";
    }
    
    //show detail customer
    public String showDetailCategory(long id) {
        categories = categoriesFacade.find(id);
        return "updatecategory";
    }
    
    //Update
    public String updateCat() {
        categoriesFacade.edit(categories);
        return "displaycategory?faces-redirect=true";
    }
    //Delete
    public String deleteCus(long id) {
        Categories c = categoriesFacade.find(id);
        categoriesFacade.remove(c);
        return "displaycategory";
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List <Categories> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List <Categories> listCategories) {
        this.listCategories = listCategories;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public CategoriesFacadeLocal getCategoriesFacade() {
        return categoriesFacade;
    }

    public void setCategoriesFacade(CategoriesFacadeLocal categoriesFacade) {
        this.categoriesFacade = categoriesFacade;
    }
    
}
