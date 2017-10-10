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
        Tutor tutor = userApp.readTutor(tutorEmail);
        if(session.getAttribute("user") instanceof Student) {
            userApp.studentCancelBooking(Integer.parseInt(bookingId), (Student) session.getAttribute("user"), tutor);
        }
        else {
            userApp.tutorCancelBooking(Integer.parseInt(bookingId), tutor);
        }
        response.sendRedirect("booking.jsp");
    %>
</page>