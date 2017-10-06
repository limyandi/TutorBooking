<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : bookings2.xsl
    Created on : October 6, 2017, 10:39 PM
    Author     : limyandivicotrico
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-bookings" exclude-result-prefixes="x">
    <xsl:output method="html"/>

    <xsl:template match="x:bookings">
        <html>
            <head>
                <title>Booking XML</title>
                <link rel="stylesheet"   
                 href="./bootstrap.css" type="text/css"/>
            </head>
            <body>
                <h1 align="Center">Booking XML</h1>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="x:booking">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Tutor Email</th>
                <th>Tutor First Name</th>
                <th>Tutor Last Name</th>
                <th>Subject Name</th>
                <th>Student Email</th>
                <th>Student First Name</th>
                <th>Student Last Name</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
                <xsl:apply-templates/>
            </tbody>
        </table>
    </xsl:template>
    
    <xsl:template match="x:tutoremail|x:tutorfirstname|x:tutorlastname|x:subjectname|x:studentemail|x:studentfirstname|studentlastname|status">
        <td><xsl:apply-templates/></td>
    </xsl:template>
    

</xsl:stylesheet>


</xsl:stylesheet>
