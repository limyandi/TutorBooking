
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
import source.Bookings;
import source.Students;
import source.Student;
import source.Tutor;
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
     *
     * @param txt
     * @return
     */
    @WebMethod
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod
    public ArrayList<Tutor> getTutors() throws IOException, Exception {
        return getUserApp().getTutors().getTutors();
    }

    @WebMethod
    public ArrayList<Tutor> getTutorsByStatus(@WebParam(name = "status") String status) throws IOException, Exception {
        return getUserApp().getTutors().getByStatus(status);
    }

    @WebMethod
    public ArrayList<Tutor> getSubjectTutors(@WebParam(name = "subject") String subject) throws IOException, Exception {
        return getUserApp().getTutors().getBySubject(subject);
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws java.io.IOException 
    *
     */
    @WebMethod
    public Tutor tutorLogin(@WebParam(name = "email") String email, @WebParam(name = "password") String password) throws IOException, Exception {
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
    public Student studentLogin(@WebParam(name = "email") String email, @WebParam(name = "password") String password) throws IOException, Exception {
        return getUserApp().getStudents().login(email, password);
    }

    @WebMethod
    public Bookings getAvailable() throws JAXBException, IOException, Exception {
        return getUserApp().getBookings().getByStatus("available");
    }

    @WebMethod
    public void makeBooking(@WebParam(name = "student") Student student, @WebParam(name = "tutor") Tutor tutor) throws JAXBException, IOException, Exception {
        getUserApp().addBooking(student, tutor);
    }

    @WebMethod
    public void makeEmailBooking(@WebParam(name = "student") Student student, @WebParam(name = "tutorEmail") String tutorEmail) throws IOException, Exception {
        UserApp userApp = getUserApp();
        Booking booking = student.createBooking((userApp.getBookings().getBookings().size() + 1), userApp.getTutors().checkExistingEmail(tutorEmail));
        userApp.getBookings().addBooking(booking);
        userApp.updateBookings();
        userApp.updateTutors();
    }

    @WebMethod
    public ArrayList<Booking> getBookingStudentEmail(@WebParam(name = "studentEmail") String studentEmail) throws IOException, Exception {
        return getUserApp().getBookings().getByEmail(studentEmail).getBookings();
    }

    @WebMethod
    public ArrayList<Booking> getBookingByStatus(@WebParam(name = "status") String status, @WebParam(name = "student") Student student) throws IOException, Exception {
        return getUserApp().getBookings().getByStatus(status).getBookings();
    }

    @WebMethod
    public void cancelBooking(@WebParam(name = "student") Student student, @WebParam(name = "id") int id) throws IOException, Exception {
        UserApp userApp = getUserApp();
        userApp.getBookings().getBooking(id).setStatus("cancelled");
        Tutors tutors = userApp.getTutors();
        String email = userApp.getBookings().getBooking(id).getTutorEmail();
        Tutor tutor = tutors.checkExistingEmail(email);
        System.out.println(email);
        tutor.setStatus("available");
        userApp.updateBookings();
        userApp.updateTutors();
    }

    @WebMethod
    public ArrayList<Booking> getStatusTutorBooking(@WebParam(name = "status") String status, @WebParam(name = "tutor") Tutor tutor) throws IOException, Exception {
        UserApp userApp = getUserApp();
        ArrayList<Booking> returnable = new ArrayList<Booking>();
        for (Booking booking : userApp.getBookings().getByStatus(status).getBookings()) {
            if (booking.getTutorEmail().equals(tutor.getEmail())) {
                returnable.add(booking);
            }
        }
        return returnable;
    }

    /**
     *
     * @param tutor
     * @param id
     * @throws IOException
     * @throws Exception
     */
    @WebMethod
    public void cancelBookingTutor(@WebParam(name = "tutor") Tutor tutor, @WebParam(name = "id") int id) throws IOException, Exception {
        getUserApp().tutorCancelBooking(id, tutor);
    }

    @WebMethod
    public void completeBooking(@WebParam(name = "tutor") Tutor tutor, @WebParam(name = "id") int id) throws IOException, Exception {
        getUserApp().completeBooking(id, tutor);
    }
        @WebMethod
    public void removeTutor(@WebParam(name="tutor")Tutor tutor) throws IOException, Exception{
        getUserApp().removeTutor(tutor);
    }
    
    @WebMethod
    public void removeStudent(@WebParam(name="student")Student student) throws IOException, Exception{
        getUserApp().removeStudent(student);
    }
}