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
@XmlRootElement(name = "tutors", namespace="http://www.uts.edu.au/31284/wsd-tutors")
public class Tutors{
    @XmlElement(name = "tutor")
    private ArrayList<Tutor> tutors = new ArrayList<Tutor>();
    
    public Tutors(){
        
    }
    
    public Tutors(ArrayList<Tutor> tutors){
        this.tutors = tutors;
    }
    
    public void addTutor(Tutor tutor){
        this.tutors.add(tutor);
    }
    
    public void removeTutor(Tutor tutor, ArrayList<Booking> bookings){
        this.tutors.remove(tutor);
        for(Booking booking: bookings) {
            if(booking.getTutorEmail().equals(tutor.getEmail())) {
                booking.setStatus("cancelled");
            }
        }
    }
    
    public Tutor login(String email, String password){
        for(Tutor Tutor: tutors){
            if(Tutor.getEmail().equals(email)&&Tutor.getPassword().equals(password))
                return Tutor;
        }
        return null;
    }
    
    // Check if the tutor with the existing email exists.
    public Tutor checkExistingEmail(String email) {
        for(Tutor tutor: tutors) {
            if(tutor.getEmail().equals(email))
                return tutor;
        }
        return null;
    }
    
    public ArrayList<Tutor> getByStatus(String status) {
        ArrayList<Tutor> tutorslist = new ArrayList<Tutor>();
        for(Tutor tutor: this.tutors){
            if(tutor.getStatus().equals(status)){
                tutorslist.add(tutor);
            }   
        }
        return tutorslist;
    }
    
    public ArrayList<Tutor> getByFirstName(String firstName) {
        ArrayList<Tutor> tutorslist = new ArrayList<Tutor>();
        for(Tutor tutor: this.tutors){
            if(tutor.getFirstName().equals(firstName)){
                tutorslist.add(tutor);
            }   
        }
        return tutorslist;
    }
    
    public ArrayList<Tutor> getByLastName(String lastName) {
        ArrayList<Tutor> tutorslist = new ArrayList<Tutor>();
        for(Tutor tutor: this.tutors){
            if(tutor.getLastName().equals(lastName)){
                tutorslist.add(tutor);
            }   
        }
        return tutorslist;
    }
    
    public ArrayList<Tutor> getBySubject(String subject) {
        ArrayList<Tutor> tutorslist = new ArrayList<Tutor>();
        for(Tutor tutor: this.tutors){
            if(tutor.getSubject().equals(subject)){
                tutorslist.add(tutor);
            }   
        }
        return tutorslist;
    }

    public ArrayList<Tutor> getTutors() {
        return tutors;
    }
    
}
