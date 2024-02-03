/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.brand;

import com.zipmart.ejb.entities.Brand;
import com.zipmart.ejb.entities.Suppliers;
import com.zipmart.ejb.session_beans.BrandFacadeLocal;
import com.zipmart.ejb.session_beans.SuppliersFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "brandAdminBean")
@RequestScoped
public class BrandAdminBean {

    @EJB
    private SuppliersFacadeLocal suppliersFacade;

    @EJB
    private BrandFacadeLocal brandFacade;

    private Brand brand;
    private Suppliers supplier;

    private String name;
    private String address;

    private Long selected_supp;
    private String message;

    public BrandAdminBean() {
        brand = new Brand();
        supplier = new Suppliers();
    }

    public List<Brand> showAllBrand() {
        return brandFacade.findAll();
    }

    public List<Suppliers> showAllSup() {
        return suppliersFacade.findAll();
    }

    public String addBrand() {
        brand.setSupplierID(suppliersFacade.find(selected_supp));
        brand.setBrandName(name);
        brand.setAddress(address);
        brand.setCreatedate(new Date());
        brandFacade.create(brand);
        return "brand";
    }

    public String showBrand(Long id) {
        brand = brandFacade.find(id);

        supplier = suppliersFacade.find(brand.getSupplierID().getId());
        selected_supp = supplier.getId();
        return "updateBrand";
    }

    public String detailBrand(Long id) {
        brand = brandFacade.find(id);
        return "detailProduct";
    }

    public String updateProcess() {
        Brand br = brand;
        Brand b = brandFacade.find(br.getId());
        brand.setCreatedate(b.getCreatedate());
        brand.setModifiedate(new Date());
        brand.setSupplierID(selected_supp == null ? b.getSupplierID() : suppliersFacade.find(selected_supp));
        brandFacade.edit(brand);
        return "brand";
    }

    public String removeProcess(Long id) {
        brand = brandFacade.find(id);
        if (!brand.getSupplierID().getBrandCollection().isEmpty()) {
            message = "Brand cannot delete because have supplier.";
            FacesMessage message = new FacesMessage("Brand cannot delete because have supplier.");
            FacesContext.getCurrentInstance().addMessage("message", message);
            return "brand";
        } else {
            brandFacade.remove(brand);
            return "brand";
        }
    }

    public SuppliersFacadeLocal getSuppliersFacade() {
        return suppliersFacade;
    }

    public void setSuppliersFacade(SuppliersFacadeLocal suppliersFacade) {
        this.suppliersFacade = suppliersFacade;
    }

    public BrandFacadeLocal getBrandFacade() {
        return brandFacade;
    }

    public void setBrandFacade(BrandFacadeLocal brandFacade) {
        this.brandFacade = brandFacade;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSelected_supp() {
        return selected_supp;
    }

    public void setSelected_supp(Long selected_supp) {
        this.selected_supp = selected_supp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
