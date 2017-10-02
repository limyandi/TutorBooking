/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import source.Bookings;
import javax.xml.bind.JAXBException;
import java.io.*;
import javax.servlet.ServletContext;
import source.Booking;
import source.BookingApp;

/**
 *
 * @author Jason
 */
@Path("/bookings")
public class BookingService {

    @Path("hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }
    @Context
    private ServletContext application;
    
    private BookingApp getBookingApp() throws JAXBException, IOException {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
        synchronized (application) {
            BookingApp bookingApp = (BookingApp) application.getAttribute("bookingApp");
            if (bookingApp == null) {
                bookingApp = new BookingApp();
                bookingApp.setFilePath(application.getRealPath("WEB-INF/bookings.xml"));
                application.setAttribute("bookingApp", bookingApp);
            }
            return bookingApp;
        }
    }

    @Path("list")
    @GET
    @Produces("text/xml")
    public Bookings getBookings() throws JAXBException, IOException, IOException {
        return getBookingApp().getBookings();
    }
    
    @Path("idsearch")
    @GET
    @Produces("text/xml")
    public Booking getBookingByID(@QueryParam("id")int id) throws Exception{
        return getBookingApp().getBookings().getBooking(id);
    }
    
    @Path("emailsearch")
    @GET
    @Produces("text/xml")
    public Bookings getBookingByEmail(@QueryParam("email")String email) throws Exception{
        return getBookingApp().getBookings().getByEmail(email);
    }
    
    @Path("subjectsearch")
    @GET
    @Produces("text/xml")
    public Bookings getBookingByName(@QueryParam("subject")String name) throws Exception{
        return getBookingApp().getBookings().getBySubject(name);
    }
    
    @Path("statussearch")
    @GET
    @Produces("text/xml")
    public Bookings getBookingByStatus(@QueryParam("status")String name) throws Exception{
        return getBookingApp().getBookings().getByStatus(name);
    }
}