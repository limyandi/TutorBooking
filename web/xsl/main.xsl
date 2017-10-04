<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="page">
        <html>
            <head>
                <title><xsl:value-of select="@title"/></title>
            </head>
            <body>
                <h1><xsl:value-of select="@title"/></h1>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="navigation">
        <a href="booking.jsp">Booking</a>
        <a href="account.jsp">Account</a>
        <a href="index.jsp">Logout</a>
    </xsl:template>
    
    <xsl:template match="search">
        <table>
            <form action="main.jsp" method="POST">
                <table>
                    <tr>
                        <xsl:apply-templates/>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Search"/></td>
                    </tr>
                </table>
            </form>
        </table>
    </xsl:template>
    
    <xsl:template match="subject">
        <tr>
            <td>Search By Subject:</td>
            <td>
                <select name="subject">
                    <option value="WSD">Web Services Development</option>
                    <option value="USP">Unix Systems Programming</option>
                    <option value="SEP">Software Engineering Practice</option>
                    <option value="AppProg">Application Programming</option>
                    <option value="MobileApp">Mobile Applications Development</option>
                </select>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="tutorname">
        <tr>
            <td>Search by Tutor Name:</td>
            <td>
                <input type="tutorname">Tutor Name</input>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="status">
        <tr>
            <td>Search by Status:</td>
            <td>
                <select name="status">
                    <option value="available">Available</option>
                    <option value="unavailable">Unavailable</option>
                </select>
            </td>
        </tr>
    </xsl:template>
    
</xsl:stylesheet>
