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
import source.User;
import source.userApp;
@Path("/user")
public class UserService 
{
    @Context
    private ServletContext application;
    
    private userApp getUserApp() throws Exception
    {
        synchronized (application)
                {
                    userApp userapp = (userApp) application.getAttribute("userApp");
                    if(userapp == null)
                    {
                        userapp = new userApp();
                        userapp.setFilePath(application.getRealPath("WEB-INF/users.xml"));
                        application.setAttribute("userApp", userapp);
                    }
                    return userapp;
                }
    }
    
    
    @Path("student")
    @GET
    @Produces("text/xml")
    public Users getUsers() throws Exception{
        return getUserApp().getUsers();
    }
}
