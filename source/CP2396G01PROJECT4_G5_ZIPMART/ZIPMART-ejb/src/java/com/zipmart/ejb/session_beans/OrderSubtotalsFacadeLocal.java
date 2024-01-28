/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.OrderSubtotals;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface OrderSubtotalsFacadeLocal {

    void create(OrderSubtotals orderSubtotals);

    void edit(OrderSubtotals orderSubtotals);

    void remove(OrderSubtotals orderSubtotals);

    OrderSubtotals find(Object id);

    List<OrderSubtotals> findAll();

    List<OrderSubtotals> findRange(int[] range);

    int count();
    
}