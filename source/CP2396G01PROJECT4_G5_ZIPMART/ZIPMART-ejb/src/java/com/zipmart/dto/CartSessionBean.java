/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.zipmart.dto;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateful;

/**
 *
 * @author TUONG
 */
@Stateful
public class CartSessionBean implements CartSessionBeanLocal {

    Map<Long, Integer> mycart = new HashMap<Long, Integer>();

    @Override
    public void addCart(Long id, Integer qual) {
        if (mycart.containsKey(id)) {
            mycart.replace(id, mycart.get(id));
        } else {
            mycart.put(id, qual);
        }
        System.out.println("-------->" + id);
    }

    @Override
    public Map<Long, Integer> showCartMap() {
        return mycart;
    }

    @Override
    public int countCart() {
        return mycart.size();
    }

    @Override
    public void removeCart(Long id) {
        mycart.remove(id);
    }

    @Override
    public void emptyCart() {
        mycart.clear();
    }

    @Override
    public void plusCart(Long id) {
        if (mycart.containsKey(id)) {
            if (mycart.containsKey(id)) {
                mycart.replace(id, mycart.get(id) + 1);
            }
        }
    }

    @Override
    public void deCart(Long id) {
        if (mycart.containsKey(id)) {
            if (mycart.containsKey(id)) {
                if (mycart.get(id) >= 1) {
                    mycart.replace(id, mycart.get(id) - 1);
                }
            }
        }
    }

    @Override
    public void clearCart() {
        mycart.clear();
    }

    @Override
    public void updateCart(Long id, boolean flag, Long max) {
        if (flag) {
            if (mycart.get(id) < max) {
                mycart.replace(id, mycart.get(id) + 1);
                System.out.println("increase");
            }
            System.out.println(mycart.get(id));
        } else {
            if (mycart.get(id) > 1) {
                mycart.replace(id, mycart.get(id) - 1);

            }
            System.out.println("decrease" + max);
        }
    }

    @Override
    public void upCart(Long id, boolean flag) {
        if (flag) {
            mycart.replace(id, mycart.get(id) + 1);
        } else {
            mycart.replace(id, mycart.get(id) - 1);
        }
    }

    @Override
    public void uCart(Long id, boolean flag, Cart cart) {
        int increment = flag ? 1 : -1;
        int newQuantity = mycart.getOrDefault(id, 0) + increment;
        newQuantity = Math.max(newQuantity, 0);
        mycart.put(id, newQuantity);
        cart.setQuantity(newQuantity); 
    }

}
