<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/main.xsl"?>
<%@page import="java.util.ArrayList"%>
<%@page import="source.*"%>
<!DOCTYPE html>
<page title="Main Page">
    <% if (session.getAttribute("user") instanceof Student) { %>
    <search>
        <subject></subject>
        <tutorname></tutorname>
        <status></status>
    </search>
    <% String filePath = application.getRealPath("WEB-INF/tutors.xml");%>
    <jsp:useBean id="tutorApp" class="source.TutorApp" scope="application">
        <jsp:setProperty name="tutorApp" property="filePath" value="<%=filePath%>"/>
    </jsp:useBean>
    <% String choice = request.getParameter("choice");
        ArrayList<Tutor> lists = null;
        if (choice != null) {
            if (choice.equals("subject")) {
                String subject = request.getParameter("subject");
                lists = tutorApp.getTutors().getBySubject(subject);
            } else if (choice.equals("name")) {
                String name = request.getParameter("name");
                lists = tutorApp.getTutors().getByFirstName(name);
            } else if (choice.equals("status")) {
                String status = request.getParameter("status");
                lists = tutorApp.getTutors().getByStatus(status);
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
        </tutor>
        <% } %>
    </tutors>
    <%}
    }%>
    <navigation/>
</page>
