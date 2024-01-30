/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.manager;

import com.zipmart.ejb.entities.Managers;
import com.zipmart.ejb.session_beans.ManagersFacadeLocal;
import com.zipmart.ejb.session_beans.PermissionsFacadeLocal;
import com.zipmart.util.CryptUltil;
import com.zipmart.util.FileUltil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author TUONG
 */
@Named(value = "managerBean")
@RequestScoped
public class ManagerBean {

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private ManagersFacadeLocal managersFacade;

    private Managers manager = new Managers();

    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String new_password;
    private String phone;
    private String email;
    private String address;
    private boolean status = true;

    private String pepper_pass = CryptUltil.getCrypt().getPepper_pass();
    private String salt = CryptUltil.getCrypt().getSalt_pass();
    private String hashPassword;

    private Part file;
    private String imageURL;

    private short selected_gender;
    private String genderLabel;
    private String user_message;
    private String message_delete;

    public ManagerBean() {
    }

    // Get All Data
    public List<Managers> showAll() {
        List<Managers> listMg = new ArrayList<>();
        for (Managers manager : managersFacade.findAll()) {
            if (manager.getStatus() == null) {
                manager.setStatus(Boolean.TRUE);
            }
            if (manager.getStatus()) {
                listMg.add(manager);
            }
        }
        return listMg;
    }

    // Show Edit
    public String showListID(Long id) {
        manager = managersFacade.find(id);
        id = manager.getId();
        System.out.println("====== ID Manager ========== " + id);
        return "profileEdit";
    }

    // Show Detail
    public String showDetails(Long id) {
        manager = managersFacade.find(id);
        id = manager.getId();
        selected_gender = manager.getManagerGender();
        switch (selected_gender) {
            case 1:
                genderLabel = "Male";
                break;
            case 2:
                genderLabel = "Female";
                break;
            case 3:
                genderLabel = "Other";
                break;
            case 4:
                genderLabel = "Not Set";
                break;
            default:
                genderLabel = "Unknow";
                break;
        }
        System.out.println("====== ID Manager ========== " + id + "=========" + "========" + genderLabel);
        return "profile";
    }

    // Add Manager
    public String createManager() {
        long checkUser = managersFacade.findByUsername(username);
        if (checkUser > 0 && status == true) {
            user_message = "Username already exists!";
            FacesContext.getCurrentInstance().addMessage("username", new FacesMessage(FacesMessage.SEVERITY_ERROR, user_message, null));
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addManager";
        } else if (checkUser > 0 && status == false) {
            user_message = "Username already exists!";
            FacesContext.getCurrentInstance().addMessage("username", new FacesMessage(FacesMessage.SEVERITY_ERROR, user_message, null));
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addManager";
        } else if (checkUser > 0 && manager.getStatus() == null) {
            user_message = "Username already exists!";
            FacesContext.getCurrentInstance().addMessage("username", new FacesMessage(FacesMessage.SEVERITY_ERROR, user_message, null));
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addManager";
        } else {
            imageURL = FileUltil.getInstance().uploadFile(file);
            hashPassword = CryptUltil.getCrypt().enCodePass(password);
            manager.setFullname(fullname);
            manager.setUsername(username);
            manager.setPassword(hashPassword);
            manager.setAddress(address);
            manager.setPhone(phone);
            manager.setEmail(email);
            manager.setImageURL(imageURL);
            manager.setManagerGender(selected_gender);
            manager.setCreatedate(new Date(System.currentTimeMillis()));
            if (manager.getStatus() == null) {
                manager.setStatus(Boolean.TRUE);
            }

            managersFacade.create(manager);
        }
        return "manager";
    }

    // Update Manager
    public String updateManager() {
        Managers managerUp = manager;
        Managers ma = managersFacade.find(managerUp.getId());
        hashPassword = CryptUltil.getCrypt().enCodePass(new_password);

        if (managerUp.getImageURL() == null) {
            imageURL = FileUltil.getInstance().uploadFile(file);
            managerUp.setImageURL(imageURL);
        } else {
            managerUp.setImageURL(ma.getImageURL());
        }
        managerUp.setPassword(hashPassword);
        managerUp.setCreatedate(ma.getCreatedate());
        managerUp.setModifiedate(new Date(System.currentTimeMillis()));
        if (managerUp.getStatus() == null) {
            managerUp.setStatus(Boolean.TRUE);
        }
        managersFacade.edit(managerUp);
        return "manager";
    }

    // Change Status
    public String toggleStatus(Long ID) {
        manager = managersFacade.find(ID);
        System.out.println("init id: " + manager.getId() + "---------- status: " + manager.getStatus());
        if (manager.getStatus()) {
            manager.setStatus(false);
        } else {
            manager.setStatus(true);
        }
        managersFacade.edit(manager);
        return "manager";
    }

    // Delete Manager
    public String deleteManager(Long id) {
        managersFacade.remove(managersFacade.find(id));
        message_delete = "Deleted employee successfully!";
        return null;
    }   
    
    //Handle File
    private void handleFileUploadException(IOException ex) {
        Logger.getLogger(ManagerBean.class.getName()).log(Level.SEVERE, null, ex);

        String errorMessage;
        if (ex.getMessage().contains("File not found")) {
            errorMessage = "The file does not exist or cannot be accessed. Please check the file and try again.";
        } else if (ex.getMessage().contains("Invalid file format")) {
            errorMessage = "The file is in an invalid format. Please check the file format and try again.";
        } else {
            errorMessage = "An error occurred while uploading the file. Please try again.";
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
    }

    public void backIndex() {
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/webapp/webapp.administrator/partials/index.xhtml");
    }

    public void backToEmployee() {
//        // Huỷ session trước đó
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        // Chuyển về trang employee.xhtml
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/webapp/webapp.administrator/employee/employee.xhtml");
    }

    public ManagersFacadeLocal getManagersFacade() {
        return managersFacade;
    }

    public void setManagersFacade(ManagersFacadeLocal managersFacade) {
        this.managersFacade = managersFacade;
    }

    public Managers getManager() {
        return manager;
    }

    public void setManager(Managers manager) {
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPepper_pass() {
        return pepper_pass;
    }

    public void setPepper_pass(String pepper_pass) {
        this.pepper_pass = pepper_pass;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public short getSelected_gender() {
        return selected_gender;
    }

    public void setSelected_gender(short selected_gender) {
        this.selected_gender = selected_gender;
    }

    public String getGenderLabel() {
        return genderLabel;
    }

    public void setGenderLabel(String genderLabel) {
        this.genderLabel = genderLabel;
    }

    public String getUser_message() {
        return user_message;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }

    public String getMessage_delete() {
        return message_delete;
    }

    public void setMessage_delete(String message_delete) {
        this.message_delete = message_delete;
    }
}
