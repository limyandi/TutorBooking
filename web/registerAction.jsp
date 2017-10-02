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
            String filePath;
            String schemaPath;
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
            if (userType.equals("student")) {
                filePath = application.getRealPath("WEB-INF/students.xml");
        %>
        <jsp:useBean id="studentApp" class="source.StudentApp" scope="application">
            <jsp:setProperty name="studentApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            Students students = studentApp.getStudents();
            Student student = students.checkExistingEmail(email);

            if (student == null) {
                Student user = new Student(fname, lname, email, password, dob, userType);
                session.setAttribute("user", user);
                students.addStudent(user);
                studentApp.updateStudents(students, filePath);
                response.sendRedirect("main.jsp");
            } else {
        %>
        <p>User with that email already exists, Click <a href="register.jsp"> here </a> to register again.</p>
        <%            }
            } else {
            String specialty = request.getParameter("specialty");
            filePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="tutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="tutorApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            Tutors tutors = tutorApp.getTutors();
            Tutor tutor = tutors.checkExistingEmail(email);

            if (tutor == null) {
                Tutor user = new Tutor(fname, lname, email, password, dob, userType, specialty, "available");
                session.setAttribute("user", user);
                tutors.addTutor(user);
                tutorApp.updateTutors(tutors, filePath);
                response.sendRedirect("main.jsp");
            } else {
        %>
        <p>User with that email already exists, Click <a href="register.jsp"> here </a> to register again.</p>
        <%
                }
             }
            }
        %>
    </body>
</html>
