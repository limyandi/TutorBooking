/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.registerlogin;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author limyandivicotrico
 */
public class Login implements Serializable {
    private String email;
    private String password;
    private Hashtable errors;
    
    public Login() {
        this.email = "";
        this.password = "";
    }
    
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public boolean validateLogin() {
        boolean allValidated = true;
        Pattern emailPattern = Pattern.compile("([A-Za-z\\._]+)@(([a-z-]+)\\.)+([a-z-]+)");
        Matcher emailMatcher = emailPattern.matcher(email);
        if(!emailMatcher.find()) {
            errors.put("email", "Email does not match!");
            allValidated = false;
        }
        Pattern passwordPattern = Pattern.compile("([A-Za-z0-9!@#$%^*\\?]{6,16})");      
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.find()) {
            errors.put("password", "Your password needs to be at least 6 characters long and maximum 16 characters");
            allValidated = false;
        }
        return allValidated;
    }

    public String getErrorMessage(String errorType) {
        String errorMessage = (String)errors.get(errorType.trim());
        if(errorMessage == null) 
            return "";
        return errorMessage;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
