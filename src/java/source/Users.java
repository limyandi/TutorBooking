/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author limyandivicotrico
 */
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "students", namespace="http://www.uts.edu.au/31284/wsd-students")
public class Users implements Serializable {
    @XmlElement(name = "student")
    private ArrayList<User> users = new ArrayList<User>();
    
    public Users(){
        
    }
    
    public Users(ArrayList<User> users){
        this.users=users;
    }
    
    public void addUser(User user){
        users.add(user);
    }
    
    public void removeUser(User user){
        this.users.remove(user);
    }
    
    public User login(String email, String password){
        for(User user: this.users){
            if(user.getEmail().equals(email)&&user.getPassword().equals(password))
                return user;
        }
        return null;
    }
    
    //TODO: May be too redundant
    public User checkExistingEmail(String email) {
        for(User user: this.users) {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }
}
