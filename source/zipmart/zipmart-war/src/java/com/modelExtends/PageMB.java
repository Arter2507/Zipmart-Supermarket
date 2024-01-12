/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.modelExtends;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author TUONG
 */
@Named(value = "pageMB")
@SessionScoped
public class PageMB implements Serializable {

    private String aboutPage = "/views/about.xhtml";
    public PageMB() {
    }

    public String getAboutPage() {
        return aboutPage;
    }

    public void setAboutPage(String aboutPage) {
        this.aboutPage = aboutPage;
    }
    
}
