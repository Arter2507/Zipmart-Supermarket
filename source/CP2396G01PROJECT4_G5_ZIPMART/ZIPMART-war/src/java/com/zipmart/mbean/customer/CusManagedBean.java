/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.customer;

import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.session_beans.CustomersFacadeLocal;
import com.zipmart.util.CryptUltil;
import com.zipmart.util.FileUltil;
import com.zipmart.util.SessionUltil;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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

    private List<Customers> listCustomer = new ArrayList<>();

    private Part fileImage;
    private String keyfind = "";
    private String imageurl;
    private Date createdate;
    private String birthdate;
    private String dateError;
    private String password;
    private int status_login;
    private String newpass;
    
    private String messagePass;

    private String username;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private String message;

    public CusManagedBean() {
        customers = new Customers();
        listCustomer = new ArrayList<>();
        customers.setCreatedate(new Date(System.currentTimeMillis()));
    }

    //Showall
    public List<Customers> showAllCustomer() {
        return customersFacade.findAll();
    }
    
    public String updatePassword() {
        customers = customersFacade.getFindByUsername(username);
        customers.setOldPass("");
        customers.setNewPass("");
        return "changePass";
    }
    
    public String saveUpDatePassword() {
        Customers c = customers;
        String old = CryptUltil.getCrypt().enCodePass(password);
        
        if (!old.equals(customers.getPassword())) {
            messagePass = "The old password is not correct!";
        } else {
            String newp = CryptUltil.getCrypt().enCodePass(newpass);
            customers.setPassword(newp);
            customersFacade.edit(customers);
        }
        messagePass = "Changed password successfully!";
        return "changePass";
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

    public String showUpdateUsers(String username) throws IOException {
        try {
            username = (String) sessionMap.get("username");
            customers = customersFacade.getFindByUsername(username);
            Long id = customers.getId();
            customers = customersFacade.find(id);
            System.out.println("============================" + customers.toString());
            Long userID = customers.getId();
            return "profile";
        } catch (Exception e) {
            System.out.println("error Profile" + e.getMessage());
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.client/login.xhtml");
        }
        return null;
    }

    public String saveUpdateUsers() {
        Customers c = customers;
        Customers cus = customersFacade.find(c.getId());
        System.out.println("---------------------++++++++++++++++++++" + customers.toString());
        imageurl = FileUltil.getInstance().uploadFile(fileImage);
        customers.setImageURL(imageurl);
        customers.setModifiedate(new Date());
        customers.setModifieby(cus.getFullname());
        customers.setCreatedate(cus.getCreatedate());
        customers.setCreateby(cus.getCreateby());
        customersFacade.edit(customers);
        message = "Update Profile successfully!";
        return "profile";
    }

    public String removeCustomer(Long id) {
        customers = customersFacade.find(id);
        customers.setStatus(Boolean.FALSE);
        status_login = 3;
        username = "";
        password = "";
        HttpSession session = SessionUltil.getSession();
        session.invalidate();
        return "index";
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

    public List<Customers> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(List<Customers> listCustomer) {
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

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus_login() {
        return status_login;
    }

    public void setStatus_login(int status_login) {
        this.status_login = status_login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessagePass() {
        return messagePass;
    }

    public void setMessagePass(String messagePass) {
        this.messagePass = messagePass;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
