<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : createBking.xsl
    Created on : 19 September 2017, 10:30 PM
    Author     : Carl
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-tutors" exclude-result-prefixes="x">
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="x:tutors">
        <html>
            <body>
                <h2>Create a booking</h2>
                <table>
                    <tr>
                        <th>Subject</th>   
                        <th>Tutor</th>
                        <th>Tutor Email</th>   
                    </tr>
                    <xsl:apply-templates/>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="x:tutor">
        <tr>
            <td>
                <xsl:value-of select="x:subject"/>
            </td>
            <td>
                <xsl:value-of select="x:firstname"/> <xsl:text> </xsl:text> <xsl:value-of select="x:lastname"/>
            </td>
            <td>
                <xsl:value-of select="x:email"/>
            </td>
            <td>
                <a href="createBkingAction.jsp?tutorEmail={email}">Book</a>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
