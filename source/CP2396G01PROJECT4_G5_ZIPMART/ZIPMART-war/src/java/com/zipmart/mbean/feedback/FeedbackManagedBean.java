/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.feedback;

import com.zipmart.ejb.entities.Feedbacks;
import com.zipmart.ejb.entities.Products;
import com.zipmart.ejb.session_beans.FeedbacksFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private String currentDateFormatted;
    private Date createdate;

    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;
    

    private List <Products> listProduct = new ArrayList<>();
    private List <Feedbacks> listFeedback = new ArrayList<>();
    
    
    
    public FeedbackManagedBean() {
        feedbacks = new Feedbacks();
        listFeedback = new ArrayList<>();
        createdate = new Date();
        feedbacks.setCreatedate(new Date(System.currentTimeMillis()));
    }
    
    //Add
    public String createFeedback() {
        feedbacksFacade.create(feedbacks);
        return "feedbackdone";
    }
    
    //ShowallFeedback
    public List<Feedbacks> showallFeedback(){
        return feedbacksFacade.findAll();
    }
    
    //Showdetailfeedback
    public String showDetailFeedback(long id) {
        feedbacks = feedbacksFacade.find(id);
        return "displayfeedbackdetail";
    }
    
    //showdetaifeedback
    public String showDetailFeedbackU(long id){
        feedbacks = feedbacksFacade.find(id);
        return "updatefeedback";
    }
    
    //Update
    public String updateFeedback(){
        feedbacksFacade.edit(feedbacks);
        return "displayfeedback";
    }
    
    //Delete
    public String deleteFeedback(long id) {
        Feedbacks f = feedbacksFacade.find(id);
        feedbacksFacade.remove(f);
        return "displayfeedback";
    }

    public Feedbacks getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedbacks feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getCurrentDateFormatted() {
        return currentDateFormatted;
    }

    public void setCurrentDateFormatted(String currentDateFormatted) {
        this.currentDateFormatted = currentDateFormatted;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public List <Products> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List <Products> listProduct) {
        this.listProduct = listProduct;
    }

    public List <Feedbacks> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List <Feedbacks> listFeedback) {
        this.listFeedback = listFeedback;
    }

    public FeedbacksFacadeLocal getFeedbacksFacade() {
        return feedbacksFacade;
    }

    public void setFeedbacksFacade(FeedbacksFacadeLocal feedbacksFacade) {
        this.feedbacksFacade = feedbacksFacade;
    }
    
}
