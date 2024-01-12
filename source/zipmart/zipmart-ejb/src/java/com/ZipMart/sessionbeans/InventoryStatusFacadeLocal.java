/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.InventoryStatus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface InventoryStatusFacadeLocal {

    void create(InventoryStatus inventoryStatus);

    void edit(InventoryStatus inventoryStatus);

    void remove(InventoryStatus inventoryStatus);

    InventoryStatus find(Object id);

    List<InventoryStatus> findAll();

    List<InventoryStatus> findRange(int[] range);

    int count();
    
}
