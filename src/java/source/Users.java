/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;
import java.util.ArrayList;
/**
 *
 * @author limyandivicotrico
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class Users {
    private ArrayList<User> users;
    
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
            if(user.getEmail().equals(email)&&user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
