/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.models;

import com.entities.Employee;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author TUONG
 */
@Named(value = "crudView")
@ViewScoped
public class CrudView implements Serializable {

    private List<Employee> employee;

    private Employee selectedEmployee;

    private List<Employee> selectedEmployees;

    private EmployeeService employeeService;

    public CrudView() {
        this.employee = this.employeeService.getClonedEmployees(100);
    }

    public List<Employee> getEmployees() {
        return employee;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<Employee> getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(List<Employee> selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }

    public void openNew() {
        this.selectedEmployee = new Employee();
    }

    public void saveEmployee() {
        if (this.selectedEmployee.getName() == null) {
            this.selectedEmployee.setName(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.employee.add(this.selectedEmployee);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageEmployeeDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteEmployee() {
        this.employee.remove(this.selectedEmployee);
        this.selectedEmployees.remove(this.selectedEmployee);
        this.selectedEmployee = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedEmployees()) {
            int size = this.selectedEmployees.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedEmployees() {
        return this.selectedEmployees != null && !this.selectedEmployees.isEmpty();
    }

    public void deleteSelectedEmployees() {
        this.employee.removeAll(this.selectedEmployees);
        this.selectedEmployees = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employees Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtEmployees').clearFilters()");
    }
    
}
