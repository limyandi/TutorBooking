/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap;

import java.io.IOException;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import source.*;
/**
 *
 * @author limyandivicotrico
 */
@WebService(serviceName = "BookingApp")
public class BookingSOAP {

    @Resource
    private WebServiceContext context;
    
    private BookingApp getBookingApp() throws JAXBException, IOException {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
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
    
    public Bookings fetchBookings() throws JAXBException, IOException, IOException {
        return getBookingApp().getBookings();
    }
    
    public Booking fetchBookingByID(int id) throws Exception{
        return getBookingApp().getBookings().getBooking(id);
    }
    
    public Bookings fetchBookingsByEmail(String email) throws Exception{
        return getBookingApp().getBookings().getByEmail(email);
    }
    
    public Bookings fetchBookingsByName(String subjectname) throws Exception{
        return getBookingApp().getBookings().getBySubject(subjectname);
    }
    
    public Bookings fetchBookingsByStatus(String status) throws Exception{
        return getBookingApp().getBookings().getByStatus(status);
    }
    
}
