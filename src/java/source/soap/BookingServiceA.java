/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.soap;

import java.io.IOException;
import java.util.ArrayList;
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
import source.Tutors;
import source.UserApp;

/**
 *
 * @author Jason
 */
@WebService(serviceName = "BookingServiceA")
public class BookingServiceA {

    @Resource
    private WebServiceContext context;

    private UserApp getUserApp() throws JAXBException, IOException, Exception {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        synchronized (application) {
            UserApp userApp = (UserApp) application.getAttribute("userApp");
            if (userApp == null) {
                userApp = new UserApp();
                userApp.setBookingFilePath(application.getRealPath("WEB-INF/bookings.xml"));
                userApp.setStudentFilePath(application.getRealPath("WEB-INF/students.xml"));
                userApp.setTutorFilePath(application.getRealPath("WEB-INF/tutors.xml"));
                application.setAttribute("userApp", userApp);
            }
            return userApp;
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
    
    @WebMethod
    public ArrayList<Tutor> getTutors() throws IOException, Exception{
        return getUserApp().getTutors().getTutors();
    }
    @WebMethod
    public ArrayList<Tutor> getTutorsByStatus(String status) throws IOException, Exception{
        return getUserApp().getTutors().getByStatus(status);
    }
    
    @WebMethod
    public ArrayList<Tutor> getSubjectTutors(String subject) throws IOException, Exception{
        return getUserApp().getTutors().getBySubject(subject);
    }
    
    /**
     * @param email
     * @param password
     * @return 
     * @throws java.io.IOException 
    **/
    @WebMethod
    public Tutor tutorLogin(@WebParam(name="email")String email, @WebParam(name="password") String password) throws IOException, Exception{
        return getUserApp().getTutors().login(email, password);
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
        return getUserApp().getStudents().login(email, password);
    }
    
    @WebMethod
    public Bookings getAvailable() throws JAXBException, IOException, Exception{
        return getUserApp().getBookings().getByStatus("available");
    }
    
    @WebMethod
    public void makeBooking(Student student, Tutor tutor) throws JAXBException, IOException, Exception{
        getUserApp().getBookings().addBooking(new Booking(tutor, student));
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        getUserApp().updateBookingsXML();
    }
    
    @WebMethod
    public void makeEmailBooking(Student student, String tutorEmail) throws IOException, Exception{
        Tutor tutor = getUserApp().getTutors().checkExistingEmail(tutorEmail);
        tutor.setStatus("unavailable");
        getUserApp().getBookings().addBooking(new Booking(tutor, student));
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        getUserApp().updateBookingsXML();
    }
}