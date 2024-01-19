/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.login;

import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.entities.Managers;
import com.zipmart.ejb.entities.Permissions;
import com.zipmart.ejb.session_beans.EmployeesFacadeLocal;
import com.zipmart.ejb.session_beans.ManagersFacadeLocal;
import com.zipmart.ejb.session_beans.PermissionsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

@Named(value = "loginAdminManagedBean")
@SessionScoped
public class LoginAdminManagedBean implements Serializable {

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private ManagersFacadeLocal managersFacade;

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private Employees employee = new Employees();
    private Managers manager = new Managers();
    private Permissions permission = new Permissions();
    
    private String username;
    private String password;
    private String fullname;
    
    private int statusLogin;
    
    public LoginAdminManagedBean() {
    }
    
}
