/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.login;

import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.entities.Permissions;
import com.zipmart.ejb.session_beans.CustomersFacadeLocal;
import com.zipmart.ejb.session_beans.PermissionsFacadeLocal;
import com.zipmart.util.CryptUltil;
import com.zipmart.util.SessionUltil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TUONG
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    private Customers customer = new Customers();
    private Permissions permission = new Permissions();

    private String username;
    private String password;
    private String confirm_password;
    private String hash_password;

    private Long id_group;

    private int status_login;

    public LoginManagedBean() {
    }

    @PostConstruct
    public void init() {
        status_login = 4;
        id_group = 3L;
    }

    public String processSignup() {
            Customers cus = customer;
            Permissions per = permissionsFacade.find(id_group);
            if (confirm_password.equals(password)) {
                hash_password = CryptUltil.getCrypt().enCodePass(confirm_password);
                cus.setPassword(hash_password);
                status_login = 2;
            } else {
                System.out.println("Password and Confirm Password are not same");
            }
            cus.setFullname(username);
            cus.setUsername(username);
            cus.setCustomerGroup(per);
            cus.setStatus(Boolean.TRUE);
            cus.setCreatedate(new Date(System.currentTimeMillis()));
            cus.setCreateby(cus.getFullname());
            customersFacade.create(cus);
            return "login";
    }

    public String processLogin() throws IOException {
        customer = customersFacade.getFindByUsername(username);
        Customers cus_login = customer;
        confirm_password = cus_login.getPassword();
        hash_password = CryptUltil.getCrypt().enCodePass(password);
        if (hash_password.equals(confirm_password)) {
            status_login = 1;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("statuslogin", status_login);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.client/index.xhtml");
        }
        return null;
    }

    public String login() {
        hash_password = CryptUltil.getCrypt().enCodePass(password);
        boolean valid = customersFacade.validate(username, hash_password);
        if (valid) {
            customer = customersFacade.getFindByUsername(username);
            if (customer.getStatus()) {
                HttpSession session = SessionUltil.getSession();
                session.setAttribute("username", username);
                session.setAttribute("id", customer.getId());
                status_login = 1;
                return "index";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        "messege",
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Your Account is Block",
                                "Please enter correct username and Password"));
                return "login";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "messege",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    public void logout() throws IOException {
        status_login = 3;
        username = "";
        password = "";
        HttpSession session = SessionUltil.getSession();
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.client/login.xhtml");
    }

    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

    public PermissionsFacadeLocal getPermissionsFacade() {
        return permissionsFacade;
    }

    public void setPermissionsFacade(PermissionsFacadeLocal permissionsFacade) {
        this.permissionsFacade = permissionsFacade;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Permissions getPermission() {
        return permission;
    }

    public void setPermission(Permissions permission) {
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }

    public Long getId_group() {
        return id_group;
    }

    public void setId_group(Long id_group) {
        this.id_group = id_group;
    }

    public int getStatus_login() {
        return status_login;
    }

    public void setStatus_login(int status_login) {
        this.status_login = status_login;
    }
}
