/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.Categories;
import com.zipmart.ejb.entities.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author TUONG
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal {

    @PersistenceContext(unitName = "ZIPMART-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }

    @Override
    public List<Products> getFeaturedProducts() {
// 1. Create CriteriaBuilder and CriteriaQuery instances
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);

// 2. Specify the root entity
        Root<Products> root = cq.from(Products.class);

// 3. Select all products
        cq.select(root);

// 4. Order by discount DESC
        cq.orderBy(cb.desc(root.get("discount")));

        // 5. Get the result list
        TypedQuery<Products> query = em.createQuery(cq);
        List<Products> results = query.getResultList();

        return results;
    }

    @Override
    public List<Products> getProSameCate(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> root = cq.from(Products.class);

        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Products> subRoot = subquery.from(Products.class);
        subquery.select(subRoot.get("categoryID").get("id").as(Long.class));
        subquery.where(cb.equal(subRoot.get("id"), id));

        Predicate condition = root.get("categoryID").get("id").in(subquery);
        cq.where(condition);

        Query query = em.createQuery(cq);
        return query.getResultList();
//        return null;
    }   

    @Override
    public List<Products> getProductBest() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> root = cq.from(Products.class);
        cq.select(root);
        cq.orderBy(cb.desc(root.get("unitPrice")));
        TypedQuery<Products> query = em.createQuery(cq);
        query.setMaxResults(6);
        List<Products> results = query.getResultList();
        return results;
    }

    @Override
    public List<Products> findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> root = cq.from(Products.class);
        cq.select(root);
        cq.where(cb.like(cb.lower(root.get("productName").as(String.class)), "%" + name + "%"));
        TypedQuery<Products> query = em.createQuery(cq);
        return query.getResultList();
    }

}
