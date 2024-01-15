/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.employee;

import com.group5.zipmart.entities.Employees;
import com.group5.zipmart.sessionbeans.AccountsFacadeLocal;
import com.group5.zipmart.sessionbeans.EmployeesFacadeLocal;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


@Named(value = "empManagedBean")
@RequestScoped
public class EmpManagedBean {

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private EmployeesFacadeLocal employeesFacade;
    
    private Employees employee;
    
    private long id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String imgUrl;
    private String address;
    private String notes;
    private String phone;
    private Timestamp birthday;
    
    public EmpManagedBean() {
        employee = new Employees();
    }

    // Get All Data
    public List<Employees> showEmpList(){
        return employeesFacade.findAll();
    }
    
    // Update Employee
    
    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }

    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public AccountsFacadeLocal getAccountsFacade() {
        return accountsFacade;
    }

    public void setAccountsFacade(AccountsFacadeLocal accountsFacade) {
        this.accountsFacade = accountsFacade;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
}
