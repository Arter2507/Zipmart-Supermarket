/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Genders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface GendersFacadeLocal {

    void create(Genders genders);

    void edit(Genders genders);

    void remove(Genders genders);

    Genders find(Object id);

    List<Genders> findAll();

    List<Genders> findRange(int[] range);

    int count();
    
}
