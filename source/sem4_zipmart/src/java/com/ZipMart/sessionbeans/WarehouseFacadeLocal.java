/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.Warehouse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface WarehouseFacadeLocal {

    void create(Warehouse warehouse);

    void edit(Warehouse warehouse);

    void remove(Warehouse warehouse);

    Warehouse find(Object id);

    List<Warehouse> findAll();

    List<Warehouse> findRange(int[] range);

    int count();
    
}
