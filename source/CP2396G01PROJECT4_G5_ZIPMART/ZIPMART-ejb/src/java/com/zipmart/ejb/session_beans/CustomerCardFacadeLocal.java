/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.CustomerCard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface CustomerCardFacadeLocal {

    void create(CustomerCard customerCard);

    void edit(CustomerCard customerCard);

    void remove(CustomerCard customerCard);

    CustomerCard find(Object id);

    List<CustomerCard> findAll();

    List<CustomerCard> findRange(int[] range);

    int count();
    
}
