/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package com.zipmart.dto;

import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface CartSessionBeanLocal {
    void addCart(Long id, Integer qual);

    public Map<Long, Integer> showCartMap();

    public int countCart();

    public void removeCart(Long id);

    public void emptyCart();  
    
    void plusCart(Long id);
    
    void deCart(Long id);
    
    void clearCart();

    public void updateCart(Long id, boolean flag, Long max);
    
    public void upCart(Long id, boolean flag);
    
    public void uCart(Long id, boolean flag, Cart cart);
}
