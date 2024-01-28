/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Feedbacks;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author phnha
 */
@Stateless
public class FeedbacksFacade extends AbstractFacade<Feedbacks> implements FeedbacksFacadeLocal {

    @PersistenceContext(unitName = "ZIPMART-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbacksFacade() {
        super(Feedbacks.class);
    }
    
}