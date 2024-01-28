/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.customer;

import com.group5.zipmart.entities.Customers;
import com.group5.zipmart.sessionbeans.CustomersFacadeLocal;
import com.zipmart.mbean.blog.BlogManagedBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author phnha
 */
@Named(value = "customerMB")
@SessionScoped
public class CusManagedBean implements Serializable {

    @EJB
    private CustomersFacadeLocal customersFacade;
    
    private Customers customers;
    
    private List <Customers> listCustomer = new ArrayList<>();
    
    private Part fileImage;
    private String keyfind="";
    
    final String UPLOAD_DIRECTORY = "../../resources/images/customer";
    
    public CusManagedBean() {
        customers = new Customers();
        listCustomer = new ArrayList<>();
    }
    
    //Showall
    public List<Customers> showAllCustomer(){
        return customersFacade.findAll();
    }
    
    //Upload Image
    //******************************************upload file image ***********************************************
    public void uploadFile() {
        System.out.println("Form has been submitted!");
        System.out.println("fileImage: " + fileImage);
        if (fileImage != null) {
            InputStream content = null;
            try {
                System.out.println("name: " + fileImage.getSubmittedFileName());
                System.out.println("type: " + fileImage.getContentType());
                System.out.println("size: " + fileImage.getSize());
                content = fileImage.getInputStream();
                // Write content to disk or DB.
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext ec = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) ec.getRequest();

                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");

                // constructs path of the directory to save uploaded fileImage  
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY;
                System.out.println("pathhhhhhhhhhhhhhhhhhhh: +++" + uploadFilePath);
                // creates the save directory if it does not exists
                File fileImageSaveDir = new File(uploadFilePath);
                if (!fileImageSaveDir.exists()) {
                    fileImageSaveDir.mkdirs();
                }
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(uploadFilePath + File.separator + fileImage.getSubmittedFileName());
                    content = fileImage.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = content.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                    System.out.println("File uploaded successfully!");
                } catch (Exception e) {
                    e.toString();
                    //fileImageName = "";
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (content != null) {
                        content.close();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(BlogManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Add
    public String createCus() {
        customersFacade.create(customers);
        return "displaycustomer?faces-redirect=true";
    }
    
    //show detail customer
    public String showDetailCustomerU(long id) {
        customers = customersFacade.find(id);
        return "updatecustomer";
    }
    
    public String showDetailCustomerD(long id) {
        customers = customersFacade.find(id);
        return "deletecustomer";
    }
    
    //Update
    public String updateCus() {
        customersFacade.edit(customers);
        return "displaycustomer?faces-redirect=true";
    }
    //Delete
    public String deleteCus(long id) {
        Customers c = customersFacade.find(id);
        customersFacade.remove(c);
        return "displaycustomer";
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

    public List <Customers> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(List <Customers> listCustomer) {
        this.listCustomer = listCustomer;
    }

    public Part getFileImage() {
        return fileImage;
    }

    public void setFileImage(Part fileImage) {
        this.fileImage = fileImage;
    }

    public String getKeyfind() {
        return keyfind;
    }

    public void setKeyfind(String keyfind) {
        this.keyfind = keyfind;
    }
}