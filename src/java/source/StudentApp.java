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

public class StudentApp implements Serializable {

    private Students students;
    private String filePath;

    public StudentApp() {
    }

    public StudentApp(Students students, String filePath) {
        this.students = students;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public Students getStudents() {
        return this.students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public void setFilePath(String filePath) throws Exception {
        this.filePath = filePath;
        JAXBContext jc = JAXBContext.newInstance(Students.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        FileInputStream fin = new FileInputStream(filePath);
        this.setStudents((Students) unmarshaller.unmarshal(fin));
        fin.close();
    }

    public void updateStudents(Students students, String filePath) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Students.class);

        Marshaller marshaller = jc.createMarshaller();
        FileOutputStream fos = new FileOutputStream(filePath);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(students, fos);
        fos.close();
    }

}
