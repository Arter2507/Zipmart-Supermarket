/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.customer;


import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.session_beans.CustomersFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.Part;

/**
 *
 * @author phnha
 */
@Named(value = "customerMB")
@SessionScoped
public class CusManagedBean implements Serializable {

    @EJB
    private CustomersFacadeLocal customersFacade;
    
    private Customers customers;
    
    private List <Customers> listCustomer = new ArrayList<>();
    
    private Part fileImage;
    private String keyfind="";
    private Date createdate;
    private String birthdate;
    private String dateError;
    
    public CusManagedBean() {
        customers = new Customers();
        listCustomer = new ArrayList<>();
        customers.setCreatedate(new Date(System.currentTimeMillis()));
    }
    
    //Showall
    public List<Customers> showAllCustomer(){
        return customersFacade.findAll();
    }
    
    //Add
    public String createCus() {
        customersFacade.create(customers);
        return "displaycustomer?faces-redirect=true";
    }
    
    //show detail customer
    public String showDetailCustomerU(long id) {
        customers = customersFacade.find(id);
        return "updatecustomer";
    }
    
    public String showDetailCustomerD(long id) {
        customers = customersFacade.find(id);
        return "deletecustomer";
    }
    
    //Update
    public String updateCus() {
        customersFacade.edit(customers);
        return "displaycustomer?faces-redirect=true";
    }
    //Delete
    public String deleteCus(long id) {
        Customers c = customersFacade.find(id);
        customersFacade.remove(c);
        return "displaycustomer";
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

    public List <Customers> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(List <Customers> listCustomer) {
        this.listCustomer = listCustomer;
    }

    public Part getFileImage() {
        return fileImage;
    }

    public void setFileImage(Part fileImage) {
        this.fileImage = fileImage;
    }

    public String getKeyfind() {
        return keyfind;
    }

    public void setKeyfind(String keyfind) {
        this.keyfind = keyfind;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }
}
