/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.Managers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface ManagersFacadeLocal {

    void create(Managers managers);

    void edit(Managers managers);

    void remove(Managers managers);

    Managers find(Object id);

    List<Managers> findAll();

    List<Managers> findRange(int[] range);

    int count();
    
}
