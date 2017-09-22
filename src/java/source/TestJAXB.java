package source;

import java.util.*;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class TestJAXB implements Serializable {

    public static void main(String[] args) throws Exception {
        // Test for Students, dont forget to change the JAXBContext and schema location and the marshalling.
        /*Students students = new Students();
        Student ryan = new Student("Ryan", "Heise", "Ryanheise@gmail.com", "ryan?1", "09/09/1998", "student");
        students.addStudent(ryan); */
        // Test for Tutor, dont forget to change the JAXBContext and schema location and the marshalling.
        Tutors tutors = new Tutors();
        Tutor rico = new Tutor("Ryan", "Heise", "Ryanheise@gmail.com", "ryan?1", "09/09/1998", "student", "WSD", "available");
        tutors.addTutor(rico);
      
        // Boilerplate code to convert objects to XML...
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
        Schema schema = sf.newSchema(new File("web/WEB-INF/tutors.xsd")); 
        
        JAXBContext jc = JAXBContext.newInstance(Tutors.class);
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
        
        m.marshal(tutors, System.out);
    }
}
