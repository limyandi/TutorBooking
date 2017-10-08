<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<!DOCTYPE html>

<page title="Index Page">
    <navnonauth/>
    <%
            String answer = request.getParameter("delete");
            if (answer != null) {
                String studentFilePath = application.getRealPath("WEB-INF/students.xml");
                String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");
                String bookingFilePath = application.getRealPath("WEB-INF/bookings.xml");
    %>    
        <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application">
            <jsp:setProperty name="userApp" property="studentFilePath" value="<%=studentFilePath%>"/>
            <jsp:setProperty name="userApp" property="tutorFilePath" value="<%=tutorFilePath%>"/>
            <jsp:setProperty name="userApp" property="bookingFilePath" value="<%=bookingFilePath%>"/>
        </jsp:useBean>
        <%
            if (session.getAttribute("user") instanceof Student) {
                    userApp.removeStudent((Student)session.getAttribute("user"));
                }
            else {
                    userApp.removeTutor((Tutor)session.getAttribute("user"));
                }
            }
            session.invalidate();
        %>
</page>
