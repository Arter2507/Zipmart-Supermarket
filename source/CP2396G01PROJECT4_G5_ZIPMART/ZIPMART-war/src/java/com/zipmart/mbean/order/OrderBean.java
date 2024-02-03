package com.zipmart.mbean.order;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import com.zipmart.ejb.session_beans.OrderDetailsFacadeLocal;
import com.zipmart.ejb.session_beans.OrdersFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author TUONG
 */
@Named(value = "orderBean")
@RequestScoped
public class OrderBean {

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    
    public OrderBean() {
    }

    public OrdersFacadeLocal getOrdersFacade() {
        return ordersFacade;
    }

    public void setOrdersFacade(OrdersFacadeLocal ordersFacade) {
        this.ordersFacade = ordersFacade;
    }

    public OrderDetailsFacadeLocal getOrderDetailsFacade() {
        return orderDetailsFacade;
    }

    public void setOrderDetailsFacade(OrderDetailsFacadeLocal orderDetailsFacade) {
        this.orderDetailsFacade = orderDetailsFacade;
    }
    
    
}
