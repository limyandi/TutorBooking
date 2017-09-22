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
            if (userType.equals("student")) {
                filePath = application.getRealPath("WEB-INF/students.xml");
                schemaPath = application.getRealPath("WEB-INF/students.xsd");
        %>
        <jsp:useBean id="studentApp" class="source.StudentApp" scope="application">
            <jsp:setProperty name="studentApp" property="filePath" value="<%=filePath%>"/>
            <jsp:setProperty name="studentApp" property="schemaPath" value="<%=schemaPath%>"/>
        </jsp:useBean>
        <%
            Students students = studentApp.getStudents();
            Student student = students.checkExistingEmail(email);

            if (student == null) {
                Student newStudent = new Student(fname, lname, email, password, dob, userType);
                session.setAttribute("student", newStudent);
                students.addStudent(student);
                studentApp.updateStudents(students, filePath, schemaPath);
        %> <p>Successful</p>
        <%
        } else {

        %>
        <p>User with that email already exists, Click <a href="register.html"> here </a> to register again.</p>
        <%            }
        } else {
            String specialty = request.getParameter("specialty");
            filePath = application.getRealPath("WEB-INF/tutors.xml");
            schemaPath = application.getRealPath("WEB-INF/tutors.xsd");
        %>
        <jsp:useBean id="tutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="tutorApp" property="filePath" value="<%=filePath%>"/>
            <jsp:setProperty name="tutorApp" property="schemaPath" value="<%=schemaPath%>"/>
        </jsp:useBean>
        <%
            Tutors tutors = tutorApp.getTutors();
            Tutor tutor = tutors.checkExistingEmail(email);

            if (tutor == null) {
                Tutor newTutor = new Tutor(fname, lname, email, password, dob, userType, specialty, "available");
                session.setAttribute("student", newTutor);
                tutors.addTutor(tutor);
                tutorApp.updateTutors(tutors, filePath, schemaPath);
        %>
        <p>Successful</p>
        <%
        } else {
        %>
        <p>User with that email already exists, Click <a href="register.html"> here </a> to register again.</p>
        <%
                }
            }
        %>

    </body>
</html>
