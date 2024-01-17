/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.OrderByQuarter;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface OrderByQuarterFacadeLocal {

    void create(OrderByQuarter orderByQuarter);

    void edit(OrderByQuarter orderByQuarter);

    void remove(OrderByQuarter orderByQuarter);

    OrderByQuarter find(Object id);

    List<OrderByQuarter> findAll();

    List<OrderByQuarter> findRange(int[] range);

    int count();
    
}
