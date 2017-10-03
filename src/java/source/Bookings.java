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
@XmlRootElement(name = "bookings", namespace = "http://www.uts.edu.au/31284/wsd-bookings")
public class Bookings {
    @XmlElement(name = "booking")
    private ArrayList<Booking> bookings = new ArrayList<Booking>();

    public Bookings() {
    }
    
    public Bookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }
    
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }
    public void addBookings(Bookings bookings){
        for(Booking booking:bookings.getBookings()){
            this.bookings.add(booking);
        }
        
    }
    
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
    
    public Booking getBooking(int id){
        for(Booking booking: this.bookings){
            if(booking.getId() == id){
                return booking;
            }   
        }
        return null;
    }
    
    public Bookings getByStatus(String status){
        Bookings bookings = new Bookings();
        for(Booking booking: this.bookings){
            if(booking.getStatus().equals(status)){
                bookings.addBooking(booking);
            }
        }
        return bookings;
    }
    
    public Bookings getByEmail(String email){
        Bookings bookings = new Bookings();
        for(Booking booking: this.bookings){
            if(booking.getStudentEmail().equals(email)){
                bookings.addBooking(booking);
            }
        }
        return bookings;
    }
    
    public Bookings getBySubject(String subject){
        Bookings bookings = new Bookings();
        for(Booking booking: this.bookings){
            if(booking.getSubjectName().equals(subject)){
                bookings.addBooking(booking);
            }
        }
        return bookings;
    }
}
