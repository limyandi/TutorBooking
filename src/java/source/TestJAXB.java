package source;

import java.util.*;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class TestJAXB implements Serializable {

    public static void main(String[] args) throws Exception {
        Users users = new Users();
        User ryan = new User("Ryan", "Heise", "Ryanheise@gmail.com", "ryan?1", "09/09/1998", "student");
        users.addUser(ryan);
        // Boilerplate code to convert objects to XML...
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
        Schema schema = sf.newSchema(new File("web/WEB-INF/students.xsd")); 
        
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        m.setSchema(schema);
        m.setEventHandler(new ValidationEventHandler() {
           @Override
           public boolean handleEvent(ValidationEvent event) {
               System.out.println("MESSAGE: " + event.getMessage());
               return true;
           }
        });
        
        m.marshal(users, System.out);
    }
}
