/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.Invoices;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface InvoicesFacadeLocal {

    void create(Invoices invoices);

    void edit(Invoices invoices);

    void remove(Invoices invoices);

    Invoices find(Object id);

    List<Invoices> findAll();

    List<Invoices> findRange(int[] range);

    int count();
    
}
