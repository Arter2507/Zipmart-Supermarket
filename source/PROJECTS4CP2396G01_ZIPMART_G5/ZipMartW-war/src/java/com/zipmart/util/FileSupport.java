package com.zipmart.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class FileSupport {
    
    public static String saveFile(String realPath, String part, byte[] fileBytes, String originalFileName) throws FileNotFoundException, IOException {
        String directoryPath = realPath + File.separator + "resources"
                + File.separator + "images";
        
        if (part != null) {
            directoryPath += File.separator + part;
        }
        
        String fileName = generateUniqueFileName(originalFileName);
        directoryPath += File.separator + fileName;
        
        try {
            FileOutputStream fos = new FileOutputStream(directoryPath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            
            /**
             * Do lưu file nó chỉ lưu trên gfdeploy nên phía local không có file
             * Khi rebuild lại file sẽ mất cho nên subDirectory là để lưu thêm nó ở local
             * Hãy sửa lại subDirectory thành nơi mà thư mục #project-war được lưu sau đó là web/resources/images
             */
            
            // ->> UPLOAD TO DEV NOT AT DIST            
            String subDirectoryPath = "C:\\== Users\\TUONG\\Documents\\GitHub\\Zipmart-Supermarket\\source\\PROJECTS4CP2396G01_ZIPMART_G5\\ZipMartW-war\\web\\resources\\images";
            if (part != null) {
                subDirectoryPath += File.separator + part;
            }
            subDirectoryPath += File.separator + fileName;
            FileOutputStream subFos = new FileOutputStream(subDirectoryPath);
            BufferedOutputStream subBos = new BufferedOutputStream(subFos);
            
            subBos.write(fileBytes);
            subBos.flush();
            subBos.close();
            subFos.close();
            // ->> END - UPLOAD TO DEV NOT AT DIST

            bos.write(fileBytes);
            bos.flush();
            bos.close();
            fos.close();
        } catch (IOException e) {
            throw new IOException();
        }
        
        return fileName;
    }
    
    private static String generateUniqueFileName(String originalFileName) {
        // Get the current timestamp
        long timestamp = System.currentTimeMillis();
        
        // Generate a random UUID (Universally Unique Identifier)
        UUID uuid = UUID.randomUUID();
        
        // Extract the file extension (if any) from the original file name
        String fileExtension = "";
        int lastDotIndex = originalFileName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            fileExtension = originalFileName.substring(lastDotIndex);
        }
        
        // Combine timestamp, UUID, and file extension (if any) to create a unique file name
        String uniqueFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(timestamp))
                + "_" + uuid.toString() + fileExtension;
        
        return uniqueFileName;
    }
    
    public static void deleteFile(String realPath, String part, String fileName) throws IOException {
        String pathString = realPath  + File.separator + "resources"
                + File.separator + "images";
        if (part != null) {
            pathString += File.separator + part;
        }
        pathString += File.separator + fileName;
        
        Path pathToDelete = Paths.get(pathString);
        
        // ->> DELETE FILE NOT AT DIST
        String subDirectoryPath = "C:\\== Users\\TUONG\\Documents\\GitHub\\Zipmart-Supermarket\\source\\PROJECTS4CP2396G01_ZIPMART_G5\\ZipMartW-war\\web\\resources\\images";
        if (part != null) {
            subDirectoryPath += File.separator + part;
        }
        subDirectoryPath += File.separator + fileName;
        Files.delete(Paths.get(subDirectoryPath));
        
        Files.delete(pathToDelete);
    }
    
    public static String perfectImg(HttpServletRequest request, String part, String fileName) {
        return  "http://" + request.getServerName() + ":" + 
                request.getServerPort() + request.getContextPath() + 
                "/resources" + "/images" + "/" + part +
                "/" + fileName;
    }
}
