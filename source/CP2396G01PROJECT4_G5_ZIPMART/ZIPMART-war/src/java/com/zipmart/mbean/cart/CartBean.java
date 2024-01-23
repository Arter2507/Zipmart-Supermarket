/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.cart;

import com.zipmart.dto.Cart;
import com.zipmart.dto.CartSessionBeanLocal;
import com.zipmart.ejb.entities.Products;
import com.zipmart.ejb.session_beans.ProductsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author TUONG
 */
@Named(value = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

    @Inject
    private CartBean cartBean;

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private CartSessionBeanLocal cartSessionBean;

    private Cart cart = new Cart();

    private int quantity_sell;
    private int num;
    private List<Integer> num_cart = new ArrayList<>();
    private double total_moneycart;
    private double price_dis = 0.00;
    private double total_pr;
    private int total_procart;
    private String message = "";
    private Double ship = 2.00;

    public CartBean() {
    }

    public String addCart(Long id, int quantity) {
        try {
            System.out.println("=============================1 " + id + " " + quantity);
            cartSessionBean.addCart(id, quantity);
            System.out.println("=============================" + id + " " + quantity);
            return "cart";
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("=============================" + id + " " + quantity);
        }
        return "cart";
    }

    public List<Cart> showCart() {
        List<Cart> list_cart = new ArrayList<>();
        Set<Map.Entry<Long, Integer>> setCart = cartSessionBean.showCartMap().entrySet();
        total_moneycart = 0.0;
        total_procart = 0;
        price_dis = 0.0;
        total_pr = 0.0;
        for (Map.Entry<Long, Integer> e : setCart) {
            Long id = e.getKey();
            Integer quality = e.getValue();
            Products pro = productsFacade.find(id);
            double uprice = pro.getUnitPrice();
            if (pro.getDiscount() > 0) {
                price_dis = pro.getUnitPrice() * (1 - pro.getDiscount() / 100.0);
                total_pr = quality * price_dis;
            } else {
                price_dis = 0.0;
                total_pr = quality * uprice;
            }
            Cart cShop = new Cart(id, pro.getProductName(), quality, uprice, pro.getDiscount(), price_dis, total_pr, pro.getImageURL());
            list_cart.add(cShop);
            num_cart.add(quality);
            total_moneycart += total_pr;
            total_procart += quality;
        }
        return list_cart;
    }

    public void updateCart(Long id, boolean flag) {
        cartSessionBean.upCart(id, flag);
    }
    
    public void upCart(Long id, boolean flag) {
        cartSessionBean.uCart(id, flag, cart);
    }

    public String removeCart(Long id) {
        cartSessionBean.removeCart(id);
        message = "Delete product success!";
        return "cart";
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartSessionBeanLocal getCartSessionBean() {
        return cartSessionBean;
    }

    public void setCartSessionBean(CartSessionBeanLocal cartSessionBean) {
        this.cartSessionBean = cartSessionBean;
    }

    public int getQuantity_sell() {
        return quantity_sell;
    }

    public void setQuantity_sell(int quantity_sell) {
        this.quantity_sell = quantity_sell;
    }

    public List<Integer> getNumCart() {
        return num_cart;
    }

    public void setNumCart(List<Integer> num_cart) {
        this.num_cart = num_cart;
    }

    public ProductsFacadeLocal getProductsFacade() {
        return productsFacade;
    }

    public void setProductsFacade(ProductsFacadeLocal productsFacade) {
        this.productsFacade = productsFacade;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotalMoneyCart() {
        return total_moneycart;
    }

    public void setTotalMoneyCart(double total_moneycart) {
        this.total_moneycart = total_moneycart;
    }

    public double getPrice_dis() {
        return price_dis;
    }

    public void setPrice_dis(double price_dis) {
        this.price_dis = price_dis;
    }

    public double getTotal_pr() {
        return total_pr;
    }

    public void setTotal_pr(double total_pr) {
        this.total_pr = total_pr;
    }

    public int getTotalProCart() {
        return total_procart;
    }

    public void setTotalProCart(int total_procart) {
        this.total_procart = total_procart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getShip() {
        return ship;
    }

    public void setShip(Double ship) {
        this.ship = ship;
    }

    public CartBean getCartBean() {
        if (cartBean == null) {
            cartBean = new CartBean();
        }
        return cartBean;
    }

    public void setCartBean(CartBean cartBean) {
        this.cartBean = cartBean;
    }

    public List<Integer> getNum_cart() {
        return num_cart;
    }

    public void setNum_cart(List<Integer> num_cart) {
        this.num_cart = num_cart;
    }

    public double getTotal_moneycart() {
        return total_moneycart;
    }

    public void setTotal_moneycart(double total_moneycart) {
        this.total_moneycart = total_moneycart;
    }

    public int getTotal_procart() {
        return total_procart;
    }

    public void setTotal_procart(int total_procart) {
        this.total_procart = total_procart;
    }
}
