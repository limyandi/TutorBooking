<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%@page import="uts.wsd.registerlogin.Login"%>
<%
    Login login = new Login();
    if (session.getAttribute("loginData") != null) {
        login = (Login) session.getAttribute("loginData");
    }
%>
<page title="Login Page">
    <navnonauth/>
    <inputs action="loginAction.jsp" value="Login">
        <input type="text" label="Email" name="email"><%=login.getEmail()%></input>
        <error><%=login.getErrorMessage("email")%></error>
        <input type="password" label="Password" name="password"><%=login.getPassword()%></input>
        <error><%=login.getErrorMessage("password")%></error>
    </inputs>
</page>