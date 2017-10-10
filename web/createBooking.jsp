<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<!DOCTYPE html>
<page title="Create Booking">
    <navigation/>
    <pagetitle>Tutor Details</pagetitle>
    <tutors>
        <% 
            String email = request.getParameter("tutorEmail");
            String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");
            String bookingFilePath = application.getRealPath("WEB-INF/bookings.xml");
        %>
        <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application">
            <jsp:setProperty name="userApp" property="bookingFilePath" value="<%=bookingFilePath%>"/>
            <jsp:setProperty name="userApp" property="tutorFilePath" value="<%=tutorFilePath%>"/>
        </jsp:useBean>
        <%
            userApp.setBookingFilePath(bookingFilePath);
            Tutor tutor = userApp.readTutor(email);
            Student student = (Student) session.getAttribute("user");
            userApp.addBooking(student, tutor);
        %>
        <tutor>
            <email><%=tutor.getEmail()%></email>
            <subject><%=tutor.getSubject()%></subject>
            <firstname><%=tutor.getFirstName()%></firstname>
            <lastname><%=tutor.getLastName()%></lastname>
            <status>Active</status>
        </tutor>
    </tutors>
    <success>Your booking has been added to your bookings list!</success>
    <link to="main.jsp">Back to Main Menu</link>
    <link to="booking.jsp">Check your Booking</link>
</page>