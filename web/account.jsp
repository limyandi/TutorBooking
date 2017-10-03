<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/account.xsl"?>
<%@page import="source.*"%>
<!DOCTYPE html>

<%
    User user;
    if (session.getAttribute("user") instanceof Student) {
        user = (Student) session.getAttribute("user");
    } else {
        user = (Tutor) session.getAttribute("user");
    }
    String fname = request.getParameter("Fname");
    String lname = request.getParameter("Lname");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String dob = request.getParameter("dob");

    if (email != null && fname != null && lname != null
            && password != null && dob != null) {
        //TODO: MARSHAL. (Change the current session of user data and then marshal it.)
        user.updateDetails(fname, lname, email, password, dob);
        if (session.getAttribute("user") instanceof Tutor) {
            String specialty = request.getParameter("specialty");
            if (specialty != null) {
                user.setSubject(specialty);
            }
        }
    }
%>
<page title="Profile for <%= user.getFirstName()%> <%= user.getLastName()%>">
    <inputs>
        <firstname><%= user.getFirstName()%></firstname>
        <lastname><%= user.getLastName()%></lastname>
        <email><%= user.getEmail()%></email>
        <password><%= user.getPassword()%></password>
        <dob><%= user.getDob()%></dob>
        <% if (session.getAttribute("user") instanceof Tutor) {%>
        <specialty><%= user.getSubject()%></specialty>
        <% } %>
    </inputs>
    <delete/>
</page>

