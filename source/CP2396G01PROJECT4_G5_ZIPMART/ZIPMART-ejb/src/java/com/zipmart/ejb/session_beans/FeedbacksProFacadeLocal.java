/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.FeedbacksPro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface FeedbacksProFacadeLocal {

    void create(FeedbacksPro feedbacksPro);

    void edit(FeedbacksPro feedbacksPro);

    void remove(FeedbacksPro feedbacksPro);

    FeedbacksPro find(Object id);

    List<FeedbacksPro> findAll();

    List<FeedbacksPro> findRange(int[] range);

    int count();
    
}
