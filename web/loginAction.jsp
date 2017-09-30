<%-- 
    Document   : loginAction
    Created on : Sep 13, 2017, 1:43:57 PM
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Students users = StudentApp.getStudents();
            if (users != null) {
                Student user = users.login(email, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    response.sendRedirect("main.jsp");
                }
            } else {
                filePath = application.getRealPath("WEB-INF/tutors.xml");
        %>

        <jsp:useBean id="TutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="TutorApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            Tutors tutors = TutorApp.getTutors();
            if (tutors != null) {
                Tutor user = tutors.login(email, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    response.sendRedirect("main.jsp");
                } 
             } else { %>
        <p>Password incorrect. Click <a href="login.html"> here </a> to try again.</p>
        <% }
        }%>

    </body>
</html>

    </body>
</html>
