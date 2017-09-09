<%-- 
    Document   : main
    Created on : Sep 2, 2017, 5:01:11 PM
    Author     : limyandivicotrico
--%>
<%@page import="source.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <% 
        String fname = request.getParameter("Fname");
        String lname = request.getParameter("Lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String userType = request.getParameter("usertype");
        String filePath = application.getRealPath("WEB-INF/students.xml"); 
    %>
    <jsp:useBean id="userApp" class="source.userApp" scope="application">
        <jsp:setProperty name="userApp" property="filePath" value="<%=filePath%>"/>
    </jsp:useBean>
    <%
        Users users = userApp.getUsers();
        User user = users.checkExistingEmail(email);
    %>
    <body>
        <% 
        if (user == null) {
            User newUser = new User(fname, lname, email, password, dob, userType);
            session.setAttribute("user", newUser);
            users.addUser(newUser);
            userApp.updateUsers(users, filePath);
        %>
        <h1>Main Page</h1>
        <!-- Handle if user is tutor or student 
        (if user is tutor, 
        do not display search form) -->
        <form action="main.jsp" method="POST">
            <table>
                <tr><td><input type="text" name="category"/></td>
                    <td><select name="catval">
                    <option value="subject">Subject</option>
                    <option value="tutorname">Tutor Name</option>
                    <option value="tutorstatus">Tutor Status</option>
                        </select></td>
            </tr>
            <tr><td><input type="submit" value="Enter"/></td></tr>
            </table>
        </form>
        <!-- Display result here, 
        check the value of 
        'category' and 'catval'
        -->
        
        
        <a href='booking.jsp'>Booking</a>
        <a href='account.jsp'>Account</a>
        <% } else { %>
        <p>This email is already exist, click <a href="register.html"> here</a> to register again.</p>   
        <% } %>
    </body>
</html>
