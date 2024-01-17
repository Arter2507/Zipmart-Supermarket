/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.SalesTotalsbyAmount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface SalesTotalsbyAmountFacadeLocal {

    void create(SalesTotalsbyAmount salesTotalsbyAmount);

    void edit(SalesTotalsbyAmount salesTotalsbyAmount);

    void remove(SalesTotalsbyAmount salesTotalsbyAmount);

    SalesTotalsbyAmount find(Object id);

    List<SalesTotalsbyAmount> findAll();

    List<SalesTotalsbyAmount> findRange(int[] range);

    int count();
    
}
