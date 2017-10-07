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

        %>
        <jsp:useBean id="registerHandler" class="uts.wsd.registerlogin.Register" scope="request">
        </jsp:useBean>
        <%
            registerHandler.setFirstname(fname);
            registerHandler.setLastname(lname);
            registerHandler.setEmail(email);
            registerHandler.setPassword(password);
            if(!registerHandler.validateRegister()) {
                session.setAttribute("registerData", registerHandler);
                response.sendRedirect("register.jsp");
            }
            else {
                String studentFilePath = application.getRealPath("WEB-INF/students.xml");
                String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="userApp" class="source.UserApp" scope="application">
            <jsp:setProperty name="userApp" property="studentFilePath" value="<%=studentFilePath%>"/>
            <jsp:setProperty name="userApp" property="tutorFilePath" value="<%=tutorFilePath%>"/>
        </jsp:useBean>      
        <% 
                Students students = userApp.getStudents();
                Tutors tutors = userApp.getTutors();
                Student student = students.checkExistingEmail(email);
                Tutor tutor = tutors.checkExistingEmail(email);

                if(student == null && tutor == null) {
                    if(userType.equals("student")) {
                        student = new Student(fname, lname, email, password, dob, userType);
                        session.setAttribute("user", student);
                        userApp.studentRegister(student); 
                    }
                    else {
                        String specialty = request.getParameter("specialty");
                        tutor = new Tutor(fname, lname, email, password, dob, userType, specialty, "available");
                        session.setAttribute("user", tutor);
                        userApp.tutorRegister(tutor);
                    }
                    response.sendRedirect("main.jsp");
                }
                else {
        %>
        <p>User with that email already exists, Click <a href="register.jsp"> here </a> to register again.</p>
        <%      } 
            }
        %>
    </body>
</html>
