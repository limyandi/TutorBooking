<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<page title="Booking History">
    <navigation/>
    <pagetitle>Your Bookings History</pagetitle>
    <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application"/>
    <%  String bookingFilePath = application.getRealPath("WEB-INF/bookings.xml");
        userApp.setBookingFilePath(bookingFilePath);
        ArrayList<Booking> lists;
        if (session.getAttribute("user") instanceof Student) {
            Student student = (Student) session.getAttribute("user");
            lists = userApp.getStudentBookingsHistory(student);
        }
        else {
            Tutor tutor = (Tutor) session.getAttribute("user");
            lists = userApp.getTutorBookingsHistory(tutor);
        }
    %>
    <% if(lists != null) { %>
    <bookings>
        <% for (Booking booking: lists) { %>
        <booking>
            <id><%=booking.getId()%></id>
            <subject><%=booking.getSubjectName()%></subject>
            <tutoremail><%=booking.getTutorEmail()%></tutoremail>
            <tutorname><%=booking.getTutorFirstName()%> <%=booking.getTutorLastName()%></tutorname>
            <studentemail><%=booking.getStudentEmail()%></studentemail>
            <studentname><%=booking.getStudentFirstName()%> <%=booking.getStudentLastName()%></studentname>
            <status><%=booking.getStatus()%></status>
        </booking>
        <% } %>
    </bookings>
    <%  }
    %>
    <link to="booking.jsp">Back to your active booking</link>
</page>