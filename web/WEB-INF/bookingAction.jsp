<%-- 
    Document   : bookingAction
    Created on : 24/09/2017, 11:09:13 PM
    Author     : Carl
--%>

<%@page import="source.Booking"%>
<%@page import="source.Bookings"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String id = request.getParameter("id");
            String filePath = application.getRealPath("WEB-INF/bookings.xml");
        %>
        <jsp:useBean id="bookingApp" class="source.BookingApp" scope="application">
            <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <% Bookings bookings = bookingApp.getBookings();
            Booking booking = bookings.checkId(id);
            if (booking != null) {
                bookings.removeBooking(booking);
                bookingApp.updateBookings(bookings, filePath);
        %>

    </body>
</html>
