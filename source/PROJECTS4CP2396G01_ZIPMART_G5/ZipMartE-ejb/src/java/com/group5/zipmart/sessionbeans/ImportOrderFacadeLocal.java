/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.ImportOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface ImportOrderFacadeLocal {

    void create(ImportOrder importOrder);

    void edit(ImportOrder importOrder);

    void remove(ImportOrder importOrder);

    ImportOrder find(Object id);

    List<ImportOrder> findAll();

    List<ImportOrder> findRange(int[] range);

    int count();
    
}