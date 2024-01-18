package com.zipmart.util;

import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptUltil {

    private static CryptUltil crypt = null;

    private String salt_pass = UUID.randomUUID().toString();
    private String pepper_pass = "secret_employee";
    private String salt = BCrypt.gensalt(12).concat(pepper_pass);
    public static CryptUltil getInstance() {
        if (crypt == null) {
            crypt = new CryptUltil();
        }
        return crypt;
    }

    public String enCodePass(String password){              
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
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
}
