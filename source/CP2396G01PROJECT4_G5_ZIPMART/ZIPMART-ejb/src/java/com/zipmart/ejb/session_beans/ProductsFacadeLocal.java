/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Products;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface ProductsFacadeLocal {

    void create(Products products);

    void edit(Products products);

    void remove(Products products);

    Products find(Object id);

    List<Products> findAll();

    List<Products> findRange(int[] range);

    int count();
    
    List<Products> getFeaturedProducts ();
    
    List<Products> getProSameCate(long id);
    
    List<Products> getFeaturedProductDetails ();
    
}
