<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/login.xsl"?>
<%@page import="uts.wsd.registerlogin.Login"%>
<%
    Login login = new Login();
    if (session.getAttribute("loginData") != null) {
        login = (Login) session.getAttribute("loginData");
    }
%>
<page title="Login Page">
    <inputs>
        <email><%=login.getEmail()%></email>
        <emailerror><%=login.getErrorMessage("email")%></emailerror>
        <password><%=login.getPassword()%></password>
        <passworderror><%=login.getErrorMessage("password")%></passworderror>
    </inputs>
</page>