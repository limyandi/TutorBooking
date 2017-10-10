<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%@page import="java.util.ArrayList"%>
<%@page import="source.*"%>
<!DOCTYPE html>
<page title="Main Page">
    <navigation/>
    <pagetitle>Main Page</pagetitle>
    <% if (session.getAttribute("user") instanceof Student) { %>
    <inputs action="main.jsp" value="Search">
        <select label="Search" onchange="userChoice(this.value)" name="choice">
            <option value="subject">Subject</option>
            <option value="firstname">First Name</option>
            <option value="lastname">Last Name</option>
            <option value="status">Status</option>
        </select>
        <select name="subject" id="subject" style="display:block;">
            <option value='WSD'>Web Services Development</option>
            <option value='USP'>Unix Systems Programming</option>
            <option value='SEP'>Software Engineering Practice</option>
            <option value='AppProg'>Application Programming</option>
            <option value='MobileApp'>Mobile Applications Development</option>
        </select>
        <input type="text" id="firstname" name="firstname" style="display:none;"/>
        <input type="text" id="lastname" name="lastname" style="display:none;"/>
        <select name="status" id="status" style="display:none;">
            <option value="available">Available</option>
            <option value="unavailable">Unavailable</option>
        </select>
    </inputs>
    <% String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");%>
    <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application">
        <jsp:setProperty name="userApp" property="filePath" value="<%=tutorFilePath%>"/>
    </jsp:useBean>
    <% String choice = request.getParameter("choice");
        ArrayList<Tutor> lists = null;
        if (choice != null) {
            if (choice.equals("subject")) {
                String subject = request.getParameter("subject");
                lists = userApp.getTutorBySubject(subject);
            } else if (choice.equals("firstname")) {
                String name = request.getParameter("firstname");
                lists = userApp.getTutorByFirstName(name);
            } else if (choice.equals("lastname")) { 
                String lastName = request.getParameter("lastname");
                lists = userApp.getTutorByLastName(lastName);
            }
            else if (choice.equals("status")) {
                String status = request.getParameter("status");
                lists = userApp.getTutorByStatus(status);
            } 
        }
    %>

    <%  if (lists != null) {
    %>  
    <tutors>
        <% for (Tutor tutor : lists) {%>
        <tutor>
            <email><%=tutor.getEmail()%></email>
            <firstname><%=tutor.getFirstName()%></firstname>
            <lastname><%=tutor.getLastName()%></lastname>
            <subject><%=tutor.getSubject()%></subject>
            <status><%=tutor.getStatus()%></status>
        </tutor>
        <% } %>
    </tutors>
    <%}
    }%>
</page>
