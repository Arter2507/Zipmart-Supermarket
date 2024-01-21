/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.customer;

import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.session_beans.CustomerCardFacadeLocal;
import com.zipmart.ejb.session_beans.CustomerGendersFacadeLocal;
import com.zipmart.ejb.session_beans.CustomersFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author TUONG
 */
@Named(value = "customerAdminBean")
@RequestScoped
public class CustomerAdminBean {

    @EJB
    private CustomerGendersFacadeLocal customerGendersFacade;

    @EJB
    private CustomersFacadeLocal customersFacade;

    private Customers customer = new Customers();

    private long genderValue;
    private String genderLabel;
    public CustomerAdminBean() {
    }

    public List<Customers> showAdminCus() {
        List<Customers> listCus = new ArrayList<>();
        for (Customers customer : customersFacade.findAll()) {
            if (customer.getStatus() == null) {
                customer.setStatus(Boolean.TRUE);
            }
            if (customer.getStatus()) {
                listCus.add(customer);
            }
        }
        return listCus;
    }
    
    public String showDetails(Long id) {
        customer = customersFacade.find(id);
        id = customer.getId();
        genderValue = customer.getCustomerGender(); 
        switch ((int) genderValue) {
            case 1:
                genderLabel = "Male";
                break;
            case 2:
                genderLabel = "Female";
                break;
            case 3:
                genderLabel = "Other";
                break;
            case 4:
                genderLabel = "Not Set";
                break;
            default:
                genderLabel = "Unknow";
                break;
        }
        System.out.println("====== ID Customer ========== " + id + "=========" + genderValue + "========" + genderLabel);
        return "detailCustomer";
    }

    public String activeStatus(Long ID) {
        customer = customersFacade.find(ID);
        System.out.println("init id: " + customer.getId() + "---------- status: " + customer.getStatus());
        if (customer.getStatus()) {
            customer.setStatus(false);
        } else {
            customer.setStatus(true);
        }
        customersFacade.edit(customer);
        return "customer";
    }
    
    public CustomerGendersFacadeLocal getCustomerGendersFacade() {
        return customerGendersFacade;
    }

    public void setCustomerGendersFacade(CustomerGendersFacadeLocal customerGendersFacade) {
        this.customerGendersFacade = customerGendersFacade;
    }

    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public long getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(long genderValue) {
        this.genderValue = genderValue;
    }

    public String getGenderLabel() {
        return genderLabel;
    }

    public void setGenderLabel(String genderLabel) {
        this.genderLabel = genderLabel;
    }

}
