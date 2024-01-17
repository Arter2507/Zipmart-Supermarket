/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.EmployeeGenders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface EmployeeGendersFacadeLocal {

    void create(EmployeeGenders employeeGenders);

    void edit(EmployeeGenders employeeGenders);

    void remove(EmployeeGenders employeeGenders);

    EmployeeGenders find(Object id);

    List<EmployeeGenders> findAll();

    List<EmployeeGenders> findRange(int[] range);

    int count();
    
}
