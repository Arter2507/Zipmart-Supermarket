/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.product;

import com.zipmart.dto.Cart;
import com.zipmart.dto.CartSessionBeanLocal;
import com.zipmart.ejb.entities.Products;
import com.zipmart.ejb.session_beans.ProductsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author TUONG
 */
@Named(value = "productBean")
@SessionScoped
public class ProductBean implements Serializable {

    @EJB
    private ProductsFacadeLocal productsFacade;

    private Products product;

    private Integer discout;
    private double unit;
    private Double price_dis;

    public ProductBean() {
    }

    public List<Products> showAllPro() {
        List<Products> list_pro = new ArrayList<>();
        for (Products pro : productsFacade.findAll()) {
            if (pro.getAvaliable() == true) {
                discountPrice(pro.getUnitPrice(), pro.getDiscount(), pro);
                list_pro.add(pro);
            }
        }
        return list_pro;
    }

    public String showDetailsPro(Long id) {
        product = productsFacade.find(id);
        unit = product.getUnitPrice();
        discout = product.getDiscount();
        discountPrice(product.getUnitPrice(), product.getDiscount(), product);
        return "shop-details";
    }

    public List<Products> showSame(Long id) {
        if (id == null) {
            return Collections.emptyList();
        }

        List<Products> list_pro = new ArrayList<>();
        Products prot = productsFacade.find(id);
        Long ids = prot.getId();
        for (Products pro : productsFacade.getProSameCate(ids)) {
            if (pro.getAvaliable() == true) {
                discountPrice(pro.getUnitPrice(), pro.getDiscount(), pro);
                list_pro.add(pro);
            }
        }
        Collections.shuffle(list_pro);
        return list_pro.subList(0, Math.min(6, list_pro.size()));
    }

    public List<Products> showFeature() {
        List<Products> list_pro = new ArrayList<>();
        for (Products pro : productsFacade.getFeaturedProducts()) {
            if (pro.getAvaliable() == true && pro.getDiscount() > 0) {
                discountPrice(pro.getUnitPrice(), pro.getDiscount(), pro);
                list_pro.add(pro);
            }
        }
        Collections.shuffle(list_pro);
        return list_pro.subList(0, Math.min(3, list_pro.size()));
    }

    public List<Products> showFeaDetail() {
        List<Products> list_pro = new ArrayList<>();
        for (Products pro : productsFacade.getFeaturedProductDetails()) {
            if (pro.getDiscount() > 0) {
                discountPrice(pro.getUnitPrice(), pro.getDiscount(), pro);
                list_pro.add(pro);
                pro.setId(null);
            }
        }
        return list_pro;
    }

    public Double discountPrice(Double unit, Integer dis, Products product) {
        if (dis > 0) {
            Double price_dis = unit * (1 - dis / 100.0);
            price_dis = Math.ceil(price_dis * 100) / 100;
            product.setPrice_discout(price_dis);
            return price_dis;
        }
        return product.getPrice_discout();
    }

    public ProductsFacadeLocal getProductsFacade() {
        return productsFacade;
    }

    public void setProductsFacade(ProductsFacadeLocal productsFacade) {
        this.productsFacade = productsFacade;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getDiscout() {
        return discout;
    }

    public void setDiscout(Integer discout) {
        this.discout = discout;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public Double getPrice_dis() {
        return price_dis;
    }

    public void setPrice_dis(Double price_dis) {
        this.price_dis = price_dis;
    }

}
