<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<page title="Booking Page">
    <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application"/>
    <% 
        String bookingFilePath = application.getRealPath("WEB-INF/bookings.xml");
        String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");
        userApp.setBookingFilePath(bookingFilePath);
        userApp.setTutorFilePath(tutorFilePath);
        
        String bookingId = request.getParameter("bookingId");
        String tutorEmail = request.getParameter("tutorEmail");
        // At this part, instead of getting the tutor email, i would have preferred to put in the current tutor in the session, but the XML is strangely not updated.
        Tutor tutor = userApp.readTutor(tutorEmail);
        userApp.completeBooking(Integer.parseInt(bookingId), tutor);
        response.sendRedirect("booking.jsp");
    %>
</page>