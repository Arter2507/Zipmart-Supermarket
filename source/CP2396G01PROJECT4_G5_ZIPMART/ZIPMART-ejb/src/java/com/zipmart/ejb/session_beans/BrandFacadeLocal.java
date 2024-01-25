/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Brand;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface BrandFacadeLocal {

    void create(Brand brand);

    void edit(Brand brand);

    void remove(Brand brand);

    Brand find(Object id);

    List<Brand> findAll();

    List<Brand> findRange(int[] range);

    int count();
    
}
