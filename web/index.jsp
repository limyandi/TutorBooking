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
        <% String filePath = application.getRealPath("WEB-INF/students.xml");%>
        <jsp:useBean id="StudentApp" class="source.StudentApp" scope="application">
            <jsp:setProperty name="StudentApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            String answer = request.getParameter("delete");
            if (answer != null) {
                Students students = StudentApp.getStudents();
                Student student = (Student) session.getAttribute("user");
                students.removeStudent(student);
                StudentApp.updateStudents(students, filePath);
            }
            session.invalidate();
        %>
        <h1 align="center">Welcome to the UTS Tutor System</h1>
        <p align="center"> <u><a href="register.jsp">Register</a></u> | <u><a href="login.jsp">LoginADS</a></u></p>
    </body>
</html>
