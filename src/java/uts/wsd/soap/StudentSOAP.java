/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import source.*;

/**
 *
 * @author limyandivicotrico
 */
@WebService(serviceName = "StudentApp")
public class StudentSOAP {
    @Context
    private WebServiceContext context;

    //GET THE STUDENT APP
    private StudentApp getStudentApp() throws JAXBException, IOException, Exception {
        ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        synchronized (application) {
            StudentApp studentApp = (StudentApp) application.getAttribute("studentApp");
            if (studentApp == null) {
                studentApp = new StudentApp();
                studentApp.setFilePath(application.getRealPath("web/WEB-INF/students.xml"));
                application.setAttribute("studentApp", studentApp);
            }
            return studentApp;
        }
    }
    
    public Student studentLogin(String email, String password) {
        try {
            return getStudentApp().getStudents().login(email, password);
        } catch (IOException ex) {
            Logger.getLogger(StudentSOAP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StudentSOAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //TODO: LOGOUT.
    
    //TODO: CREATE A BOOKING.
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
