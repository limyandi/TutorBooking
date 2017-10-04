<%@page contentType="text/xml" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/main.xsl"?>
<%@page import="source.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<page title="Main Page">
    <% if (session.getAttribute("user") instanceof Student) { %>
    <search>
    	<subject></subject>
    	<tutorname></tutorname>
    	<status></status>
    </search>
    <% } %>
    <% if (request.getParameter("status") != null) {
    %>
    <c:import url="./WEB-INF/tutors.xml"
              var="inputDoc" />

    <c:import url="./WEB-INF/tutors.xsl"
              var="stylesheet" />
    
    <x:transform xml  = "${inputDoc}" xslt = "${stylesheet}">
    </x:transform>
    <% } %>
    <navigation/>
</page>
