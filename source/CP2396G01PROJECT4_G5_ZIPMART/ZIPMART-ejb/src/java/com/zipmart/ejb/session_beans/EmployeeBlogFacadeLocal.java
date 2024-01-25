/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.EmployeeBlog;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface EmployeeBlogFacadeLocal {

    void create(EmployeeBlog employeeBlog);

    void edit(EmployeeBlog employeeBlog);

    void remove(EmployeeBlog employeeBlog);

    EmployeeBlog find(Object id);

    List<EmployeeBlog> findAll();

    List<EmployeeBlog> findRange(int[] range);

    int count();
    
}
