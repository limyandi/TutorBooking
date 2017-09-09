/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class userApp implements Serializable{
    private Users users;
    private String filePath;

    public userApp() {
    }
    
    public userApp(String filePath, Users users) {
        this.filePath = filePath;
        this.users = users;
    }
    
    public String getFilePath(){
        return this.filePath;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
    
    public void setFilePath(String filePath) throws Exception {
        this.filePath = filePath;
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(filePath);
        //this.setUsers((Users) unmarshaller.unmarshal(fin));
        users = (Users) unmarshaller.unmarshal(fin);
        fin.close();
    }
    
    public void updateUsers(Users users, String filePath) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(this.getFilePath());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(users, fos);
        fos.close();
    }
            
}
