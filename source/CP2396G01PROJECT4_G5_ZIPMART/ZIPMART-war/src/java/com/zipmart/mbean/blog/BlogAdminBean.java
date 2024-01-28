/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.blog;

import com.zipmart.ejb.entities.BlogCategories;
import com.zipmart.ejb.entities.Blogs;
import com.zipmart.ejb.entities.EmployeeBlog;
import com.zipmart.ejb.entities.EmployeeBlogPK;
import com.zipmart.ejb.entities.Employees;
import com.zipmart.ejb.session_beans.BlogCategoriesFacadeLocal;
import com.zipmart.ejb.session_beans.BlogsFacadeLocal;
import com.zipmart.ejb.session_beans.EmployeeBlogFacadeLocal;
import com.zipmart.ejb.session_beans.EmployeesFacadeLocal;
import com.zipmart.util.FileUltil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author TUONG
 */
@Named(value = "blogAdminBean")
@RequestScoped
public class BlogAdminBean {
    
    @EJB
    private EmployeesFacadeLocal employeesFacade;
    
    @EJB
    private EmployeeBlogFacadeLocal employeeBlogFacade;
    
    @EJB
    private BlogCategoriesFacadeLocal blogCategoriesFacade;
    
    @EJB
    private BlogsFacadeLocal blogsFacade;    
    
    private Blogs blog = new Blogs();
    private Employees employee = new Employees();
    private BlogCategories cate_blog = new BlogCategories();
    private EmployeeBlog eb = new EmployeeBlog();
    private EmployeeBlogPK idEB = new EmployeeBlogPK();
    
    
    private Long id;
    private String image_url;
    private Part file;
    
    private Long selected_category;
    private Long selected_employee;
    
    private final String UPLOAD_DIRECTORY = "../resources\\images\\blog";
    
    public BlogAdminBean() {
    }

    //Save image
    //Upload Image
    //******************************************upload file image ***********************************************
    public void uploadFile() {
        if (file != null) {
            InputStream content = null;
            try {
                content = file.getInputStream();
                // Write content to disk or DB.
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext ec = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) ec.getRequest();

                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");

                // constructs path of the directory to save uploaded fileImage
                String relativePath = "/resources/images/blog";
                String uploadFilePath = request.getServletContext().getRealPath(relativePath);
                // creates the save directory if it does not exists
                File fileImageSaveDir = new File(uploadFilePath);
                if (!fileImageSaveDir.exists()) {
                    fileImageSaveDir.mkdirs();
                }
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(uploadFilePath + File.separator + file.getSubmittedFileName());
                    content = file.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = content.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
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
    
    public List<Blogs> showAllBlogs() {
        return blogsFacade.findAll();
    }
    
    public List<BlogCategories> showAllCategory() {
        return blogCategoriesFacade.findAll();
    }
    
    public List<Employees> showEmployee() {
        return employeesFacade.findAll();
    }
    
    public String addBlog() {
        image_url = FileUltil.getInstance().uploadFile(file);
        blog.setCategory(blogCategoriesFacade.find(selected_category));
        blog.setImageURL(image_url);
        blogsFacade.create(blog);
        
        Blogs bl = blog;
        id = bl.getId();
        bl = blogsFacade.find(id);
        employee = employeesFacade.find(selected_employee);
        
        if (bl != null && employee != null) {
                idEB.setBlogID(bl.getId());
                idEB.setEmployeeID(employee.getId());

                bl.setEmployeeID(employee.getId());
                bl.setCreateby(employee.getFullname());
                System.out.println("Gender: " + bl.getEmployeeID());

                eb = employeeBlogFacade.find(idEB);

                if (eb == null) {
                    // Create if not exist
                    eb = new EmployeeBlog();
                    eb.setEmployeeBlogPK(idEB);
                    employeeBlogFacade.create(eb);
                } else {
                    // Update if exist
                    eb.setEmployees(employee);
                    eb.setBlogs(bl);
                    employeeBlogFacade.edit(eb);
                }
                blogsFacade.edit(bl);
            }
        return "displayblog?faces-redirect=true";
    }
    
    public String updateBlog(){
        Blogs blogUp = blog;
    if (blogUp.getImageURL() == null) {
            image_url = FileUltil.getInstance().uploadFile(file);
            blogUp.setImageURL(image_url);
        } else {
            blogUp.setImageURL(blogUp.getImageURL());
        }

        if (employee != null) {
            blogUp.setEmployeeID(employee.getId());

            idEB.setBlogID(blogUp.getId());
            idEB.setEmployeeID(employee.getId());
            System.out.println("Gender: " + blogUp.getEmployeeID());
            eb.setEmployeeBlogPK(idEB);
            EmployeeBlog existingEmployeeBlog = employeeBlogFacade.find(idEB);

            if (existingEmployeeBlog == null) {
                // Create if not exist
                employeeBlogFacade.create(eb);
            } else {
                // Update if exist
                existingEmployeeBlog.setBlogs(eb.getBlogs());
                existingEmployeeBlog.setEmployees(eb.getEmployees());
                employeeBlogFacade.edit(existingEmployeeBlog);
            }
        }
        
        blogsFacade.edit(blogUp);
        return "displayblog";
    }
    
    public BlogCategoriesFacadeLocal getBlogCategoriesFacade() {
        return blogCategoriesFacade;
    }
    
    public void setBlogCategoriesFacade(BlogCategoriesFacadeLocal blogCategoriesFacade) {
        this.blogCategoriesFacade = blogCategoriesFacade;
    }
    
    public BlogsFacadeLocal getBlogsFacade() {
        return blogsFacade;
    }
    
    public void setBlogsFacade(BlogsFacadeLocal blogsFacade) {
        this.blogsFacade = blogsFacade;
    }
    
    public Blogs getBlog() {
        return blog;
    }
    
    public void setBlog(Blogs blog) {
        this.blog = blog;
    }
    
    public Employees getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
    public BlogCategories getCate_blog() {
        return cate_blog;
    }
    
    public void setCate_blog(BlogCategories cate_blog) {
        this.cate_blog = cate_blog;
    }
    
    public EmployeeBlog getEb() {
        return eb;
    }
    
    public void setEb(EmployeeBlog eb) {
        this.eb = eb;
    }
    
    public EmployeeBlogPK getIdEB() {
        return idEB;
    }
    
    public void setIdEB(EmployeeBlogPK idEB) {
        this.idEB = idEB;
    }
    
    public Long getSelected_category() {
        return selected_category;
    }
    
    public void setSelected_category(Long selected_category) {
        this.selected_category = selected_category;
    }
    
    public Long getSelected_employee() {
        return selected_employee;
    }
    
    public void setSelected_employee(Long selected_employee) {
        this.selected_employee = selected_employee;
    }
    
    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }
    
    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }
    
    public EmployeeBlogFacadeLocal getEmployeeBlogFacade() {
        return employeeBlogFacade;
    }
    
    public void setEmployeeBlogFacade(EmployeeBlogFacadeLocal employeeBlogFacade) {
        this.employeeBlogFacade = employeeBlogFacade;
    }
    
    public String getUPLOAD_DIRECTORY() {
        return UPLOAD_DIRECTORY;
    }
    
    public String getImage_url() {
        return image_url;
    }
    
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    
    public Part getFile() {
        return file;
    }
    
    public void setFile(Part file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
