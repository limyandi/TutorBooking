<%-- 
    Document   : account
    Created on : Sep 5, 2017, 9:24:24 PM
    Author     : limyandivicotrico
--%>
<%@page import="source.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Page</title>
    </head>
    <body>
        <!-- haven't done yet-->
        <%
            User user = (User) session.getAttribute("user");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String favcol = request.getParameter("favcol");
        %>
        <h1>Account Page</h1>
        <% 
            if(email != null && name != null && password != null 
                    && gender != null && favcol !=null) {
            //TODO: Do we need to update this to XML too?
            user.setEmail(email);
            user.setFirstName(name);
            user.setPassword(password);
            }
        %>


    </body>
</html>
