/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.BlogFeedBacks;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TUONG
 */
@Stateless
public class BlogFeedBacksFacade extends AbstractFacade<BlogFeedBacks> implements BlogFeedBacksFacadeLocal {

    @PersistenceContext(unitName = "sem4_zipmartPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlogFeedBacksFacade() {
        super(BlogFeedBacks.class);
    }
    
}
