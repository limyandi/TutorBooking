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
import java.util.ArrayList;
import javax.servlet.ServletContext;
import source.Booking;
import source.UserApp;

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

    private UserApp getUserApp() throws JAXBException, IOException, Exception {
        // The web server can handle requests from different clients in parallel.
        // These are called "threads".
        //
        // We do NOT want other threads to manipulate the application object at the same
        // time that we are manipulating it, otherwise bad things could happen.
        //
        // The "synchronized" keyword is used to lock the application object while
        // we're manpulating it.
        synchronized (application) {
            UserApp userApp = (UserApp) application.getAttribute("userApp");
            if (userApp == null) {
                userApp = new UserApp();
                userApp.setBookingFilePath(application.getRealPath("WEB-INF/bookings.xml"));
                application.setAttribute("userApp", userApp);
            }
            return userApp;
        }
    }

    @Path("list")
    @GET
    @Produces("text/xml")
    public Bookings getBookings() throws JAXBException, IOException, IOException, Exception {
        return getUserApp().getBookings();
    }

    @Path("search")
    @GET
    @Produces("text/xml")
    public Bookings getBookingGeneral(@QueryParam("email") String email, @QueryParam("subject") String subject, @QueryParam("status") String status, @QueryParam("id") String id) throws Exception {
        int searchInt;
        UserApp userApp = getUserApp();
        Bookings bookingRemove = new Bookings();
        Bookings bookingReturn = new Bookings();
        bookingReturn.addBookings(userApp.getBookings());
        if (email != null) {
            for (Booking booking : bookingReturn.getBookings()) {
                if (booking.getStudentEmail() != email) {
                    bookingRemove.addBooking(booking);
                }
            }
        }
        if (subject != null) {
            for (Booking booking : bookingReturn.getBookings()) {
                if (booking.getSubjectName() != subject) {
                    bookingRemove.addBooking(booking);
                }
            }
        }
        if (status != null) {
            for (Booking booking : bookingReturn.getBookings()) {
                if (booking.getStatus() != status) {
                    bookingRemove.addBooking(booking);
                }
            }
        }   
        //convert id to int in case it happens to be in String
        try {
            searchInt = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            searchInt = 99999;
        }
        if (searchInt != 99999) {
            for (Booking booking : bookingReturn.getBookings()) {
                if (booking.getId() != searchInt) {
                    bookingRemove.addBooking(booking);
                }
            }
        }
        bookingReturn.removeBookings(bookingRemove);
        return bookingReturn;
    }

    @Path("idsearch")
    @GET
    @Produces("text/xml")
    public Booking getBookingByID(@QueryParam("id") int id) throws Exception {
        return getUserApp().getBookings().getBooking(id);
    }

    @Path("emailsearch")
    @GET
    @Produces("text/xml")
    public Bookings getBookingByEmail(@QueryParam("email") String email) throws Exception {
        return getUserApp().getBookings().getByEmail(email);
    }

    @Path("subjectsearch")
    @GET
    @Produces("text/xml")
    public Bookings getBookingByName(@QueryParam("subject") String name) throws Exception {
        return getUserApp().getBookings().getBySubject(name);
    }

    @Path("statussearch")
    @GET
    @Produces("text/xml")
    public Bookings getBookingByStatus(@QueryParam("status") String name) throws Exception {
        return getUserApp().getBookings().getByStatus(name);
    }
}