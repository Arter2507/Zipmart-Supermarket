/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Encrypt;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface EncryptFacadeLocal {

    void create(Encrypt encrypt);

    void edit(Encrypt encrypt);

    void remove(Encrypt encrypt);

    Encrypt find(Object id);

    List<Encrypt> findAll();

    List<Encrypt> findRange(int[] range);

    int count();
    
}
