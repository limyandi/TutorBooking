<%-- 
    Document   : index
    Created on : Sep 13, 2017, 1:35:44 PM
    Author     : limyandivicotrico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="source.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>   
        <%
            String answer = request.getParameter("delete");
            if (answer != null) {
                String filePath;
                if (session.getAttribute("user") instanceof Student) {
                    filePath = application.getRealPath("WEB-INF/students.xml");
        %>
        <jsp:useBean id="StudentApp" class="source.StudentApp" scope="application">
            <jsp:setProperty name="StudentApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
                    Students students = StudentApp.getStudents();
                    students.removeStudent((Student) session.getAttribute("user"));
                    StudentApp.updateStudents(students, filePath);
                }
                else {
                    filePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="TutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="TutorApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
                    Tutors tutors = TutorApp.getTutors();
                    tutors.removeTutor((Tutor) session.getAttribute("user"));
                    TutorApp.updateTutors(tutors, filePath);
                }
            }
            session.invalidate();
        %>
        <h1 align="center">Welcome to the UTS Tutor System</h1>
        <p align="center"> <u><a href="register.jsp">Register</a></u> | <u><a href="login.jsp">Login</a></u></p>
    </body>
</html>
