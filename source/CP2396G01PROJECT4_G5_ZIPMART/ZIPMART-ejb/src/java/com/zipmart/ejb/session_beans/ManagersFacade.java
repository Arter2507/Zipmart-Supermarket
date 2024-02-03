/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Managers;
import com.zipmart.ejb.entities.Managers_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author TUONG
 */
@Stateless
public class ManagersFacade extends AbstractFacade<Managers> implements ManagersFacadeLocal {

    @PersistenceContext(unitName = "ZIPMART-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagersFacade() {
        super(Managers.class);
    }

    @Override
    public Managers getFindByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root r = cq.from(Managers.class);
        cq.select(r);
        cq.where(cb.equal(r.get("username"), username));
        Query query = em.createQuery(cq);
        return (Managers) query.getSingleResult();
    }

    @Override
    public boolean validate(String username, String password) {
        boolean flag = false;
        try {
            Query query = em.createQuery("SELECT m FROM Managers m WHERE m.username =:uname and m.password = :pword");
            query.setParameter("uname", username);
            query.setParameter("pword", password);
            query.getSingleResult();
            flag = true;
        } catch (Exception e) {
            System.out.println("error->>>>>> " + e.getMessage());
            System.out.println(username);
            flag = false;
        }
        return flag;
    }

    @Override
    public Long findByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Managers.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.and(cb.equal(root.get("username"), username)));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();

    }
}
