/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 *
 * @author limyandivicotrico
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "booking")
public class Booking implements Serializable {
    
    @XmlAttribute(name="id")
    private int id;
    @XmlElement(name= "tutoremail")
    private String tutorEmail;
    @XmlElement(name = "tutorfirstname")
    private String tutorFirstName;
    @XmlElement(name = "tutorlastname")
    private String tutorLastName;
    @XmlElement(name = "subjectname")
    private String subjectName;
    @XmlElement(name = "studentemail")
    private String studentEmail;
    @XmlElement(name = "studentfirstname")
    private String studentFirstName;
    @XmlElement(name = "studentlastname")
    private String studentLastName;
    @XmlElement(name = "status")
    private String status;
    
    public Booking(Tutor tutor, Student student){
        this.status="incomplete";
        this.studentEmail=student.getEmail();
        this.studentFirstName=student.getFirstName();
        this.studentLastName=student.getLastName();
        this.subjectName=tutor.getSubject();
        this.tutorEmail=tutor.getEmail();
        this.tutorFirstName=tutor.getFirstName();
        this.tutorLastName=tutor.getLastName();
    }
    
    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getTutorFirstName() {
        return tutorFirstName;
    }

    public void setTutorFirstName(String tutorFirstName) {
        this.tutorFirstName = tutorFirstName;
    }

    public String getTutorLastName() {
        return tutorLastName;
    }

    public void setTutorLastName(String tutorLastName) {
        this.tutorLastName = tutorLastName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
    
}
