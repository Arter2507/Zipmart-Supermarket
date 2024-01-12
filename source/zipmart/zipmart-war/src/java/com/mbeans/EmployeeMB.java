/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.ZipMart.entities.Employees;
import com.ZipMart.sessionbeans.EmployeesFacadeLocal;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "employeeMB")
@RequestScoped
public class EmployeeMB {

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private String fullname;
    private String email;
    private String address;
    private String phone;
    private String notes;
    private Integer employeeID;
    private Employees employee;
    private Timestamp birthDate;

    public EmployeeMB() {
        employee = new Employees();
    }

    public List<Employees> showListEmp() {
        return employeesFacade.findAll();
    }

//    public String showUpdateEmp(Integer id) {
//        employee = employeesFacade.find(id);
//        employeeID = employee.getEmployeeID();
//        System.out.println(employeeID);
//        return "updateEmployee";
//    }

    public String test(){
        return "loginAdmin";
    }
    
    public String deleteEmployees(int id) {
        employeesFacade.remove(employeesFacade.find(id));
        return "displayEmployee";
    }

    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }

    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getEmployeeId() {
        return employeeID;
    }

    public void setEmployeeId(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

}
