/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.category;

import com.zipmart.ejb.entities.Categories;
import com.zipmart.ejb.session_beans.CategoriesFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author TUONG
 */
@Named(value = "categoryBean")
@RequestScoped
public class CategoryBean {

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    private Categories catogory = new Categories();

    public CategoryBean() {
    }

    public List<Categories> showAllCate() {
        return categoriesFacade.findAll();
    }

    public CategoriesFacadeLocal getCategoriesFacade() {
        return categoriesFacade;
    }

    public void setCategoriesFacade(CategoriesFacadeLocal categoriesFacade) {
        this.categoriesFacade = categoriesFacade;
    }

    public Categories getCatogory() {
        return catogory;
    }

    public void setCatogory(Categories catogory) {
        this.catogory = catogory;
    }

}
