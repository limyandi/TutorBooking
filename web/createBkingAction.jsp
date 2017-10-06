<%-- 
    Document   : createBkingAction
    Created on : 05/10/2017, 7:26:25 PM
    Author     : Carl
--%>

<%@page import="source.Student"%>
<%@page import="source.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String email = request.getParameter("email");
            String filePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="TutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="TutorApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            Tutors tutors = TutorApp.getTutors();
            Tutor tutor = tutors.checkExistingEmail(email);
            Student student = (Student)session.getAttribute(Student);
        %>
            
    </body>
</html>
