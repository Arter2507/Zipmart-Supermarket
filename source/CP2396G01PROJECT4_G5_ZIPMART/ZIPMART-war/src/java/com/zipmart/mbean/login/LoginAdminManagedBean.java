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
import com.zipmart.util.CryptUltil;
import com.zipmart.util.SessionUltil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Named(value = "loginAdminManagedBean")
@SessionScoped
public class LoginAdminManagedBean implements Serializable {

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private ManagersFacadeLocal managersFacade;

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    @Inject
    private LoginAdminManagedBean loginAdminManagedBean;

    private Employees employee;
    private Managers manager;
    private Permissions permission;

    private String username;
    private String login_password;
    private String hash_employee;
    private String hash_manager;
    private String salt_employee;
    private String salt_manager;
    private String fullname;

    private int statusLogin;
    private int check;
    private boolean flag = false;

    private String error_password;

    public LoginAdminManagedBean() {
        employee = new Employees();
        manager = new Managers();
        permission = new Permissions();
    }

    @PostConstruct
    public void init() {
        statusLogin = 6;
    }

    public String login() throws IOException {
        String login = CryptUltil.getCrypt().enCodePass(login_password);
        flag = managersFacade.validate(username, login);
        if (flag) {
            manager = managersFacade.getFindByUsername(username);
            if (manager.getStatus()) {
                HttpSession session = SessionUltil.getSession();
                session.setAttribute("username", username);
                session.setAttribute("id", manager.getId());
                statusLogin = 1;
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/employee/employee.xhtml");
                return null;
            }
        } else if (flag = employeesFacade.validate(username, login)) {
            employee = employeesFacade.getFindByUsername(username);
            if (employee.getStatus()) {
                HttpSession session = SessionUltil.getSession();
                session.setAttribute("username", username);
                session.setAttribute("id", manager.getId());
                statusLogin = 2;
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/partials/index.xhtml");
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "message",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/partials/login.xhtml");
            return null;
        }
        return null;
    }

    public void checkLogin() throws IOException {
        if (statusLogin != 2) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/partials/login.xhtml");
        }
    }

    public void checkLogin1() throws IOException {
        if (statusLogin != 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/partials/login.xhtml");
        }
    }

    public void checkLoginAdminOwner() throws IOException {
        if (statusLogin != 1 && statusLogin != 2) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/partials/login.xhtml");
        }
    }

    public LoginAdminManagedBean getLoginAdminManagedBean() {
        return loginAdminManagedBean;
    }

    public void logoutAdmin() throws IOException {
        statusLogin = 3;
        username = "";
        login_password = "";
        HttpSession session = SessionUltil.getSession();
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/partials/login.xhtml");
    }

    public PermissionsFacadeLocal getPermissionsFacade() {
        return permissionsFacade;
    }

    public void setPermissionsFacade(PermissionsFacadeLocal permissionsFacade) {
        this.permissionsFacade = permissionsFacade;
    }

    public ManagersFacadeLocal getManagersFacade() {
        return managersFacade;
    }

    public void setManagersFacade(ManagersFacadeLocal managersFacade) {
        this.managersFacade = managersFacade;
    }

    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }

    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }

    public void setLoginAdminManagedBean(LoginAdminManagedBean loginAdminManagedBean) {
        this.loginAdminManagedBean = loginAdminManagedBean;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Managers getManager() {
        return manager;
    }

    public void setManager(Managers manager) {
        this.manager = manager;
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

    public String getLoginPassword() {
        return login_password;
    }

    public void setLoginPassword(String login_password) {
        this.login_password = login_password;
    }

    public String getHash_employee() {
        return hash_employee;
    }

    public void setHash_employee(String hash_employee) {
        this.hash_employee = hash_employee;
    }

    public String getHash_manager() {
        return hash_manager;
    }

    public void setHash_manager(String hash_manager) {
        this.hash_manager = hash_manager;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(int statusLogin) {
        this.statusLogin = statusLogin;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getSalt_employee() {
        return salt_employee;
    }

    public void setSalt_employee(String salt_employee) {
        this.salt_employee = salt_employee;
    }

    public String getSalt_manager() {
        return salt_manager;
    }

    public void setSalt_manager(String salt_manager) {
        this.salt_manager = salt_manager;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getError_password() {
        return error_password;
    }

    public void setError_password(String error_password) {
        this.error_password = error_password;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
