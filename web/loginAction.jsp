<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%-- 
    Document   : loginAction
    Created on : Sep 13, 2017, 1:43:57 PM
    Author     : limyandivicotrico
--%>
<page title="Process Login Page">
    <navnonauth/>
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
                String studentFilePath = application.getRealPath("WEB-INF/students.xml");
                String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");
                String bookingFilePath = application.getRealPath("WEB-INF/bookings.xml");
        %>
        <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application">
            <jsp:setProperty name="userApp" property="studentFilePath" value="<%=studentFilePath%>"/>
            <jsp:setProperty name="userApp" property="tutorFilePath" value="<%=tutorFilePath%>"/>
        </jsp:useBean>
        <%
                Students students = userApp.getStudents();
                Student student = students.login(email, password);
                if (student != null) {
                    session.setAttribute("user", student);
                    response.sendRedirect("main.jsp");
                } else {
                    Tutors tutors = userApp.getTutors();
                    Tutor tutor = tutors.login(email, password);
                    if (tutor != null) {
                    session.setAttribute("user", tutor);
                    response.sendRedirect("main.jsp");
                    } else {
        %>
        <link to="login.jsp" label="Password incorrect. Click to ">Login again</link>
        <%          }
                }
            }
        %>
</page>