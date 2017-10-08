/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author limyandivicotrico
 */
public interface UserDao {
    // Create
    void addStudent(Student student);
    void addTutor(Tutor tutor);
    void addBooking(Student student, Tutor tutor);
    
    // Read
    Students getStudents();
    Tutors getTutors();
    Bookings getBookings();
    
    // Update
    void updateDetails(User user, String firstname, String lastname, String password, String dob);
    /* cancel and complete does not delete anything, it just changes status so it is included in update*/
    void studentCancelBooking(int bookingId, Student student, Tutor tutor);
    void tutorCancelBooking(int bookingId, Tutor tutor);
    void completeBooking(int bookingId, Tutor tutor);
    
    // Delete
    void removeStudent(Student student);
    void removeTutor(Tutor tutor);
}
