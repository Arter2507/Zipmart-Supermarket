/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.blog;

import com.group5.zipmart.entities.Blogs;
import com.group5.zipmart.sessionbeans.BlogsFacadeLocal;
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
@Named(value = "blogMB")
@SessionScoped
public class BlogManagedBean implements Serializable {

    @EJB
    private BlogsFacadeLocal blogsFacade;

    private Blogs blogs;

    private long id;

    private Part fileImage;
    private String keyfind="";

    final String UPLOAD_DIRECTORY = "../../resources\\images\\blog";
    private List<Blogs> listBlog = new ArrayList<>();

    public BlogManagedBean() {
        blogs = new Blogs();
        listBlog = new ArrayList<>();
    }

    //Showall
    public List<Blogs> showAllBlogs() {
        return blogsFacade.findAll();
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

    //Create
    public String createBlog() {
        blogs.setImageURL(fileImage.getSubmittedFileName());
        uploadFile();
        blogsFacade.create(blogs);
        return "displayblog?faces-redirect=true";
    }
    
    //Update
    public String updateBlog() {
//        blogs.setImageURL(fileImage.getSubmittedFileName());
//        uploadFile();
        blogsFacade.edit(blogs);
        return "displayblog";
    }
    
    //Delete
    public String deleteBlog(long id) {
        Blogs b = blogsFacade.find(id);
        blogsFacade.remove(b);
        return "displayblog";
    }
    
    //show detail blog
    public String showDetailBlogU(long id) {
        blogs = blogsFacade.find(id);
        return "updateblog";
    }
    
    public String showblogbyID(long id){
        blogs = blogsFacade.find(id);
        return "blog-details";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

  
    public Blogs getBlogs() {
        return blogs;
    }

    public void setBlogs(Blogs blogs) {
        this.blogs = blogs;
    }

    public List<Blogs> getListBlog() {
        return listBlog;
    }

    public void setListBlog(List<Blogs> listBlog) {
        this.listBlog = listBlog;
    }

    public Part getFileImage() {
        return fileImage;
    }

    public void setFileImage(Part fileImage) {
        this.fileImage = fileImage;
    }

    public BlogsFacadeLocal getBlogsFacade() {
        return blogsFacade;
    }

    public void setBlogsFacade(BlogsFacadeLocal blogsFacade) {
        this.blogsFacade = blogsFacade;
    }

    public String getKeyfind() {
        return keyfind;
    }

    public void setKeyfind(String keyfind) {
        this.keyfind = keyfind;
    }
    
}
