package com.zipmart.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptUltil {

    private static CryptUltil crypt = null;

    public static CryptUltil getCrypt() {
        if (crypt == null) {
            crypt = new CryptUltil();
        }
        return crypt;
    }

    public static void setCrypt(CryptUltil aCrypt) {
        crypt = aCrypt;
    }

    private final String salt_pass = "dhapsowidnshywipldkhtsb@&ndjaspsad|";
    private final String pepper_pass = "secretrandom";

    public String enCodePass(String pass) {
        String str = pass + salt_pass + pepper_pass;
        String md5_hex = DigestUtils.md5Hex(str).toUpperCase();
        return md5_hex;
    }

    public String deCodePass(String pass) {
        String str = pass + salt_pass + pepper_pass;
        String md5_hex = DigestUtils.md5Hex(str).toUpperCase();
        return md5_hex;
    }

    public boolean checkPass(String pass, String hash_pass) {
        if (pass.equals(hash_pass)) {
            System.out.println("Verify");
            return true;
        } else {
            System.out.println("Invalid password.");
            return false;
        }
    }

    public String getSalt_pass() {
        return salt_pass;
    }

    public String getPepper_pass() {
        return pepper_pass;
    }
}
