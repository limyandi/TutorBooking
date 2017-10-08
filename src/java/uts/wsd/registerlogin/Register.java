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
public class Register implements Serializable {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private Hashtable errors;
    
    public Register() {
        this.email = "";
        this.password = "";
        this.firstname = "";
        this.lastname = "";
        errors = new Hashtable();
    }
    
    public Register(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;  
        errors = new Hashtable();
    }
    
    public boolean validateRegister() {
        boolean allValidated = true;
        Pattern emailPattern = Pattern.compile("([A-Za-z\\._]+)@(([a-z-]+)\\.)+([a-z-]+)");
        Matcher emailMatcher = emailPattern.matcher(email);
        if(!emailMatcher.find()) {
            errors.put("email", "Please enter a valid email");
            allValidated = false;
        }
        Pattern passwordPattern = Pattern.compile("([A-Za-z0-9!@#$%^*\\?]{6,16})");      
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.find()) {
            errors.put("password", "Please enter valid password");
            allValidated = false;
        }
        Pattern namePattern = Pattern.compile("([A-Za-z]+)");
        Matcher nameMatcher = namePattern.matcher(firstname);
        
        if(!nameMatcher.find()) {
            errors.put("firstname", "Please enter valid name");
            errors.put("lastname", "Please enter a valid name");
            allValidated = false;
        }
   
        return allValidated;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getErrorMessage(String errorType) {
        String errorMessage = (String)errors.get(errorType.trim());
        if(errorMessage == null) 
            return "";
        return errorMessage;
    }
}
