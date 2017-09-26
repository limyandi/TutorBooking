/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import javax.xml.bind.annotation.*;

/**
 *
 * @author limyandivicotrico
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutor")
public class Tutor {
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
    @XmlElement(name = "subject")
    private String subject;
    @XmlElement(name = "status")
    private String status;
    private Booking booking;
    
    public Tutor(String firstName, String lastName, String email, String password, String dob, String role, String specialty, String available) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.role = role;
        this.subject = specialty;
        this.status = available;
    }

    public Tutor() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void cancelBooking() {
        booking.setStatus("cancelled");
        status = "available";
    }
  
    
    
}