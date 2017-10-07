/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.soap;

import java.io.IOException;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import source.Booking;
import source.BookingApp;
import source.Bookings;
import source.StudentApp;
import source.Student;
import source.Tutor;
import source.TutorApp;

/**
 *
 * @author Jason
 */
@WebService(serviceName = "BookingServiceA")
public class BookingServiceA {

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
    
    private StudentApp getStudentApp() throws JAXBException, IOException, Exception {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        synchronized (application) {
            StudentApp studentApp = (StudentApp) application.getAttribute("studentApp");
            if (studentApp == null) {
                studentApp = new StudentApp();
                studentApp.setFilePath(application.getRealPath("WEB-INF/students.xml"));
                application.setAttribute("studentApp", studentApp);
            }
            return studentApp;
        }
    }
    private TutorApp getTutorApp() throws JAXBException, IOException, Exception {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        synchronized (application) {
            TutorApp tutorApp = (TutorApp) application.getAttribute("tutorApp");
            if (tutorApp == null) {
                tutorApp = new TutorApp();
                tutorApp.setFilePath(application.getRealPath("WEB-INF/tutor.xml"));
                application.setAttribute("tutorApp", tutorApp);
            }
            return tutorApp;
        }
    }
    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    
    /**
     * @param email
     * @param password
     * @return 
     * @throws java.io.IOException 
    **/
    @WebMethod
    public Tutor tutorLogin(@WebParam(name="email")String email, @WebParam(name="password") String password) throws IOException, Exception{
        return getTutorApp().getTutors().login(email, password);
    }
    
    /**
     *
     * @param email
     * @param password
     * @return
     * @throws IOException
     * @throws Exception
     */
    @WebMethod
    public Student studentLogin(@WebParam(name="email")String email, @WebParam(name="password") String password) throws IOException, Exception{
        return getStudentApp().getStudents().login(email, password);
    }
    
    @WebMethod
    public Bookings getAvailable() throws JAXBException, IOException{
        return getBookingApp().getBookings().getByStatus("available");
    }
    
    @WebMethod
    public void makeBooking(Student student, Tutor tutor) throws JAXBException, IOException{
        getBookingApp().getBookings().addBooking(new Booking(tutor, student));
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        getBookingApp().updateBookings(getBookingApp().getBookings(), application.getRealPath("WEB-INF/bookings.xml"));
    }
}
