package com.zipmart.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUltil {

    private static FileUltil fileUltil = null;
    
    private Part file;
    private String imagePath;
    private String src = "file";
    private static final String UPLOAD_DIRECTORY = "resources\\images\\uploads";

    public static FileUltil getInstance() {
        if (fileUltil == null) {
            fileUltil = new FileUltil();
        }
        return fileUltil;
    }    

    public String uploadFile() {
        if (file != null) {
            try {
                InputStream content = file.getInputStream();
                // Get real path of the application
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext ec = context.getExternalContext();
                HttpServletRequest request = (HttpServletRequest) ec.getRequest();
                String applicationPath = request.getServletContext().getRealPath("");
                src = applicationPath.substring(0, applicationPath.indexOf("dist") - 1) + File.separator + "FinalAssignment_EJP-war" + File.separator + "web";
                String uploadFilePath = src + File.separator + UPLOAD_DIRECTORY;

                // Create the upload directory if it doesn't exist
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }

                // Write the file to the upload directory
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(uploadFilePath + File.separator + file.getSubmittedFileName());
                    imagePath = file.getSubmittedFileName();
                    outputStream = new FileOutputStream(outputFilePath);
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = content.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                    System.out.println("File uploaded successfully!");
                } catch (Exception ex) {
                    Logger.getLogger(FileUltil.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(FileUltil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return imagePath;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
