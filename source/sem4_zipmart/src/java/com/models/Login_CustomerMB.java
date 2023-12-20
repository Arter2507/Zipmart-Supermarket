/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.models;

import com.ZipMart.entities.Accounts;
import com.ZipMart.sessionbeans.AccountsFacadeLocal;
import com.ZipMart.sessionbeans.CustomersFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author TUONG
 */
@Named(value = "loginCusMB")
@SessionScoped
public class Login_CustomerMB implements Serializable {
@EJB
    private AccountsFacadeLocal accountsFacade;

    
    @EJB
    private CustomersFacadeLocal customersFacade;

    private String username;
    private String password;
    private Accounts curentUser; 
    public Login_CustomerMB() {
        curentUser = new Accounts();
    }
    
    public String login(){
    curentUser = accountsFacade.validateUser(username, password);
    if (curentUser != null) {
            // User authenticated, store user info in session
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", curentUser);
            return "index";
        } else {
            // Invalid login, show error message
            // You can add a FacesMessage here to display an error message on the login page
            return null;
        }
    }
}
