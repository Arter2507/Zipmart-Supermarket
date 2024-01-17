/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.employee;

import com.group5.zipmart.entities.Employees;
import com.group5.zipmart.entities.Permissions;
import com.group5.zipmart.sessionbeans.EmployeesFacadeLocal;
import com.group5.zipmart.sessionbeans.PermissionsFacadeLocal;
import com.zipmart.util.FileUltil;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@Named(value = "empManagedBean")
@RequestScoped
public class EmpManagedBean {

    @EJB
    private PermissionsFacadeLocal permissionFacade;

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private Employees employee;

    private long id;
    private long permissionID;
    private long accountID;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private Part file;
    private String imgUrl;
    private String address;
    private String notes;
    private String phone;
    private Timestamp birthday;
    private Date dayOfBirth;
    private String messageUpdateProfile = "";

    private String messageConfirmOldPass = "";
    private String messageConfirmNewPass = "";

    private String messageAddEmployee = "";
    private String messageDeleteEmployee = "";
    private String fullnameMessage;
    private String usernameMessage;
    private String passwordMessage;
    private String addressMessage;
    private String phoneMessage;

    public EmpManagedBean() {
        employee = new Employees();
    }

    // Get All Data
    public List<Employees> showEmpList() {
        return employeesFacade.findAll();
    }

    // Show update Employee
    public String showList(Long id) {
        employee = employeesFacade.find(id);
        id = employee.getId();
        System.out.println("==================================" + id.toString());
        return "updateEmployee";
    }

    // Update Employee
    public String saveCreateEmp() {
//        if (isUsernameExist()) {
//            return "addEmployee";
//        } else {
        imgUrl = FileUltil.getInstance().uploadFile();

        employeesFacade.create(employee);
        //}
        return "employee";
    }

    private boolean isUsernameExist() {
        long checkUsername = employeesFacade.getUserNameEx(employee.getUsername());
        if (checkUsername > 0) {
            usernameMessage = "Username already exists!";
            return true;
        }
        return false;
    }

    private void employeeCredentialsFromAccount() {
//        account = accountsFacade.find(employee.getAccountID().getId());
//        account.setUsername(employee.getAccountID().getUsername());
//        account.setPassword(employee.getAccountID().getPassword());
//        account.setPermissionID(employee.getAccountID().getPermissionID());


    }

    private void handleFileUploadException(IOException ex) {
        Logger.getLogger(EmpManagedBean.class.getName()).log(Level.SEVERE, null, ex);

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

    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }

    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getBirthday() {
        return new Timestamp(this.dayOfBirth.getTime());
    }

    public void setBirthday(Timestamp birthday) {
        this.dayOfBirth = new Date(birthday.getTime());
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public PermissionsFacadeLocal getPermissionFacade() {
        return permissionFacade;
    }

    public void setPermissionFacade(PermissionsFacadeLocal permissionFacade) {
        this.permissionFacade = permissionFacade;
    }

    public long getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(long permissionID) {
        this.permissionID = permissionID;
    }

    public long getAccountID() {
        return accountID = 2;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public String getMessageUpdateProfile() {
        return messageUpdateProfile;
    }

    public void setMessageUpdateProfile(String messageUpdateProfile) {
        this.messageUpdateProfile = messageUpdateProfile;
    }

    public String getMessageConfirmOldPass() {
        return messageConfirmOldPass;
    }

    public void setMessageConfirmOldPass(String messageConfirmOldPass) {
        this.messageConfirmOldPass = messageConfirmOldPass;
    }

    public String getMessageConfirmNewPass() {
        return messageConfirmNewPass;
    }

    public void setMessageConfirmNewPass(String messageConfirmNewPass) {
        this.messageConfirmNewPass = messageConfirmNewPass;
    }

    public String getMessageAddEmployee() {
        return messageAddEmployee;
    }

    public void setMessageAddEmployee(String messageAddEmployee) {
        this.messageAddEmployee = messageAddEmployee;
    }

    public String getMessageDeleteEmployee() {
        return messageDeleteEmployee;
    }

    public void setMessageDeleteEmployee(String messageDeleteEmployee) {
        this.messageDeleteEmployee = messageDeleteEmployee;
    }

    public String getFullnameMessage() {
        return fullnameMessage;
    }

    public void setFullnameMessage(String fullnameMessage) {
        this.fullnameMessage = fullnameMessage;
    }

    public String getUsernameMessage() {
        return usernameMessage;
    }

    public void setUsernameMessage(String usernameMessage) {
        this.usernameMessage = usernameMessage;
    }

    public String getPasswordMessage() {
        return passwordMessage;
    }

    public void setPasswordMessage(String passwordMessage) {
        this.passwordMessage = passwordMessage;
    }

    public String getAddressMessage() {
        return addressMessage;
    }

    public void setAddressMessage(String addressMessage) {
        this.addressMessage = addressMessage;
    }

    public String getPhoneMessage() {
        return phoneMessage;
    }

    public void setPhoneMessage(String phoneMessage) {
        this.phoneMessage = phoneMessage;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

}
