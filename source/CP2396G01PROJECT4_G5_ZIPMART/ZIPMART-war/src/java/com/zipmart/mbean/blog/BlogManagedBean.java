/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.blog;

import com.zipmart.ejb.entities.Blogs;
import com.zipmart.ejb.session_beans.BlogCategoriesFacadeLocal;
import com.zipmart.ejb.session_beans.BlogsFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
    private BlogCategoriesFacadeLocal blogCategoriesFacade;

    @EJB
    private BlogsFacadeLocal blogsFacade;
    
    private Blogs blogs;

    private long id;

    private Date createdate;

    private Part fileImage;
    private String keyfind = "";

    final String UPLOAD_DIRECTORY = "../resources\\images\\blog";
    private List<Blogs> listBlog = new ArrayList<>();
    
    
    public BlogManagedBean() {
         blogs = new Blogs();
        listBlog = new ArrayList<>();
        blogs.setCreatedate(new Date(System.currentTimeMillis()));
    }
    
    //Showall
    public List<Blogs> showAllBlogs() {
        return blogsFacade.findAll();
    }

    //Save image
    //Upload Image
    //******************************************upload file image ***********************************************
    public void uploadFile() {
        if (fileImage != null) {
            InputStream content = null;
            try {
                content = fileImage.getInputStream();
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
                    File outputFilePath = new File(uploadFilePath + File.separator + fileImage.getSubmittedFileName());
                    content = fileImage.getInputStream();
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

    //Create
    public String createBlog() {
        blogs.setImageURL(fileImage.getSubmittedFileName());
        uploadFile();
        blogsFacade.create(blogs);
        return "displayblog?faces-redirect=true";
    }

    //Update
    public String updateBlog() {
        blogs.setImageURL(fileImage.getSubmittedFileName());
        uploadFile();
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

    public String showblogbyID(long id) {
        blogs = blogsFacade.find(id);
        return "blog-details";
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

    public Blogs getBlogs() {
        return blogs;
    }

    public void setBlogs(Blogs blogs) {
        this.blogs = blogs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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

    public List<Blogs> getListBlog() {
        return listBlog;
    }

    public void setListBlog(List<Blogs> listBlog) {
        this.listBlog = listBlog;
    }
}