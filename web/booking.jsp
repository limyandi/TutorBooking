<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<page title="Booking Page">
    <navigation/>
    <pagetitle>Active Booking</pagetitle>
    <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application"/>
    <%  String bookingFilePath = application.getRealPath("WEB-INF/bookings.xml");
        userApp.setBookingFilePath(bookingFilePath);
        ArrayList<Booking> lists;
        if (session.getAttribute("user") instanceof Student) {
            Student student = (Student) session.getAttribute("user");
            lists = userApp.getStudentActiveBooking(student);
        }
        else {
            Tutor tutor = (Tutor) session.getAttribute("user");
            lists = userApp.getTutorActiveBooking(tutor);
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
            <% if (session.getAttribute("user") instanceof Tutor ) { %>
            <complete/>
            <% }%>
        </booking>
        <% } %>
    </bookings>
    <%  }
    %>
    <link to="bookingHistory.jsp">View your Full Bookings History</link>
</page>