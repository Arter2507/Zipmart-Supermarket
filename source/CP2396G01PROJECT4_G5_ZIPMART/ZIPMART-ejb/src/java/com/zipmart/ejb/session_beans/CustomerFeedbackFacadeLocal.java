/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.CustomerFeedback;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface CustomerFeedbackFacadeLocal {

    void create(CustomerFeedback customerFeedback);

    void edit(CustomerFeedback customerFeedback);

    void remove(CustomerFeedback customerFeedback);

    CustomerFeedback find(Object id);

    List<CustomerFeedback> findAll();

    List<CustomerFeedback> findRange(int[] range);

    int count();
    
}
