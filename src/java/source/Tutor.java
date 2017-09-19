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
@XmlRootElement(name = "Tutor")
public class Tutor extends User{
    @XmlElement
    private String subject;
    @XmlElement
    private String status;
    
    public Tutor(String firstName, String lastName, String email, String password, String dob, String role, String specialty, String available) {
        super(firstName, lastName, email, password, dob, role);
        this.subject = specialty;
        this.status = available;
    }

    public Tutor() {
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

  
    
    
}