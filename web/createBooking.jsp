<%@page import="source.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            String email = request.getParameter("tutorEmail");
            String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");
            String bookingFilePath = application.getRealPath("WEB-INF/bookings.xml");
        %>
        <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application">
            <jsp:setProperty name="userApp" property="tutorFilePath" value="<%=tutorFilePath%>"/>
            <jsp:setProperty name="userApp" property="bookingFilePath" value="<%=bookingFilePath%>"/>
        </jsp:useBean>
        <%
            Tutor tutor = userApp.getTutors().checkExistingEmail(email);
            Student student = (Student) session.getAttribute("user");
            if(tutor.getStatus().equals("available")) {
                userApp.addBooking(student, tutor);
        %>
            <p><%=tutor.getEmail()%></p>
            <p><%=tutor.getFirstName()%></p>
            <p><%=tutor.getLastName()%></p>
            <p><%=tutor.getSubject()%></p>
            <p><%=student.getEmail()%></p>
            <p>BOOK SUCCESSFULLY!</p>
            <p>Click here to go back to Your<a href="main.jsp"> Menu! </a></p>
        <% } else {
        %>
        <p> sorry, this tutor is currently booked by someone else! </p>
        <p>Click here to go back to Your<a href="main.jsp"> Menu! </a></p>
        
        <% } %>
    </body>
</html>