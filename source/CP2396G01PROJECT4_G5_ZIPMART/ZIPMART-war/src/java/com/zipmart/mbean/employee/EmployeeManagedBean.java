package com.zipmart.mbean.employee;

import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.session_beans.EmployeesFacadeLocal;
import com.zipmart.ejb.session_beans.PermissionsFacadeLocal;
import com.zipmart.util.FileUltil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author TUONG
 */
@Named(value = "employeeManagedBean")
@SessionScoped
public class EmployeeManagedBean implements Serializable {

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    @EJB
    private PermissionsFacadeLocal permissionFacade;

    private Employees employee;

    private long id;
    private long permissionID;
    private String fullname;
    private String username;
    private String password;
    private String plainpass;
    private String salt_pass;
    private String pepper_pass;
    private String hashPassword;
    private String email;
    private Part file;
    private String imgUrl;
    private String address;
    private String notes;
    private String phone;
    private Timestamp birthday;
    private boolean status;
    private Date dayOfBirth;

    private String usernameMessage;
    private String messageDeleteEmployee;
    
    public EmployeeManagedBean() {
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

    // Insert Employee
    public String saveCreateEmp() {
        if (isUsernameExist()) {
            return "addEmployee";
        } else {
            imgUrl = FileUltil.getInstance().uploadFile(file);
            System.out.println("=====================-----------=====================" + imgUrl);
            employeeCredentialsFromAccount();
            employeesFacade.create(employee);
        }
        return "employee";
    }
    
    // Delete Employee
    public String deleteEmployee(Long id) {
        employeesFacade.remove(employeesFacade.find(id));
        messageDeleteEmployee = "Deleted employee successfully!";
        return null;
    }

    // Check exist username
    private boolean isUsernameExist() {
        long checkUsername = employeesFacade.getUserNameEx(employee.getUsername());
        if (checkUsername > 0) {
            usernameMessage = "Username already exists!";
            return true;
        }
        return false;
    }

    // setup form employee
    private void employeeCredentialsFromAccount() {
        salt_pass = UUID.randomUUID().toString();
        pepper_pass = "secret_employee";
        String salt = BCrypt.gensalt(12).concat(pepper_pass);
        String temp = password;
        plainpass = temp;
        employee.setPlainpass(plainpass);
        System.out.println(plainpass);
        hashPassword = BCrypt.hashpw(password, salt);
        employee.setImageURL(imgUrl);
        employee.setFullname(fullname);
        employee.setUsername(username);
        employee.setPassword(hashPassword);
        employee.setSaltPassword(salt);
        employee.setPepperPassword(pepper_pass);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setAddress(address);
        employee.setBirthDate(dayOfBirth);
    }

    // exception handle
    private void handleFileUploadException(IOException ex) {
        Logger.getLogger(EmployeeManagedBean.class.getName()).log(Level.SEVERE, null, ex);

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

    public PermissionsFacadeLocal getPermissionFacade() {
        return permissionFacade;
    }

    public void setPermissionFacade(PermissionsFacadeLocal permissionFacade) {
        this.permissionFacade = permissionFacade;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(long permissionID) {
        this.permissionID = permissionID;
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

    public String getSalt_pass() {
        return salt_pass;
    }

    public void setSalt_pass(String salt_pass) {
        this.salt_pass = salt_pass;
    }

    public String getPepper_pass() {
        return pepper_pass;
    }

    public void setPepper_pass(String pepper_pass) {
        this.pepper_pass = pepper_pass;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
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
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUsernameMessage() {
        return usernameMessage;
    }

    public void setUsernameMessage(String usernameMessage) {
        this.usernameMessage = usernameMessage;
    }

    public String getMessageDeleteEmployee() {
        return messageDeleteEmployee;
    }

    public void setMessageDeleteEmployee(String messageDeleteEmployee) {
        this.messageDeleteEmployee = messageDeleteEmployee;
    }

    public String getPlainpass() {
        return plainpass;
    }

    public void setPlainpass(String plainpass) {
        this.plainpass = plainpass;
    }
}
