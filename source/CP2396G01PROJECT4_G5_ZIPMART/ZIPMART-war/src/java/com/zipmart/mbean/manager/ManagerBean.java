/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.manager;

import com.zipmart.ejb.entities.Genders;
import com.zipmart.ejb.entities.ManagerGenders;
import com.zipmart.ejb.entities.ManagerGendersPK;
import com.zipmart.ejb.entities.Managers;
import com.zipmart.ejb.session_beans.GendersFacadeLocal;
import com.zipmart.ejb.session_beans.ManagerGendersFacadeLocal;
import com.zipmart.ejb.session_beans.ManagersFacadeLocal;
import com.zipmart.util.FileUltil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author TUONG
 */
@Named(value = "managerBean")
@RequestScoped
public class ManagerBean {

    @EJB
    private GendersFacadeLocal gendersFacade;

    @EJB
    private ManagerGendersFacadeLocal managerGendersFacade;

    @EJB
    private ManagersFacadeLocal managersFacade;

    private Managers manager = new Managers();
    private Genders gender = new Genders();
    private ManagerGenders mg = new ManagerGenders();
    private ManagerGendersPK idMG = new ManagerGendersPK();

    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String new_password;
    private String phone;
    private String email;
    private String address;
    private boolean status = true;

    private String pepper_pass = "secret_employee";
    private String salt = BCrypt.gensalt();
    private String hashPassword = BCrypt.hashpw(password, salt);
    private String BasicBase64format;
            

    private Part file;
    private String imageURL;

    private Long selected_gender;
    private long genderValue;
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
    
    // Show All Gender Data
    public List<Genders> showGenders() {
        return gendersFacade.findAll();
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
        genderValue = manager.getManagerGender(); 
        switch ((int) genderValue) {
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
        System.out.println("====== ID Manager ========== " + id + "=========" + genderValue + "========" + genderLabel);
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
            BasicBase64format = Base64.getEncoder().encodeToString(password.getBytes());
            imageURL = FileUltil.getInstance().uploadFile(file);
            manager.setFullname(fullname);
            manager.setUsername(username);
            manager.setPassword(BasicBase64format);
            manager.setSaltPassword(salt);
            manager.setPepperPassword(pepper_pass);
            manager.setAddress(address);
            manager.setPhone(phone);
            manager.setEmail(email);
            manager.setImageURL(imageURL);
            if (manager.getStatus() == null) {
                manager.setStatus(Boolean.TRUE);
            }
            
            managersFacade.create(manager);

            selected_gender = 4L;

            Managers mng = manager;
            id = mng.getId();
            mng = managersFacade.find(id);
            gender = gendersFacade.find(selected_gender);
            if (mng != null && gender != null) {
                idMG.setManagerID(mng.getId());
                idMG.setGenderID(gender.getId());

                mng.setManagerGender(gender.getId());
                System.out.println("Gender: " + mng.getManagerGender());

                mg = managerGendersFacade.find(idMG);

                if (mg == null) {
                    // Create if not exist
                    mg = new ManagerGenders();
                    mg.setManagerGendersPK(idMG);
                    managerGendersFacade.create(mg);
                } else {
                    // Update if exist
                    mg.setGenders(gender);
                    mg.setManagers(mng);
                    managerGendersFacade.edit(mg);
                }
                managersFacade.edit(mng);
            }
            System.out.println("Image URL: " + imageURL);
            System.out.println("Full name: " + fullname);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Salt: " + salt);
            System.out.println("Pepper: " + pepper_pass);
            System.out.println("Address: " + address);
            System.out.println("Email: " + email);
        }
        return "manager";
    }
    
    // Update Manager
    public String updateManager() {
        Managers managerUp = manager;
        gender = gendersFacade.find(selected_gender);
        imageURL = FileUltil.getInstance().uploadFile(file);

        hashPassword = BCrypt.hashpw(new_password, salt);

        if (imageURL == null) {
            managerUp.setImageURL(managerUp.getImageURL());
        } else {
            managerUp.setImageURL(imageURL);
        }

        if (gender != null) {
            managerUp.setManagerGender(gender.getId());

            idMG.setManagerID(managerUp.getId());
            idMG.setGenderID(gender.getId());
            System.out.println("Gender: " + managerUp.getManagerGender());
            mg.setManagerGendersPK(idMG);
            ManagerGenders existingEmployeeGender = managerGendersFacade.find(idMG);

            if (existingEmployeeGender == null) {
                // Create if not exist
                managerGendersFacade.create(mg);
            } else {
                // Update if exist
                existingEmployeeGender.setGenders(mg.getGenders());
                existingEmployeeGender.setManagers(mg.getManagers());
                managerGendersFacade.edit(existingEmployeeGender);
            }
        }
        managerUp.setFullname(managerUp.getFullname());
        managerUp.setUsername(managerUp.getUsername());
        managerUp.setPassword(hashPassword);
        managerUp.setSaltPassword(salt);
        managerUp.setAddress(managerUp.getAddress());
        managerUp.setPhone(managerUp.getPhone());
        managerUp.setEmail(managerUp.getEmail());
        managerUp.setImageURL(managerUp.getImageURL());
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
    
    public GendersFacadeLocal getGendersFacade() {
        return gendersFacade;
    }

    public void setGendersFacade(GendersFacadeLocal gendersFacade) {
        this.gendersFacade = gendersFacade;
    }

    public ManagerGendersFacadeLocal getManagerGendersFacade() {
        return managerGendersFacade;
    }

    public void setManagerGendersFacade(ManagerGendersFacadeLocal managerGendersFacade) {
        this.managerGendersFacade = managerGendersFacade;
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

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public ManagerGenders getMg() {
        return mg;
    }

    public void setMg(ManagerGenders mg) {
        this.mg = mg;
    }

    public ManagerGendersPK getIdMG() {
        return idMG;
    }

    public void setIdMG(ManagerGendersPK idMG) {
        this.idMG = idMG;
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

    public Long getSelected_gender() {
        return selected_gender;
    }

    public void setSelected_gender(Long selected_gender) {
        this.selected_gender = selected_gender;
    }

    public long getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(long genderValue) {
        this.genderValue = genderValue;
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

    public String getBasicBase64format() {
        return BasicBase64format;
    }

    public void setBasicBase64format(String BasicBase64format) {
        this.BasicBase64format = BasicBase64format;
    }
}
