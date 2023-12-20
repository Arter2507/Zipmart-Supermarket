/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.ThresholdAdjustment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TUONG
 */
@Stateless
public class ThresholdAdjustmentFacade extends AbstractFacade<ThresholdAdjustment> implements ThresholdAdjustmentFacadeLocal {

    @PersistenceContext(unitName = "sem4_zipmartPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThresholdAdjustmentFacade() {
        super(ThresholdAdjustment.class);
    }
    
}
