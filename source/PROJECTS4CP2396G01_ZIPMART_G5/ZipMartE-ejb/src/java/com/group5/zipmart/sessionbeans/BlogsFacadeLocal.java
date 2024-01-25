/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.Blogs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author phnha
 */
@Local
public interface BlogsFacadeLocal {

    void create(Blogs blogs);

    void edit(Blogs blogs);

    void remove(Blogs blogs);

    Blogs find(Object id);

    List<Blogs> findAll();

    List<Blogs> findRange(int[] range);

    int count();
    
}
