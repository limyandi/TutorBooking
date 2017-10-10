/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author limyandivicotrico
 */
/**
 * Important note The fields firstName and lastName were changed to firstname
 * and lastname respectively. The reason for this break from convention is that
 * the SOAP service we were using returned null if they were named as such.
 * Reasons for this are unknown.
 *
 * @author Jason
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tutor")
public class Tutor extends User {

    @XmlElement(name = "firstname")
    private String firstname;
    @XmlElement(name = "lastname")
    private String lastname;
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

    public Tutor(String firstname, String lastname, String email, String password, String dob, String role, String subject, String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.role = role;
        this.subject = subject;
        this.status = status;
    }

    public Tutor() {
    }

    public String getFirstName() {
        return this.firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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
    
    public void completeBooking(Bookings bookings, int bookingid) {
        Booking booking = bookings.checkId(bookingid);
        booking.setStatus("completed");
        setStatus("available");
    }
    
    public void cancelBooking(Bookings bookings, int bookingid) {
        Booking booking = bookings.checkId(bookingid);
        booking.setStatus("cancelled");
        setStatus("available");
    }
    
    public ArrayList<Booking> viewMyBookings(ArrayList<Booking> bookings) {
       ArrayList<Booking> mybookings = new ArrayList<Booking>();
       for(Booking booking: bookings) {
           if(booking.getTutorEmail().equals(email)) {
               mybookings.add(booking);
           }
       }
       return mybookings;
    }
    
    // Tutor active booking should have only returned one booking, but for reuse sake in the view page, we return arraylist.
    public ArrayList<Booking> viewMyActiveBooking(ArrayList<Booking> bookings) {
       ArrayList<Booking> mybooking = new ArrayList<Booking>();
       for(Booking booking: bookings) {
           if(booking.getTutorEmail().equals(email) && booking.getStatus().equals("active")) {
               mybooking.add(booking);
           }
       }
       return mybooking;
    }
    
    public void updateDetails(String firstName, String lastName, String email, String password, String dob, String specialty) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setDob(dob);
        setSubject(specialty);
    }
    
}
