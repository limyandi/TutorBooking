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
    
    public String getFilePath(){
        return this.filePath;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
     
    public userApp(Users users) {
        this.users = users;
    }
    
    public void setFilePath(String filePath) throws Exception{
        this.filePath = filePath;
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(filePath);
        this.setUsers((Users) unmarshaller.unmarshal(fin));
        fin.close();
    }
    
    public void updateUsers() throws Exception{
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(this.getFilePath());
        fos.close();
    }
            
}
