/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

/**
 *
 * @author limyandivicotrico
 */
public class BookingApp {
    private Bookings bookings;
    private String filePath;

    public BookingApp() {
    }

    public BookingApp(Bookings bookings, String filePath) {
        this.bookings = bookings;
        this.filePath = filePath;
    }

    public Bookings getBookings() {
        return bookings;
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) throws IOException, JAXBException {
        this.filePath = filePath;
        JAXBContext jc = JAXBContext.newInstance(Bookings.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(filePath);
        this.setBookings((Bookings) unmarshaller.unmarshal(fin));
        fin.close();
    }
    
    public void updateBookings(Bookings bookings, String filePath) throws PropertyException, FileNotFoundException, JAXBException, IOException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        JAXBContext jc = JAXBContext.newInstance(Bookings.class);

        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(filePath);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(bookings, fos);
        fos.close();
    }
    
//    public void addBooking(String tutorEmail, String studentEmail, String filePath) throws JAXBException, IOException {
//        Tutor tutor = this.tutor.getTutor(tutorEmail);
//        Student student = this.student.getStudent(studentEmail);
//        
//        if(tutor != null && student!= null && tutor.getStatus().equals("available")){
//            this.bookings.addBooking(tutor, student);
//        }
//        this.updateBookings(bookings, );
//        this.updateTutorsXML();
//    }
}
