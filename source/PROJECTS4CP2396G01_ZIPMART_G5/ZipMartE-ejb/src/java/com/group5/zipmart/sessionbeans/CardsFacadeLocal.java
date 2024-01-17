/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.Cards;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface CardsFacadeLocal {

    void create(Cards cards);

    void edit(Cards cards);

    void remove(Cards cards);

    Cards find(Object id);

    List<Cards> findAll();

    List<Cards> findRange(int[] range);

    int count();
    
}
