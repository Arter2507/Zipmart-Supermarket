/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.OrdersDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface OrdersDetailsFacadeLocal {

    void create(OrdersDetails ordersDetails);

    void edit(OrdersDetails ordersDetails);

    void remove(OrdersDetails ordersDetails);

    OrdersDetails find(Object id);

    List<OrdersDetails> findAll();

    List<OrdersDetails> findRange(int[] range);

    int count();
    
}
