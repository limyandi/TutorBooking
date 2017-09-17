package source;

import java.io.Serializable;
import javax.xml.bind.annotation.*;


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
@XmlRootElement (name = "student")
public class User implements Serializable {
    @XmlElement(name= "firstname")
    private String firstName;
    @XmlElement(name = "lastname")
    private String lastName;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "password")
    private String password;
    @XmlElement(name = "dateofbirth")
    private String dob;
    @XmlElement(name = "usertype")
    private String role;

    public User() {

    }

    public User(String firstName, String lastName, String email, String password, String dob, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
    
    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getRole() {
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
