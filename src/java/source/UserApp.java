/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 *
 * @author limyandivicotrico
 */

public class UserApp implements Serializable {

    private Users users;
    private String filePath;
    private String schemaPath;

    public UserApp() {
    }

    public UserApp(Users users, String filePath, String schemaPath) {
        this.users = users;
        this.filePath = filePath;
        this.schemaPath = schemaPath;
    }

    public String getSchemaPath() {
        return schemaPath;
    }

    public void setSchemaPath(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setFilePath(String filePath) throws Exception {
        this.filePath = filePath;
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(filePath);
        this.setUsers((Users) unmarshaller.unmarshal(fin));
        fin.close();
    }

    public void updateUsers(Users users, String filePath, String schemaPath) throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(schemaPath));

        JAXBContext jc = JAXBContext.newInstance(Users.class);

        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(this.getFilePath());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.setSchema(schema);
        marshaller.setEventHandler(new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent event) {
                System.out.println("MESSAGE:  " + event.getMessage());
                return true;
            }
        });

        marshaller.marshal(users, fos);
        fos.close();
    }

}
