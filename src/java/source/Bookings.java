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
    
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
}
