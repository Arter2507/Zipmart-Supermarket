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
    public Long findByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Managers.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.and(cb.equal(root.get(Managers_.username), username)));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();
    }

    @Override
    public Managers loadByUsername(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Managers.class);
        cq.where(cb.and(cb.equal(root.get(Managers_.username), username), cb.equal(root.get(Managers_.password), password)));
        Query query = em.createQuery(cq);
        return (Managers) query.getSingleResult();
    }

    @Override
    public long getCountByUsernamePassword(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Managers.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.and(cb.equal(root.get(Managers_.username), username), cb.equal(root.get(Managers_.password), password)));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();
    }

    @Override
    public Managers getfindByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Managers.class);
        cq.select(root);
        cq.where(cb.equal(root.get(Managers_.username), username));
        Query query = em.createQuery(cq);
        return (Managers) query.getSingleResult();
    }

}
