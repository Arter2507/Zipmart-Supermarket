/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.BlogCategories;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface BlogCategoriesFacadeLocal {

    void create(BlogCategories blogCategories);

    void edit(BlogCategories blogCategories);

    void remove(BlogCategories blogCategories);

    BlogCategories find(Object id);

    List<BlogCategories> findAll();

    List<BlogCategories> findRange(int[] range);

    int count();
    
}
