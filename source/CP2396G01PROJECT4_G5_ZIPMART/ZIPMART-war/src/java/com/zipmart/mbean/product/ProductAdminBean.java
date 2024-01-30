/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.product;

import com.zipmart.ejb.entities.Brand;
import com.zipmart.ejb.entities.Categories;
import com.zipmart.ejb.entities.InventoryStatus;
import com.zipmart.ejb.entities.Products;
import com.zipmart.ejb.entities.Suppliers;
import com.zipmart.ejb.session_beans.BrandFacadeLocal;
import com.zipmart.ejb.session_beans.CategoriesFacadeLocal;
import com.zipmart.ejb.session_beans.InventoryStatusFacadeLocal;
import com.zipmart.ejb.session_beans.ProductsFacadeLocal;
import com.zipmart.ejb.session_beans.SuppliersFacadeLocal;
import com.zipmart.util.FileUltil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

@Named(value = "productAdminBean")
@RequestScoped
public class ProductAdminBean {

    @EJB
    private InventoryStatusFacadeLocal inventoryStatusFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private SuppliersFacadeLocal suppliersFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private BrandFacadeLocal brandFacade;

    private Products product;
    private Brand brand;
    private Categories category;
    private Suppliers supplier;
    private InventoryStatus inventory;

    private Long id;
    private String product_name;
    private Double unit_price;
    private String unit;
    private int quantity;
    private int quantity_in_stock;
    private int discount;

    private Part file;
    private String image_url;

    private Long selected_brand;
    private Long selected_supplier;
    private Long selected_category;
    private Long selected_inventorystatus;

    public List<Brand> showAllBrand() {
        return brandFacade.findAll();
    }

    public List<Suppliers> showAllSupplier() {
        return suppliersFacade.findAll();
    }

    public List<Categories> showAllCategory() {
        return categoriesFacade.findAll();
    }

    public List<InventoryStatus> showAllSatus() {
        return inventoryStatusFacade.findAll();
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
        image_url = FileUltil.getInstance().uploadFile(file);

        product.setProductName(product_name);
        product.setUnitPrice(unit_price);
        product.setDiscount(discount);
        product.setUnit(unit);
        product.setBrandID(brandFacade.find(selected_brand));
        product.setCategoryID(categoriesFacade.find(selected_category));
        product.setSupplierID(suppliersFacade.find(selected_supplier));
        product.setImageURL(image_url);
        product.setCreatedate(new Date());
        product.setAvaliable(Boolean.TRUE);

        if (quantity > quantity_in_stock) {
            product.setQuantity(quantity_in_stock);
            product.setQuantityInStock(quantity);
        } else {
            product.setQuantity(quantity);
            product.setQuantityInStock(quantity_in_stock);
        }

        if (quantity_in_stock < 50) {
            selected_inventorystatus = 3L;
        } else if (quantity_in_stock >= 50 && quantity_in_stock < 120) {
            selected_inventorystatus = 2L;
        } else {
            selected_inventorystatus = 1L;
        }

        product.setInventoryStatus(inventoryStatusFacade.find(selected_inventorystatus));
        productsFacade.create(product);
        return "product";
    }

    public String detailProduct(Long id) {
        product = productsFacade.find(id);
        return "detailProduct";
    }

    public String updateProduct(Long id) {
        product = productsFacade.find(id);
        id = product.getId();

        brand = brandFacade.find(product.getBrandID().getId());
        selected_brand = brand.getId();

        supplier = suppliersFacade.find(product.getSupplierID().getId());
        selected_supplier = supplier.getId();

        category = categoriesFacade.find(product.getCategoryID().getId());
        selected_category = category.getId();
        System.out.println("----ID Product------>" + id);
        return "updateProduct";
    }

    public String updateProductProcess() {
        Products pro = product;
        System.out.println("------------->" + pro.getId());
        Products p = productsFacade.find(pro.getId());
        if (image_url == null && file != null) {
            image_url = FileUltil.getInstance().uploadFile(file);
            pro.setImageURL(image_url);
            System.out.println("------------->" + pro.getImageURL());
        } else if (image_url == null && file == null) {
            pro.setImageURL(p.getImageURL());
            System.out.println("------------->" + pro.getImageURL());
        }
        pro.setCreatedate(p.getCreatedate());
        pro.setModifiedate(new Date(System.currentTimeMillis()));
        pro.setBrandID(selected_brand == null ? p.getBrandID() : brandFacade.find(selected_brand));
        pro.setCategoryID(selected_category == null ? p.getCategoryID() : categoriesFacade.find(selected_category));
        pro.setSupplierID(selected_supplier == null ? p.getSupplierID() : suppliersFacade.find(selected_supplier));
        pro.setInventoryStatus(selected_inventorystatus == null ? p.getInventoryStatus() : inventoryStatusFacade.find(selected_inventorystatus));
        if (pro.getAvaliable() == null) {
            pro.setAvaliable(Boolean.TRUE);
        }
        productsFacade.edit(pro);
        return "product";
    }

    public String toggleStatus(Long ID) {
        product = productsFacade.find(ID);
        System.out.println("init id: " + product.getId() + "---------- status: " + product.getAvaliable());
        if (product.getAvaliable()) {
            product.setAvaliable(false);
        } else {
            product.setAvaliable(true);
        }
        productsFacade.edit(product);
        return "employee";
    }

    public String removeProduct(Long ID) {
        productsFacade.remove(productsFacade.find(ID));
        return "product";
    }

    public ProductAdminBean() {
        product = new Products();
        brand = new Brand();
        supplier = new Suppliers();
        category = new Categories();
        inventory = new InventoryStatus();
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

    public InventoryStatusFacadeLocal getInventoryStatusFacade() {
        return inventoryStatusFacade;
    }

    public void setInventoryStatusFacade(InventoryStatusFacadeLocal inventoryStatusFacade) {
        this.inventoryStatusFacade = inventoryStatusFacade;
    }

    public Long getSelected_inventorystatus() {
        return selected_inventorystatus;
    }

    public void setSelected_inventorystatus(Long selected_inventorystatus) {
        this.selected_inventorystatus = selected_inventorystatus;
    }

    public Long getIdpro() {
        return id;
    }

    public void setIdpro(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public InventoryStatus getInventory() {
        return inventory;
    }

    public void setInventory(InventoryStatus inventory) {
        this.inventory = inventory;
    }
}
