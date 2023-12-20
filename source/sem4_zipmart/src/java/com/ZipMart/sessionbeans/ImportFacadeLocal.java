/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.Import;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface ImportFacadeLocal {

    /*void create(Import import);
    
    void edit(Import import);
    
    void remove(Import import)*/;

    Import find(Object id);

    List<Import> findAll();

    List<Import> findRange(int[] range);

    int count();
    
}
