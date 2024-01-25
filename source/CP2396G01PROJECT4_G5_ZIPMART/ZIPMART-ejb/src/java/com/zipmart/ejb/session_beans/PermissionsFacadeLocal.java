/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Permissions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface PermissionsFacadeLocal {

    void create(Permissions permissions);

    void edit(Permissions permissions);

    void remove(Permissions permissions);

    Permissions find(Object id);

    List<Permissions> findAll();

    List<Permissions> findRange(int[] range);

    int count();
    
}
