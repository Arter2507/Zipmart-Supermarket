package com.zipmart.mbean.employee;

import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.entities.Permissions;
import com.zipmart.ejb.session_beans.EmployeesFacadeLocal;
import com.zipmart.ejb.session_beans.PermissionsFacadeLocal;
import com.zipmart.util.CryptUltil;
import com.zipmart.util.FileUltil;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author TUONG
 */
@Named(value = "employeeBean")
@RequestScoped
public class EmployeeBean implements Serializable {

    @EJB
    private PermissionsFacadeLocal permissionsFacade;

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private Employees employee;
    private Permissions per = new Permissions();

    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String new_password;
    private String phone;
    private String email;
    private String address;
    private Date dayOfBirth;
    private Timestamp birthdate;
    private boolean status = true;

    private String pepper_pass = CryptUltil.getCrypt().getPepper_pass();
    private String salt = CryptUltil.getCrypt().getSalt_pass();
    private String hashPassword;

    private Part file;
    private String imageURL;

    private short selected_gender;
    private String genderLabel;
    private String user_message;
    private String mess;

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private String user = (String) sessionMap.get("username");
    public EmployeeBean() {
        employee = new Employees();
    }

    public List<Employees> showAll() {
        List<Employees> listEmp = new ArrayList<>();
        for (Employees employee : employeesFacade.findAll()) {
            if (employee.getStatus() == null) {
                employee.setStatus(Boolean.TRUE);
            }
            if (employee.getStatus()) {
                listEmp.add(employee);
            }
        }
        return listEmp;
    }
    
    public String showListID(Long id) {
        employee = employeesFacade.find(id);
        id = employee.getId();
        System.out.println("====== ID Employee ========== " + id);
        return "updateEmployee";
    }
    
    
    public String showDetailspro(Long id) throws IOException {
        employee = employeesFacade.find(id);
        id = employee.getId();
        selected_gender = employee.getEmployeeGender();  // Assuming employeeGender is an integer
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
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ZIPMART-war/faces/webapp/webapp.administrator/employee/detailsEmployee.xhtml");
        return null;
    }
    
    public String showDetails(Long id) {
        employee = employeesFacade.find(id);
        id = employee.getId();
        selected_gender = employee.getEmployeeGender();  // Assuming employeeGender is an integer
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
        System.out.println("====== ID Employee ========== " + id + "========="  + "========" + genderLabel);
        return "details";
    }

    public String createEmp() {
        long checkUser = employeesFacade.findByUsername(username);
        if (checkUser > 0 && status == true) {
            user_message = "Username already exists!";
            FacesContext.getCurrentInstance().addMessage("username", new FacesMessage(FacesMessage.SEVERITY_ERROR, user_message, null));
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addEmployee";
        } else if (checkUser > 0 && status == false) {
            user_message = "Username already exists!";
            FacesContext.getCurrentInstance().addMessage("username", new FacesMessage(FacesMessage.SEVERITY_ERROR, user_message, null));
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addEmployee";
        } else if (checkUser > 0 && employee.getStatus() == null) {
            user_message = "Username already exists!";
            FacesContext.getCurrentInstance().addMessage("username", new FacesMessage(FacesMessage.SEVERITY_ERROR, user_message, null));
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addEmployee";
        } else {
            imageURL = FileUltil.getInstance().uploadFile(file);
            hashPassword = CryptUltil.getCrypt().enCodePass(password);
            employee.setUsername(username);
            employee.setPassword(hashPassword);
            employee.setBirthDate(dayOfBirth);
            employee.setImageURL(imageURL);
            employee.setEmployeeGender(selected_gender);
            employee.setFullname(fullname);
            employee.setAddress(address);
            employee.setPhone(phone);
            employee.setEmail(email);
            employee.setCreatedate(new Date());
            employee.setCreateby(user);
            if (employee.getStatus() == null) {
                employee.setStatus(Boolean.TRUE);
            }
            employeesFacade.create(employee);
        }
        return "employee";
    }

    public String updateEmp() {
        Employees empUp = employee;
        Employees em = employeesFacade.find(empUp.getId());
        hashPassword = CryptUltil.getCrypt().enCodePass(new_password);

        if (empUp.getImageURL() == null) {
            imageURL = FileUltil.getInstance().uploadFile(file);
            empUp.setImageURL(imageURL);
        } else {
            empUp.setImageURL(em.getImageURL());
        }              
        empUp.setEmployeeGroup(em.getEmployeeGroup());
        empUp.setPassword(hashPassword);
        empUp.setCreatedate(em.getCreatedate());
        empUp.setModifiedate(new Date(System.currentTimeMillis()));
        if (empUp.getStatus() == null) {
            empUp.setStatus(Boolean.TRUE);
        }       
        empUp.setCreateby(em.getCreateby());
        empUp.setCreatedate(em.getCreatedate());
        empUp.setModifiedate(new Date());
        empUp.setModifieby(user);
        employeesFacade.edit(empUp);
        mess = "Update profile successfully";
        return "detailsEmployee";
    }

    public String toggleEmployeeStatus(Long ID) {
        employee = employeesFacade.find(ID);
        System.out.println("init id: " + employee.getId() + "---------- status: " + employee.getStatus());
        if (employee.getStatus()) {
            employee.setStatus(false);
        } else {
            employee.setStatus(true);
        }
        employeesFacade.edit(employee);
        return "employee";
    }

    public String deleteEmployee(Long id) {
        employeesFacade.remove(employeesFacade.find(id));
        String messageDeleteEmployee = "Deleted employee successfully!";
        return null;
    }   

    public void backToIndex() {
//        // Huỷ session trước đó
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        // Chuyển về trang employee.xhtml
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "employee");
    }

    // Exception handling
    private void handleFileUploadException(IOException ex) {
        Logger.getLogger(EmployeeBean.class.getName()).log(Level.SEVERE, null, ex);

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

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
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

    public PermissionsFacadeLocal getPermissionsFacade() {
        return permissionsFacade;
    }

    public void setPermissionsFacade(PermissionsFacadeLocal permissionsFacade) {
        this.permissionsFacade = permissionsFacade;
    }

    public Permissions getPer() {
        return per;
    }

    public void setPer(Permissions per) {
        this.per = per;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

}
