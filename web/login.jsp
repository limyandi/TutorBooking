<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8" import="source.Login"%>
<html>
    <head>
        <title>Login Page</title>
    </head>
        <h2>Login Page</h2>
        <% 
            Login login = new Login();
            if(session.getAttribute("loginData") != null) {
                login = (Login) session.getAttribute("loginData");
            }
        %>
        <form method="post" action="loginAction.jsp">
            <table>
                <tr>
                    <td>Email/Username:</td><td><input type="email" name="email" value="<%=login.getEmail()%>"></td>
                    <td><%=login.getEmailError()%></td>
                </tr>
                <tr>
                    <td>Password:</td><td><input type="password" name="password" value="<%=login.getPassword()%>"></td>
                    <td><%=login.getPasswordError()%></td>
                </tr>
                <tr><td></td><td><input type="submit" value="Login"></td></tr>
            </table>            
        </form>
        <p>Click here to <u><a href="register.html">Register</a></u>.</p>
    </body>
</html>
