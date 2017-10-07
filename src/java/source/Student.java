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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student")
public class Student extends User{
    
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
    
    public Student(String firstName, String lastName, String email, String password, String dob, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.role = role;
    }
    
    public Student() {
        
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
    
    public Booking createBooking(int bookingid, Tutor tutor) {
        Booking booking = new Booking(bookingid, tutor, this, "active");
        tutor.setStatus("unavailable");
        return booking;
    }
    
    public void cancelBooking(Bookings bookings, int bookingid, Tutor tutor) {
        Booking booking = bookings.checkId(bookingid);
        tutor.setStatus("available");
        booking.setStatus("completed");
    }
    
    public ArrayList<Booking> viewMyBookings(ArrayList<Booking> bookings) {
       ArrayList<Booking> mybookings = new ArrayList<Booking>();
       for(Booking booking: bookings) {
           if(booking.getStudentEmail().equals(email)) {
               mybookings.add(booking);
           }
       }
       return mybookings;
    }
    
    public ArrayList<Booking> viewMyActiveBookings(ArrayList<Booking> bookings) {
       ArrayList<Booking> mybookings = new ArrayList<Booking>();
       for(Booking booking: bookings) {
           if(booking.getStudentEmail().equals(email) && booking.getStatus().equals("active")) {
               mybookings.add(booking);
           }
       }
       return mybookings;
    }
}
