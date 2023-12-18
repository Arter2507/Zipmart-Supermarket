/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.models;

import com.entities.Employee;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author TUONG
 */
@Named(value = "employeeService")
@ApplicationScoped
public class EmployeeService implements Serializable {

    private List<Employee> employee;

    public EmployeeService() {
        employee = new ArrayList<>();
        employee.add(new Employee(1000, "f230fh0g3", "Can Tho", "999999999", "1@gmail.com", "employee"));
        employee.add(new Employee(1001, "f230ab0g3", "Can Tho", "999999999", "2@gmail.com", "employee"));
        employee.add(new Employee(1002, "f230ht0g3", "Can Tho", "999999999", "3@gmail.com", "employee"));
        employee.add(new Employee(1003, "f230fa0g3", "Can Tho", "999999999", "4@gmail.com", "employee"));
        employee.add(new Employee(1004, "f230fd0g3", "Can Tho", "999999999", "5@gmail.com", "employee"));
        employee.add(new Employee(1005, "f230gfd0g3", "Can Tho", "999999999", "6@gmail.com", "employee"));
        employee.add(new Employee(1006, "f230fh0g3", "Can Tho", "999999999", "7@gmail.com", "employee"));
        employee.add(new Employee(1007, "f230fh0g3", "Can Tho", "999999999", "8@gmail.com", "employee"));
        employee.add(new Employee(1008, "f230fh0g3", "Can Tho", "999999999", "9@gmail.com", "employee"));
        employee.add(new Employee(1009, "f230fh0g3", "Can Tho", "999999999", "10@gmail.com", "employee"));
        employee.add(new Employee(10010, "f230fh0g3", "Can Tho", "999999999", "11@gmail.com", "employee"));
        employee.add(new Employee(10011, "f230fh0g3", "Can Tho", "999999999", "12@gmail.com", "employee"));
        employee.add(new Employee(10012, "f230fh0g3", "Can Tho", "999999999", "13@gmail.com", "employee"));
        employee.add(new Employee(10013, "f230fh0g3", "Can Tho", "999999999", "14@gmail.com", "employee"));
        employee.add(new Employee(10014, "f230fh0g3", "Can Tho", "999999999", "15@gmail.com", "employee"));
        employee.add(new Employee(10015, "f230fh0g3", "Can Tho", "999999999", "16@gmail.com", "employee"));
        employee.add(new Employee(10016, "f230fh0g3", "Can Tho", "999999999", "17@gmail.com", "employee"));
        employee.add(new Employee(10017, "f230fh0g3", "Can Tho", "999999999", "18@gmail.com", "employee"));
        employee.add(new Employee(10018, "f230fh0g3", "Can Tho", "999999999", "19@gmail.com", "employee"));
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employee);
    }

    public List<Employee> getEmployees(int size) {

        if (size > employee.size()) {
            Random rand = new Random();

            List<Employee> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(employee.size());
                randomList.add(employee.get(randomIndex));
            }

            return randomList;
        } else {
            return new ArrayList<>(employee.subList(0, size));
        }

    }

    public List<Employee> getClonedEmployees(int size) {
        List<Employee> results = new ArrayList<>();
        List<Employee> originals = getEmployees(size);
        for (Employee original : originals) {
            results.add(original.clone());
        }

        // make sure to have unique codes
        for (Employee employee : results) {
            employee.setName(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        }

        return results;
    }

}
