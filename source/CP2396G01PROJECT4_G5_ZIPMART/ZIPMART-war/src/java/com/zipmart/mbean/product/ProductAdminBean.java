/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.product;

import com.zipmart.ejb.entities.Brand;
import com.zipmart.ejb.entities.Categories;
import com.zipmart.ejb.entities.Products;
import com.zipmart.ejb.entities.Suppliers;
import com.zipmart.ejb.session_beans.BrandFacadeLocal;
import com.zipmart.ejb.session_beans.CategoriesFacadeLocal;
import com.zipmart.ejb.session_beans.ProductsFacadeLocal;
import com.zipmart.ejb.session_beans.SuppliersFacadeLocal;
import com.zipmart.util.FileUltil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author TUONG
 */
@Named(value = "productAdminBean")
@RequestScoped
public class ProductAdminBean {

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private SuppliersFacadeLocal suppliersFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private BrandFacadeLocal brandFacade;

//    private Categories category = new Categories();
//    private Brand brand = new Brand();
//    private Suppliers supplier = new Suppliers();
    private Products product;

    private Part file;
    private String image_url;

    private Long selected_brand;
    private Long selected_supplier;
    private Long selected_category;

    public ProductAdminBean() {
    }

    public List<Brand> showAllBrand() {
        return brandFacade.findAll();
    }

    public List<Suppliers> showAllSupplier() {
        return suppliersFacade.findAll();
    }

    public List<Categories> showAllCategory() {
        return categoriesFacade.findAll();
    }

    public List<Products> showAllProduct() {
        List<Products> list_pro = new ArrayList<>();
        for (Products product : productsFacade.findAll()) {
            if (product.getAvaliable()) {
                list_pro.add(product);
            }
        }
        return list_pro;
    }

    public String addNewProduct() {
        product = new Products();
        image_url = FileUltil.getInstance().uploadFile(file);
        product.setBrandID(brandFacade.find(selected_brand));
        product.setCategoryID(categoriesFacade.find(selected_category));
        product.setSupplierID(suppliersFacade.find(selected_supplier));
        product.setImageURL(image_url);
        productsFacade.create(product);
        return "product";
    }

    public String detailProduct(Long id) {
        product = productsFacade.find(id);
        return "detailProduct";
    }

    public String updateProduct(Long id) {
        product = productsFacade.find(id);
        return "updateProduct";
    }

    public String updateProductProcess() {
        Products pro = product;
        if (pro.getImageURL() != null) {
            pro.setImageURL(pro.getImageURL());
        } else {
            image_url = FileUltil.getInstance().uploadFile(file);
            pro.setImageURL(image_url);
        }
        pro.setBrandID(brandFacade.find(selected_brand));
        pro.setCategoryID(categoriesFacade.find(selected_category));
        pro.setSupplierID(suppliersFacade.find(selected_supplier));
        productsFacade.edit(pro);
        return "product";
    }

    public CategoriesFacadeLocal getCategoriesFacade() {
        return categoriesFacade;
    }

    public void setCategoriesFacade(CategoriesFacadeLocal categoriesFacade) {
        this.categoriesFacade = categoriesFacade;
    }

    public SuppliersFacadeLocal getSuppliersFacade() {
        return suppliersFacade;
    }

    public void setSuppliersFacade(SuppliersFacadeLocal suppliersFacade) {
        this.suppliersFacade = suppliersFacade;
    }

    public ProductsFacadeLocal getProductsFacade() {
        return productsFacade;
    }

    public void setProductsFacade(ProductsFacadeLocal productsFacade) {
        this.productsFacade = productsFacade;
    }

    public BrandFacadeLocal getBrandFacade() {
        return brandFacade;
    }

    public void setBrandFacade(BrandFacadeLocal brandFacade) {
        this.brandFacade = brandFacade;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Long getSelected_brand() {
        return selected_brand;
    }

    public void setSelected_brand(Long selected_brand) {
        this.selected_brand = selected_brand;
    }

    public Long getSelected_supplier() {
        return selected_supplier;
    }

    public void setSelected_supplier(Long selected_supplier) {
        this.selected_supplier = selected_supplier;
    }

    public Long getSelected_category() {
        return selected_category;
    }

    public void setSelected_category(Long selected_category) {
        this.selected_category = selected_category;
    }
}
