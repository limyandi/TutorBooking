package source;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    private String email;
    private String firstName;
    private String password;
    private String dob;
    private String role;
    
    public User() {
    
    }

    public User(String email, String firstName, String password, String dob, String role) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.dob = dob;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {
        return dob;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
