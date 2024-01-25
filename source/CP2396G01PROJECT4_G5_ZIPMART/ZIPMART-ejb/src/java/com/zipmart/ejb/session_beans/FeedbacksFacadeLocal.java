/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Feedbacks;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author phnha
 */
@Local
public interface FeedbacksFacadeLocal {

    void create(Feedbacks feedbacks);

    void edit(Feedbacks feedbacks);

    void remove(Feedbacks feedbacks);

    Feedbacks find(Object id);

    List<Feedbacks> findAll();

    List<Feedbacks> findRange(int[] range);

    int count();
    
}
