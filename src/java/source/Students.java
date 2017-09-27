/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author limyandivicotrico
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "students", namespace = "http://www.uts.edu.au/31284/wsd-students")
public class Students {
    @XmlElement(name="student")
    private ArrayList<Student> students = new ArrayList<Student>();
    
    public Students(){
        
    }
    
    public Students(ArrayList<Student> students){
        this.students = students;
    }
    
    public void addStudent(Student student){
        students.add(student);
    }
    
    public void removeStudent(Student student){
        students.remove(student);
    }
    
    public Student login(String email, String password){
        for(Student student: students){
            if(student.getEmail().equals(email)&&student.getPassword().equals(password))
                return student;
        }
        return null;
    }
    
    // Check if the student with this email exists.
    public Student checkExistingEmail(String email) {
        for(Student student: students) {
            if(student.getEmail().equals(email))
                return student;
        }
        return null;
    }
}
