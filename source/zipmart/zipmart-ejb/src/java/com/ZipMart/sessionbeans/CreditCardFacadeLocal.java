/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.CreditCard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface CreditCardFacadeLocal {

    void create(CreditCard creditCard);

    void edit(CreditCard creditCard);

    void remove(CreditCard creditCard);

    CreditCard find(Object id);

    List<CreditCard> findAll();

    List<CreditCard> findRange(int[] range);

    int count();
    
}
