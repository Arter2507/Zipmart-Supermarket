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
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;

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

    private Employees employee = new Employees();
    private Managers manager = new Managers();
    private Permissions permission = new Permissions();

    private String username;
    private String login_password;
    private String hash_employee;
    private String hash_manager;
    private String salt_employee;
    private String salt_manager;
    private String fullname;

    private int statusLogin;
    private int check;

    private String error_password;

    public LoginAdminManagedBean() {
    }

    @PostConstruct
    public void init() {
        statusLogin = 6;
    }

    public void processLogin() throws IOException {
        if (checkPassword() == 1 || checkPassword() == 2) {
            long checkManager = managersFacade.getCountByUsernamePassword(username, login_password);
            long checkEmployee = employeesFacade.getCountByUsernamePassword(username, login_password);
            if (checkManager > 0) {
                System.out.println(checkManager);
                Managers mng = managersFacade.loadByUsername(username, login_password);
                if (mng.getStatus() != false && mng.getManagerGroup().getId() == 1) {
                    statusLogin = 1;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/manager/manager.xhtml");
                } else {
                    statusLogin = 3;
                }
            } else if (checkEmployee > 0) {
                statusLogin = 2;
                System.out.println(checkEmployee);
                Employees emp = employeesFacade.loadByUsername(username, login_password);
                if (emp.getStatus() != false && emp.getEmployeeGroup().getId() == 2) {
                    statusLogin = 1;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/employee/employee.xhtml");
                } else {
                    statusLogin = 3;
                }
            } else {
                statusLogin = 3;
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/login.xhtml");
            }
        }
    }

    public int checkPassword() throws IOException {
        check = 6;
        employee = employeesFacade.getfindByUsername(username);
        manager = managersFacade.getfindByUsername(username);
        Employees emp = employee;
        Managers manage = manager;
        hash_employee = emp.getPassword();
        hash_manager = manage.getPassword();
        String login = CryptUltil.getCrypt().enCodePass(login_password);
        System.out.println("===========" + hash_employee + "===========" + emp + login);
        System.out.println("===========" + hash_manager + "===========" + manage + login);
        if (login.equals(hash_manager)) {
            System.out.println(CryptUltil.getCrypt().checkPass(login, hash_manager));
            System.out.println("Match");
            check = 1;
        } else if (login.equals(hash_employee)) {
            System.out.println(CryptUltil.getCrypt().checkPass(login, hash_manager));
            System.out.println("Match");
            check = 2;
        } else if (hash_employee == null || hash_manager == null) {
            System.out.println("Password Null");
            check = 4;
            error_password = "Incorrect Password";
            FacesContext.getCurrentInstance().addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR, error_password, null));
        } else {
            System.out.println("Incorrect Password");
            error_password = "Incorrect Password";
            check = 5;
            FacesContext.getCurrentInstance().addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR, error_password, null));
        }
//        if (BCrypt.checkpw(password, hash_employee)) {
//            check = 2;
//            System.out.println("Password Employee Match: " + password + "=======" + hash_employee);
//        } else if (BCrypt.checkpw(password, combined_manager)) {
//            check = 1;
//            System.out.println("Password Manager Match: " + password + "=======" + hash_manager);
//        } else {
//            check = 4;
//            System.out.println("Password incorrect");
//        }
        return check;
    }

    public void checkEmp() throws IOException {
        employee = employeesFacade.getfindByUsername(username);
        Employees emp = employee;
        hash_employee = emp.getPassword();
        String login = CryptUltil.getCrypt().enCodePass(login_password);

        System.out.println("===========" + hash_employee + "===========" + emp + login);
//        if (hash_employee == null) {
//            System.out.println("Password Null");
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/login.xhtml");
//        } else 
        if (login.equals(hash_employee)) {
            System.out.println(CryptUltil.getCrypt().checkPass(login, hash_employee));
            System.out.println("Match");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/employee/employee.xhtml");

        } else {
            System.out.println(CryptUltil.getCrypt().checkPass(login, hash_employee));
            System.out.println("Doesn't Match");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/login.xhtml");
        }
    }

    public void checkLogin() throws IOException {
        if (statusLogin != 2) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/login.xhtml");
        }
    }

    public void checkLogin1() throws IOException {
        if (statusLogin != 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/login.xhtml");
        }
    }

    public void checkLoginAdminOwner() throws IOException {
        if (statusLogin != 1 && statusLogin != 2) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/login.xhtml");
        }
    }

    public LoginAdminManagedBean getLoginAdminManagedBean() {
        return loginAdminManagedBean;
    }

    public void logoutAdmin() throws IOException {
        statusLogin = 3;
        username = "";
        login_password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/login.xhtml");
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
}
