/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.CustomerGenders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface CustomerGendersFacadeLocal {

    void create(CustomerGenders customerGenders);

    void edit(CustomerGenders customerGenders);

    void remove(CustomerGenders customerGenders);

    CustomerGenders find(Object id);

    List<CustomerGenders> findAll();

    List<CustomerGenders> findRange(int[] range);

    int count();
    
}
