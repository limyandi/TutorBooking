<%-- 
    Document   : registerAction
    Created on : Sep 13, 2017, 3:06:10 PM
    Author     : limyandivicotrico
--%>
<%@page import="source.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
                if (userType.equals("student")) {
                    User newUser = new User(fname, lname, email, password, dob, userType);
                    session.setAttribute("user", newUser);
                    users.addUser(newUser);
                    userApp.updateUsers(users, filePath);
                } else { //handle if user is tutor

                }
        %>
        <p>Registered successfully. Click <a href="main.jsp"> here </a> to go to the main page.</p>
        <% } else { %>
        <p>User with that email already exists, Click <a href="register.html"> here </a> to register again.</p>
        <% } %>
    </body>
</html>
