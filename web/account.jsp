<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%@page import="source.*"%>
<!DOCTYPE html>

<%
    String studentFilePath = application.getRealPath("WEB-INF/students.xml");
    String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml"); 
%>
<jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application">
            <jsp:setProperty name="userApp" property="studentFilePath" value="<%=studentFilePath%>"/>
            <jsp:setProperty name="userApp" property="tutorFilePath" value="<%=tutorFilePath%>"/>
</jsp:useBean>
<%
    User user;
    if (session.getAttribute("user") instanceof Student) {
        user = (Student) session.getAttribute("user");
    } else {
        user = (Tutor) session.getAttribute("user");
    }
    String fname = request.getParameter("Fname");
    String lname = request.getParameter("Lname");
    String password = request.getParameter("password");
    String dob = request.getParameter("dob");

    if (fname != null && lname != null
            && password != null && dob != null) {
        userApp.updateDetails(user, fname, lname, password, dob);
    }
%>
<page title="Profile Page">
    <navigation/>
    <inputs action="account.jsp" value="Update">
        <input type="text" label="First Name" name="Fname"><%= user.getFirstName()%></input>  
        <input type="text" label="Last Name" name="Lname"><%= user.getLastName()%></input>
        <input type="password" label="Password" name="password"><%= user.getPassword()%></input>
        <input type="date" label="Date of Birth" name="dob"><%= user.getDob()%></input>
    </inputs>
    <delete/>
</page>

