<%-- 
    Document   : booking
    Created on : 19/09/2017, 10:27:40 PM
    Author     : Carl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<c:import var="xml" url="/WEB-INF/bookings.xml" />
<c:import var="xslt" url="/WEB-INF/bookings.xsl" />
<x:transform xml="${xml}" xslt="${xslt}" />
