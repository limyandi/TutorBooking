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
        <jsp:useBean id="userApp" class="source.userApp" scope="application">
            <jsp:setProperty name="userApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Users users = userApp.getUsers();
            if (users != null) {
                User user = users.login(email, password);
                session.setAttribute("user", user);
        %>

        <p>Login successful. Click <a href="main.jsp"> here </a> to return to the main page.</p>
        <%  } else { %>
        <p>Password incorrect. Click <a href="login.html"> here </a> to try again.</p>
        <% }%>
    </body>
</html>
