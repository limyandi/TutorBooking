/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author limyandivicotrico
 */
public class UserApp implements Serializable {
    private String studentFilePath;
    private String tutorFilePath;
    private String bookingFilePath;
    private Students students;
    private Tutors tutors;
    private Bookings bookings;
    
    public UserApp() {
        
    }

    public UserApp(String studentFilePath, String tutorFilePath, String bookingFilePath, Students students, Tutors tutors, Bookings bookings) {
        this.studentFilePath = studentFilePath;
        this.tutorFilePath = tutorFilePath;
        this.bookingFilePath = bookingFilePath;
        this.students = students;
        this.tutors = tutors;
        this.bookings = bookings;
    }
    
    public void updateStudentsXML() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Students.class);

        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(this.studentFilePath);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(students, fos);
        fos.close();
    }
    
    public void updateTutorsXML() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Tutors.class);

        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(this.tutorFilePath);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(tutors, fos);
        fos.close();
    }
    
    public void updateBookingsXML() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Bookings.class);

        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(this.bookingFilePath);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(bookings, fos);
        fos.close();
    }
    
    public void studentRegister(Student student) throws Exception {
        students.addStudent(student);
        updateStudentsXML();
    }
    
    public void tutorRegister(Tutor tutor) throws Exception {
        tutors.addTutor(tutor);
        updateTutorsXML();
    }
    
    /*When we remove a student, we need to change the status of the bookings related 
    so we need Booking here and to change the status of tutor so we need the tutors instance.*/
    public void removeStudent(Student student) throws Exception {
        students.removeStudent(student, bookings.getBookings(), tutors.getTutors());
        updateStudentsXML();
        updateTutorsXML();
        updateBookingsXML();
    }
    
    /*When we remove a tutor, we need to change the status of the bookings related 
    so we need Booking here.*/
    public void removeTutor(Tutor tutor) throws Exception {
        tutors.removeTutor(tutor, bookings.getBookings());
        updateTutorsXML();
        updateBookingsXML();
    }
    
    public void createBooking(Student student, Tutor tutor) throws Exception {
        // Create booking requires an id of the booking and the details of the tutor.
        Booking booking = student.createBooking(bookings.getBookings().size() + 1, tutor);
        bookings.addBooking(booking);
        updateBookingsXML();
        updateTutorsXML();
    }
    
    // We need tutor here because we need to set the status of tutor become available again.
    public void studentCancelBooking(int bookingId, Student student, Tutor tutor) throws Exception {
        student.cancelBooking(bookings, bookingId, tutor);
        updateBookingsXML();
        updateTutorsXML();
    }
    
    public void tutorCancelBooking(int bookingId, Tutor tutor) throws Exception {
        tutor.cancelBooking(bookings, bookingId);
        updateBookingsXML();
        updateTutorsXML();
    }
    
    public void completeBooking(int bookingId, Tutor tutor) throws Exception {
        tutor.completeBooking(bookings, bookingId);
        updateBookingsXML();
        updateTutorsXML();
    }
    
    public void updateDetails(User user, String firstname, String lastname, String password, String dob) throws Exception {
        user.updateDetails(firstname, lastname, password, dob);
        if(user instanceof Tutor) {
            updateTutorsXML();
        }
        else {
            updateStudentsXML();
        }
    }

    public String getStudentFilePath() {
        return studentFilePath;
    }

    public void setStudentFilePath(String studentFilePath) throws Exception {
        this.studentFilePath = studentFilePath;
        JAXBContext jc = JAXBContext.newInstance(Students.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(studentFilePath);
        this.setStudents((Students) unmarshaller.unmarshal(fin));
        fin.close();
    }

    public String getTutorFilePath() {
        return tutorFilePath;
    }

    public void setTutorFilePath(String tutorFilePath) throws Exception {
        this.tutorFilePath = tutorFilePath;
        JAXBContext jc = JAXBContext.newInstance(Tutors.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(tutorFilePath);
        this.setTutors((Tutors) unmarshaller.unmarshal(fin));
        fin.close();
    }

    public String getBookingFilePath() {
        return bookingFilePath;
    }

    public void setBookingFilePath(String bookingFilePath) throws Exception {
        this.bookingFilePath = bookingFilePath;
        JAXBContext jc = JAXBContext.newInstance(Bookings.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(bookingFilePath);
        this.setBookings((Bookings) unmarshaller.unmarshal(fin));
        fin.close();
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Tutors getTutors() {
        return tutors;
    }

    public void setTutors(Tutors tutors) {
        this.tutors = tutors;
    }

    public Bookings getBookings() {
        return bookings;
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }
    
    
    
}
