/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.dto;

/**
 *
 * @author TUONG
 */
public class Cart {
    private Long id;
    private String pro_name;
    private int quantity;
    private double unit_price;
    private int discout;
    private double price_discount;
    private double total_price;

    public Cart(Long id, String pro_name, int quantity, double unit_price, int discout, double price_discount, double total_price, String image_url) {
        this.id = id;
        this.pro_name = pro_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.discout = discout;
        this.price_discount = price_discount;
        this.total_price = total_price;
        this.image_url = image_url;
    }

    public Cart() {
    }
    
    private String image_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getDiscout() {
        return discout;
    }

    public void setDiscout(int discout) {
        this.discout = discout;
    }

    public double getPrice_discount() {
        return price_discount;
    }

    public void setPrice_discount(double price_discount) {
        this.price_discount = price_discount;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
