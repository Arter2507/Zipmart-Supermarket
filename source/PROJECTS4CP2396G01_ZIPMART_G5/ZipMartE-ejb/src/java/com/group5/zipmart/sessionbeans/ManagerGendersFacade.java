/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group5.zipmart.sessionbeans;

import com.group5.zipmart.entities.ManagerGenders;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TUONG
 */
@Stateless
public class ManagerGendersFacade extends AbstractFacade<ManagerGenders> implements ManagerGendersFacadeLocal {

    @PersistenceContext(unitName = "ZipMartE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagerGendersFacade() {
        super(ManagerGenders.class);
    }
    
}
