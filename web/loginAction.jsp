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
        %>
        <jsp:useBean id="loginHandler" class="uts.wsd.registerlogin.Login" scope="request">
            <jsp:setProperty name="loginHandler" property="email" value="<%=email%>"/>
            <jsp:setProperty name="loginHandler" property="password" value="<%=password%>"/>
        </jsp:useBean>
        <%
            if (!loginHandler.validateLogin()) {
                session.setAttribute("loginData", loginHandler);
                response.sendRedirect("login.jsp");
            } else {
                Students students = StudentApp.getStudents();
                Student student = students.login(email, password);
                if (student != null) {
                    session.setAttribute("user", student);
                    response.sendRedirect("main.jsp");
                } else {
                    filePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="TutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="TutorApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            Tutors tutors = TutorApp.getTutors();
            Tutor tutor = tutors.login(email, password);
            if (tutor != null) {
                session.setAttribute("user", tutor);
                response.sendRedirect("main.jsp");
            } else {

        %>
        <p>Password incorrect. Click <a href="login.jsp"> here </a> to try again.</p>   
        <%          }
                }
            }
        %>
    </body>
</html>