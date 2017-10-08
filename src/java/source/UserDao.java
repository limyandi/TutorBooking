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
    void createStudent(Student student);
    void createTutor(Tutor tutor);
    void createBooking(Student student, Tutor tutor);
    // Read
    // Update
    void updateStudents();
    void updateTutors();
    void updateBookings();
    // Delete
    void deleteStudent(Student student);
    void deleteTutor(Tutor tutor);
}
