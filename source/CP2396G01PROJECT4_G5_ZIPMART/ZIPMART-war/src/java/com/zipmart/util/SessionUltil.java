/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.util;

import javax.servlet.http.HttpServletRequest;

public class SessionUltil {

    private static SessionUltil sessionUltil = null;

    public static SessionUltil getInstance() {
        if (sessionUltil == null) {
            sessionUltil = new SessionUltil();
        }
        return sessionUltil;
    }

    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
