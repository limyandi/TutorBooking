<%-- 
    Document   : main
    Created on : Sep 2, 2017, 5:01:11 PM
    Author     : limyandivicotrico
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="source.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <h1>Main Page</h1>
        <!-- Handle if user is tutor or student 
        (if user is tutor, 
        do not display search form) -->
        <% if (session.getAttribute("user") instanceof Student) {
        %>
        <form action="main.jsp" method="POST">
            <table>
                <tr><td><input type="text" name="category"/></td>
                    <td><select name="catval">
                            <option value="subject">Subject</option>
                            <option value="tutorname">Tutor Name</option>
                            <option value="tutorstatus">Tutor Status</option>
                        </select></td>
                </tr>
                <tr><td><input type="submit" value="Enter"/></td></tr>
            </table>
        </form>

        <%
            String text = request.getParameter("category");
            String option = request.getParameter("catval");
            String filePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="tutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="tutorApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>

        <% }%>

        <!-- 
        TODO: USE XSLT TO DISPLAY TUTOR.
        Display result here, 
        check the value of 
        'category' and 'catval'
        -->


        <a href='booking.jsp'>Booking</a>
        <a href='account.jsp'>Account</a>
        <a href='index.jsp'>Logout</a>
    </body>
</html>
