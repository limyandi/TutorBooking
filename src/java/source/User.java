/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author limyandivicotrico
 */
public abstract class User {
    public abstract String getFirstName();
   
    public abstract void setFirstName(String firstName);

    public abstract String getLastName();

    public abstract void setLastName(String lastName);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getDob();

    public abstract void setDob(String dob);

    public abstract String getRole();

    public abstract void setRole(String role);
    
    public void setSubject(String subject) {
        
    }
    
    public String getSubject() {
        return "";
    }
}
