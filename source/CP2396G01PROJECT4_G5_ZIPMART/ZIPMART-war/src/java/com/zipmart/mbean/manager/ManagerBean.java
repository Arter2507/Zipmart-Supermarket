package com.zipmart.mbean.manager;

import com.zipmart.ejb.entities.EmployeeGenders;
import com.zipmart.ejb.entities.EmployeeGendersPK;
import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.entities.Genders;
import com.zipmart.ejb.session_beans.EmployeeGendersFacadeLocal;
import com.zipmart.ejb.session_beans.EmployeesFacadeLocal;
import com.zipmart.ejb.session_beans.GendersFacadeLocal;
import com.zipmart.util.FileUltil;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
public class ManagerBean implements Serializable {

    @EJB
    private EmployeeGendersFacadeLocal employeeGendersFacade;

    @EJB
    private GendersFacadeLocal gendersFacade;

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private Employees employee = new Employees();
    private Genders gender = new Genders();
    private EmployeeGenders eg = new EmployeeGenders();
    private EmployeeGendersPK idEG = new EmployeeGendersPK();

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

    private String salt_pass = UUID.randomUUID().toString();
    private String pepper_pass = "secret_employee";
    private String salt = BCrypt.gensalt(12).concat(pepper_pass);
    private String hashPassword = BCrypt.hashpw(password, salt);

    private Part file;
    private String imageURL;

    private Long selected_gender;
    private long genderValue;
    private String genderLabel;   
    
    public ManagerBean() {
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

    public List<Genders> showGenders() {
        return gendersFacade.findAll();
    }

    public String showListID(Long id) {
        employee = employeesFacade.find(id);
        id = employee.getId();
        System.out.println("====== ID Employee ========== " + id);
        return "updateEmployee";
    }

    public String showDetails(Long id) {
        employee = employeesFacade.find(id);
        id = employee.getId();
        genderValue = employee.getEmployeeGender();  // Assuming employeeGender is an integer
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
        System.out.println("====== ID Employee ========== " + id + "=========" + genderValue + "========" + genderLabel);
        return "detailsEmployee";
    }

    public String createEmp() {
        long checkUser = employeesFacade.findByUsername(username);
        if (checkUser > 0 && status == true) {
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addEmployee";
        } else if (checkUser > 0 && status == false) {
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addEmployee";
        } else if (checkUser > 0 && employee.getStatus() == null) {
            System.out.println("Username already exists!" + username + "-------" + status + "=====" + checkUser);
            return "addEmployee";
        } else {
            imageURL = FileUltil.getInstance().uploadFile(file);
            employee.setFullname(fullname);
            employee.setUsername(username);
            employee.setPassword(hashPassword);
            employee.setSaltPassword(salt);
            employee.setPepperPassword(pepper_pass);
            employee.setAddress(address);
            employee.setPhone(phone);
            employee.setEmail(email);
            employee.setBirthDate(dayOfBirth);
            employee.setImageURL(imageURL);
            if (employee.getStatus() == null) {
                employee.setStatus(Boolean.TRUE);
            }
            
            employeesFacade.create(employee);

            selected_gender = 4L;

            Employees emp = employee;
            id = emp.getId();
            emp = employeesFacade.find(id);
            gender = gendersFacade.find(selected_gender);
            if (emp != null && gender != null) {
                idEG.setEmployeeID(emp.getId());
                idEG.setGenderID(gender.getId());

                emp.setEmployeeGender(gender.getId());
                System.out.println("Gender: " + emp.getEmployeeGender());

                eg = employeeGendersFacade.find(idEG);

                if (eg == null) {
                    // Create if not exist
                    eg = new EmployeeGenders();
                    eg.setEmployeeGendersPK(idEG);
                    employeeGendersFacade.create(eg);
                } else {
                    // Update if exist
                    eg.setGenders(gender);
                    eg.setEmployees(emp);
                    employeeGendersFacade.edit(eg);
                }
                employeesFacade.edit(emp);
            }
            System.out.println("Image URL: " + imageURL);
            System.out.println("Full name: " + fullname);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Salt: " + salt);
            System.out.println("Pepper: " + pepper_pass);
            System.out.println("Address: " + address);
            System.out.println("Email: " + email);
            System.out.println("Birthdate: " + birthdate);
        }
        return "employee";
    }

    public String updateEmp() {
        Employees empUp = employee;
        gender = gendersFacade.find(selected_gender);
        imageURL = FileUltil.getInstance().uploadFile(file);

        hashPassword = BCrypt.hashpw(new_password, salt);

        if (imageURL == null) {
            empUp.setImageURL(empUp.getImageURL());
        } else {
            empUp.setImageURL(imageURL);
        }

        if (gender != null) {
            empUp.setEmployeeGender(gender.getId());

            idEG.setEmployeeID(empUp.getId());
            idEG.setGenderID(gender.getId());
            System.out.println("Gender: " + empUp.getEmployeeGender());
            eg.setEmployeeGendersPK(idEG);
            EmployeeGenders existingEmployeeGender = employeeGendersFacade.find(idEG);

            if (existingEmployeeGender == null) {
                // Create if not exist
                employeeGendersFacade.create(eg);
            } else {
                // Update if exist
                existingEmployeeGender.setGenders(eg.getGenders());
                existingEmployeeGender.setEmployees(eg.getEmployees());
                employeeGendersFacade.edit(existingEmployeeGender);
            }
        }
        empUp.setFullname(empUp.getFullname());
        empUp.setUsername(empUp.getUsername());
        empUp.setPassword(hashPassword);
        empUp.setSaltPassword(salt);
        empUp.setAddress(empUp.getAddress());
        empUp.setPhone(empUp.getPhone());
        empUp.setEmail(empUp.getEmail());
        empUp.setBirthDate(empUp.getBirthDate());
        empUp.setNotes(empUp.getNotes());
        empUp.setImageURL(empUp.getImageURL());
        employeesFacade.edit(empUp);
        return "employee";
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

    public void backToEmployee() {
//        // Huỷ session trước đó
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        // Chuyển về trang employee.xhtml
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "employee");
    }
    
    // Exception handling
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

    public EmployeeGendersFacadeLocal getEmployeeGendersFacade() {
        return employeeGendersFacade;
    }

    public void setEmployeeGendersFacade(EmployeeGendersFacadeLocal employeeGendersFacade) {
        this.employeeGendersFacade = employeeGendersFacade;
    }

    public GendersFacadeLocal getGendersFacade() {
        return gendersFacade;
    }

    public void setGendersFacade(GendersFacadeLocal gendersFacade) {
        this.gendersFacade = gendersFacade;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public EmployeeGenders getEg() {
        return eg;
    }

    public void setEg(EmployeeGenders eg) {
        this.eg = eg;
    }

    public EmployeeGendersPK getIdEG() {
        return idEG;
    }

    public void setIdEG(EmployeeGendersPK idEG) {
        this.idEG = idEG;
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

}
