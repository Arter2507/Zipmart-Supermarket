/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.supplier;

import com.zipmart.ejb.entities.Suppliers;
import com.zipmart.ejb.session_beans.SuppliersFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "supplierAdminBean")
@RequestScoped
public class SupplierAdminBean {

    @EJB
    private SuppliersFacadeLocal suppliersFacade;

    private Suppliers supplier;

    private String company_name;
    private String cotact_name;
    private String contact_title;
    private String address;
    private String city;
    private String postal_code;
    private String phone;
    private String fax;
    private String home_page;
    private String description;
    private String mess;

    public SupplierAdminBean() {
        supplier = new Suppliers();
    }

    public List<Suppliers> showAllSup() {
        return suppliersFacade.findAll();
    }

    public String showDetail(Long id) {
        supplier = suppliersFacade.find(id);
        return "detailSupplier";
    }

    public String addSup() {
        supplier.setCompanyName(company_name);
        supplier.setContactName(cotact_name);
        supplier.setContactTitle(contact_title);
        supplier.setAddress(address);
        supplier.setCity(city);
        supplier.setPhone(phone);
        supplier.setFax(fax);
        supplier.setPostalCode(postal_code);
        supplier.setHomePage(home_page);
        supplier.setDescription(description);
        supplier.setCreatedate(new Date());
        suppliersFacade.create(supplier);
        return "supplier";
    }

    public String showUpdate(Long id) {
        supplier = suppliersFacade.find(id);
        return "updateSupplier";
    }

    public String updateProcess() {
        Suppliers sup = supplier;
        supplier.setCreatedate(sup.getCreatedate());
        supplier.setModifiedate(new Date());
        suppliersFacade.edit(supplier);
        return "supplier";
    }

    public String removeProcess(Long id) {
        supplier = suppliersFacade.find(id);
        if (!supplier.getProductsCollection().isEmpty()) {
            mess = "Supplier cannot delete because have product.";
            FacesMessage message = new FacesMessage("Supplier cannot delete because have product.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (supplier.getBrandCollection() != null) {
            mess= "Supplier cannot delete because have brand.";
            FacesMessage message = new FacesMessage("Supplier cannot delete because have brand.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            suppliersFacade.remove(supplier);
        }
        return "supplier";
    }

    public SuppliersFacadeLocal getSuppliersFacade() {
        return suppliersFacade;
    }

    public void setSuppliersFacade(SuppliersFacadeLocal suppliersFacade) {
        this.suppliersFacade = suppliersFacade;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCotact_name() {
        return cotact_name;
    }

    public void setCotact_name(String cotact_name) {
        this.cotact_name = cotact_name;
    }

    public String getContact_title() {
        return contact_title;
    }

    public void setContact_title(String contact_title) {
        this.contact_title = contact_title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHome_page() {
        return home_page;
    }

    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
