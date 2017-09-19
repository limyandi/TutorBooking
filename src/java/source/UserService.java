/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author Jason
 */

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
@Path("/user")
public class UserService 
{
    @Context
    private ServletContext application;
    
    private UserApp getUserApp() throws Exception
    {
        synchronized (application)
                {
                    UserApp userapp = (UserApp) application.getAttribute("userApp");
                    if(userapp == null)
                    {
                        userapp = new UserApp();
                        userapp.setFilePath(application.getRealPath("WEB-INF/students.xml"));
                        application.setAttribute("userApp", userapp);
                    }
                    return userapp;
                }
    }
    
    @Path("hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }
    
    @Path("student")
    @GET
    @Produces("text/xml")
    public Users getUsers() throws Exception{
        return getUserApp().getUsers();
    }
}
