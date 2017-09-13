<%-- 
    Document   : index
    Created on : Sep 13, 2017, 1:35:44 PM
    Author     : limyandivicotrico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% session.invalidate(); %>
        <h1 align="center">Welcome to the UTS Tutor System</h1>
        <p align="center"> <u><a href="register.html">Register</a></u> | <u><a href="login.html">Login</a></u></p>
    </body>
</html>
