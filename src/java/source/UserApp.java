/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author limyandivicotrico
 */
public class UserApp implements Serializable, UserDao {
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
    
    public void updateStudents() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Students.class);

            Marshaller marshaller = jc.createMarshaller();
            FileOutputStream fos = new FileOutputStream(this.studentFilePath);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(students, fos);
            fos.close(); 
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateTutors() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Tutors.class);

            Marshaller marshaller = jc.createMarshaller();
            FileOutputStream fos = new FileOutputStream(this.tutorFilePath);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(tutors, fos);
            fos.close();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateBookings() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Bookings.class);

            Marshaller marshaller = jc.createMarshaller();
            FileOutputStream fos = new FileOutputStream(this.bookingFilePath);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(bookings, fos);
            fos.close();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void addStudent(Student student) {
        students.addStudent(student);
        updateStudents();
    }
    
    @Override
    public void addTutor(Tutor tutor) {
        tutors.addTutor(tutor);
        updateTutors();
    }
    
    /*When we remove a student, we need to change the status of the bookings related 
    so we need Booking here and to change the status of tutor so we need the tutors instance.*/
    @Override
    public void removeStudent(Student student) {
        students.removeStudent(student, bookings.getBookings(), tutors.getTutors());
        updateStudents();
        updateTutors();
        updateBookings();
    }
    
    /*When we remove a tutor, we need to change the status of the bookings related 
    so we need Booking here.*/
    @Override
    public void removeTutor(Tutor tutor)  {
        tutors.removeTutor(tutor, bookings.getBookings());
        updateTutors();
        updateBookings();
    }
    
    @Override
    public void addBooking(Student student, Tutor tutor) {
        // Create booking requires an id of the booking and the details of the tutor.
        Booking booking = student.createBooking(bookings.getBookings().size() + 1, tutor);
        bookings.addBooking(booking);
        updateBookings();
        updateTutors();
    }
    
    // We need tutor here because we need to set the status of tutor become available again.
    @Override
    public void studentCancelBooking(int bookingId, Student student, Tutor tutor) {
        student.cancelBooking(bookings, bookingId, tutor);
        updateBookings();
        updateTutors();
    }
    
    @Override
    public void tutorCancelBooking(int bookingId, Tutor tutor) {
        tutor.cancelBooking(bookings, bookingId);
        updateBookings();
        updateTutors();
    }
    
    @Override
    public void completeBooking(int bookingId, Tutor tutor) {
        tutor.completeBooking(bookings, bookingId);
        updateBookings();
        updateTutors();
    }
    
    @Override
    public void updateDetails(User user, String firstname, String lastname, String password, String dob) {
        user.updateDetails(firstname, lastname, password, dob);
        if(user instanceof Tutor) {
            updateTutors();
        }
        else {
            updateStudents();
        }
    }
    
    @Override
    public Tutor readTutor(String email) {
        return tutors.checkExistingEmail(email);
    }
    
    // TODO: DELETE IF NOT NEEDED LATER.
    @Override
    public Booking readBooking(int id) {
        return bookings.checkId(id);
    }
    
    @Override
    public Student readStudent(String email) {
        return students.checkExistingEmail(email);
    }
    
    @Override
    public ArrayList<Tutor> getTutorBySubject(String subject) {
        return tutors.getBySubject(subject);
    }
    
    @Override
    public ArrayList<Tutor> getTutorByStatus(String status) {
        return tutors.getByStatus(status);
    }
    
    @Override
    public ArrayList<Tutor> getTutorByFirstName(String firstName) {
        return tutors.getByFirstName(firstName);
    }
    
    @Override
    public ArrayList<Tutor> getTutorByLastName(String lastName) {
        return tutors.getByLastName(lastName);
    }
    
    @Override
    public ArrayList<Booking> getBookingBySubject(String subject) {
        return bookings.getBySubject(subject);
    }
    
    @Override
    public ArrayList<Booking> getBookingByStudentEmail(String email) {
        return bookings.getByEmail(email);
    }
    
    @Override
    public ArrayList<Booking> getBookingByStatus(String status) {
        return bookings.getByStatus(status);
    }

    public String getStudentFilePath() {
        return studentFilePath;
    }

    @Override
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

    @Override
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

    @Override
    public void setBookingFilePath(String bookingFilePath) throws Exception {
        this.bookingFilePath = bookingFilePath;
        JAXBContext jc = JAXBContext.newInstance(Bookings.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(bookingFilePath);
        this.setBookings((Bookings) unmarshaller.unmarshal(fin));
        fin.close();
    }

    @Override
    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    @Override
    public Tutors getTutors() {
        return tutors;
    }

    public void setTutors(Tutors tutors) {
        this.tutors = tutors;
    }

    @Override
    public Bookings getBookings() {
        return bookings;
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }
}
