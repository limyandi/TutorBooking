<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : bookings.xsl
    Created on : 19 September 2017, 10:30 PM
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
                <h2>Booking</h2>
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
                            <form action="createBking.jsp">
                                <input type="submit" value="Create a booking" />
                            </form>
                        </th>
                        <th>
                            <form action="viewBking.jsp">
                                <input type="submit" value="View a booking" />
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
                <xsl:value-of select="@id"/>
            </td>
            <td>
                <xsl:value-of select="x:tutorfirstname"/>
            </td>
            <td>
                <xsl:value-of select="x:subjectname"/>
            </td>
            <td>
                <form action="bookingAction.jsp">
                    <id></id>
                    <input type="submit" value="cancel"/>
                </form>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>

