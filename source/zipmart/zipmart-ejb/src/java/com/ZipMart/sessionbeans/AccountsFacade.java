/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.Accounts;
import com.ZipMart.entities.Accounts_;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
public class AccountsFacade extends AbstractFacade<Accounts> implements AccountsFacadeLocal {

    @PersistenceContext(unitName = "zipmart-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountsFacade() {
        super(Accounts.class);
    }

    @Override
    public long loginAccount(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root r = cq.from(Accounts.class);
        cq.select(cb.count(r.get(Accounts_.username)));
        cq.where(cb.and(cb.equal(r.get(Accounts_.username), username), cb.equal(r.get(Accounts_.password), password)));
        Query query = em.createQuery(cq);
        return (Long) query.getSingleResult();
    }

    @Override
    public boolean checkLogin(String username, String password) {
        boolean flag = false;
        try {
            Query query = em.createQuery("select ac from Accounts ac where ac.username =:uname and ac.password = :pword");
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
    public Accounts findByUsername(String username) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root r = cq.from(Accounts.class);
            cq.select(r);
            cq.where(cb.equal(r.get(Accounts_.username), username));
            Query query = em.createQuery(cq);
            return (Accounts) query.getSingleResult();
        } catch (NoResultException e) {
            // Xử lý trường hợp không tìm thấy username
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your username not exist", null));
            return null;
        } catch (Exception e) {
            // Xử lý các lỗi khác
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred while retrieving account", null));
            throw new EJBException(e);
        }
    }

}
