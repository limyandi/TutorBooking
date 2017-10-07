<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
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
    <navigation/>
    <inputs action="account.jsp" value="Update">
        <input type="text" label="First Name" name="Fname"><%= user.getFirstName()%></input>
        <input type="text" label="Last Name" name="Lname"><%= user.getLastName()%></input>
        <input type="email" label="Email" name="email"><%= user.getEmail()%></input>
        <input type="password" label="Password" name="password"><%= user.getPassword()%></input>
        <input type="date" label="Date of Birth" name="dob"><%= user.getDob()%></input>
        <% if (session.getAttribute("user") instanceof Tutor) {%>
        <select name="specialty" label="Subject">
            <option value='WSD'>Web Services Development</option>
            <option value='USP'>Unix Systems Programming</option>
            <option value='SEP'>Software Engineering Practice</option>
            <option value='AppProg'>Application Programming</option>
            <option value='MobileApp'>Mobile Applications Development</option>
        </select>
        <% } %>
    </inputs>
    <delete/>
</page>

