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
        <!-- TODO: ADD CANCEL ACCOUNT -->
        <!-- TODO: ADD TUTOR --> 
        <%
            Student user = (Student) session.getAttribute("user");
            String fname = request.getParameter("Fname");
            String lname = request.getParameter("Lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
        %>
        <h1>Account Page</h1>
        <% 
            if(email != null && fname != null && lname != null 
                    && password != null && dob !=null) {
            //TODO: Do we need to update this to XML too? And if we do how?
            user.setEmail(email);
            user.setFirstName(fname);
            user.setLastName(lname);
            user.setPassword(password);
            user.setDob(dob);
            }
        %>
        <form method="post" action="main.jsp">
            <table>
                <tr><td>First Name:</td><td><input type="text" name="Fname" value="<%= user.getFirstName() %>"></td></tr>
                <tr><td>Last Name:</td><td><input type="text" name="Lname" value="<%= user.getLastName() %>"></td></tr>
                <tr><td>Email:</td><td><input type="email" name="email" value="<%= user.getEmail() %>"></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password" value="<%= user.getPassword() %>"></td></tr>
                <tr><td>Date of Birth:</td><td><input type="date" name="dob" value="<%= user.getDob() %>"></td></tr>
                <tr><td></td><td><input type="submit" value="Update"></td></tr>
            </table>            
        </form>
    </body>
</html>
