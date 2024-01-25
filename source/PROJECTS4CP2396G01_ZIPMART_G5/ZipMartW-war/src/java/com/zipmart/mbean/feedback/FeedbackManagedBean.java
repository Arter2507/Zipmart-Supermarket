/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.feedback;
import com.group5.zipmart.entities.Feedbacks;
import com.group5.zipmart.entities.Products;
import com.group5.zipmart.entities.Products;
import com.group5.zipmart.sessionbeans.FeedbacksFacadeLocal;
import com.group5.zipmart.sessionbeans.ProductsFacade;
import com.group5.zipmart.sessionbeans.ProductsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author phnha
 */
@Named(value = "feedbackMB")
@SessionScoped
public class FeedbackManagedBean implements Serializable {
    
    private Feedbacks feedbacks;

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;
    

    List <Products> listProduct = new ArrayList<>();
    List <Feedbacks> listFeedback = new ArrayList<>();
    
    
    
    public FeedbackManagedBean() {
    }
    
    //ShowallProduct
    public List<Products> showAllProductF(){
        return productsFacade.findAll();
    }
    
    //ShowallFeedback
    public String showDetailFeedback(long id) {
        feedbacks = feedbacksFacade.find(id);
        return "displayfeedbackdetail";
    }

    public Feedbacks getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedbacks feedbacks) {
        this.feedbacks = feedbacks;
    }
    
}
