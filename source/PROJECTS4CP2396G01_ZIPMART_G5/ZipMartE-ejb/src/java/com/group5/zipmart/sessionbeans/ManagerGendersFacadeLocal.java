/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.ManagerGenders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface ManagerGendersFacadeLocal {

    void create(ManagerGenders managerGenders);

    void edit(ManagerGenders managerGenders);

    void remove(ManagerGenders managerGenders);

    ManagerGenders find(Object id);

    List<ManagerGenders> findAll();

    List<ManagerGenders> findRange(int[] range);

    int count();
    
}
