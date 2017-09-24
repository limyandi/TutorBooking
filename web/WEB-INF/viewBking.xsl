<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : viewBking.xsl
    Created on : 19 September 2017, 10:31 PM
    Author     : Carl
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-bookings" exclude-result-prefixes="x">
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="x:bookings">
        <html>
            <body>
                <h2>View a booking</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Tutor</th>   
                        <th>Subject</th>   
                    </tr>
                    <xsl:apply-templates/>
                </table>
                <table>
                    <tr>
                        <th>
                            <form action="booking.jsp">
                                <input type="submit" value="Back" />
                            </form>
                        </th>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="x:booking">
        <tr>
            <td>
                <xsl:value-of select="x:id"/>
            </td>
            <td>
                <xsl:value-of select="x:tutorfirstname"/>
            </td>
            <td>
                <xsl:value-of select="x:subjectname"/>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
