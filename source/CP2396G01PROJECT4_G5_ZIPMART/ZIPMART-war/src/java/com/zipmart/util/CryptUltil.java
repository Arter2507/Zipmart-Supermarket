package com.zipmart.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptUltil {

    private static CryptUltil crypt = null;

    public static CryptUltil getInstance() {
        if (crypt == null) {
            crypt = new CryptUltil();
        }
        return crypt;
    }

    public String enCodePass(String password, String salt){
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }
}
