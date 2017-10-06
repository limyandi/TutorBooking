<?xml version="1.0" encoding="UTF-8"?>
    <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-bookings" exclude-result-prefixes="x">
    <xsl:output method="html"/>
    <xsl:template match="x:bookings">
        <html>
            <head>
                <title>Booking XML</title>
                <link rel="stylesheet"   
                 href="../css/bootstrap.css" type="text/css"/>
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
                <td><xsl:value-of select="@id"/></td>
                <xsl:apply-templates/>
            </tbody>
        </table>
    </xsl:template>
    
    <xsl:template match="x:tutoremail|x:tutorfirstname|x:tutorlastname|x:subjectname|x:studentemail|x:studentfirstname|x:studentlastname|x:status">
        <td><xsl:apply-templates/></td>
    </xsl:template>
   
</xsl:stylesheet>
