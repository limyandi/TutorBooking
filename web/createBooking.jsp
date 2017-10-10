<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<!DOCTYPE html>
<page title="Create Booking">
    <navigation/>
    <details>
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
        <tutoremail><%=tutor.getEmail()%></tutoremail>
        <tutorsubject><%=tutor.getSubject()%></tutorsubject>
        <tutorfirstname><%=tutor.getFirstName()%></tutorfirstname>
        <tutorlastname><%=tutor.getLastName()%></tutorlastname>
        <studentemail><%=student.getEmail()%></studentemail> 
    </details>
    <success>Your booking has been added to your bookings list!</success>
    <link to="main.jsp">Back to Main Menu</link>
    <link to="booking.jsp">Check your Booking</link>
</page>