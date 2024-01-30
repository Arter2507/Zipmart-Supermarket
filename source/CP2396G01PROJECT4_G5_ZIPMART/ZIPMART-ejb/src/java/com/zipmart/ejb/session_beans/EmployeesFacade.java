/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.entities.Employees_;
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
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal {

    @PersistenceContext(unitName = "ZIPMART-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }

    @Override
    public long getUserNameEx(String username, boolean status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Employees.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.and(cb.equal(root.get(Employees_.username), username), cb.equal(root.get(Employees_.status), status)));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();
    }

    @Override
    public long findByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Employees.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.and(cb.equal(root.get(Employees_.username), username)));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();
    }

    @Override
    public boolean checkLoginEmployee(String username, String password) {
        boolean flag = false;
        try {
            Query query = em.createQuery("select u from Employees u where u.username =:uname and u.password = :pword");
            query.setParameter("uname", username);
            query.setParameter("pword", password);
            query.getSingleResult();
            flag = true;
        } catch (NoResultException ex) {
            flag = false;
        }
        return flag;
    }   

    @Override
    public List<Employees> findByUsername1(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root r = cq.from(Employees.class);
        cq.select(r);
        cq.where(cb.equal(r.get(Employees_.username), username));
        Query query = em.createQuery(cq);
        return (List<Employees>) query.getResultList();
    }

    @Override
    public long getCountByUsernamePassword(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Employees.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.and(cb.equal(root.get("username"), username), cb.equal(root.get("password"), password)));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();
    }
    
    @Override
    public Employees loadByUsername(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Employees.class);
        cq.select(root);
        cq.where(cb.and(cb.equal(root.get("username"), username), cb.equal(root.get("password"), password)));
        Query query = em.createQuery(cq);
        return (Employees) query.getSingleResult();
    }
}
