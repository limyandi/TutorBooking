<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="page">
        <html>
            <head>
                <title>
                    <xsl:value-of select="@title"/>
                </title>
                <script type="text/javascript">
                    function userChoice(type) {
                    var subject = document.getElementById('subject');
                    var tutorname = document.getElementById('name');
                    var status = document.getElementById('status');
                    if (type === 'subject') {
                    subject.style.display = 'block';
                    tutorname.style.display = 'none';
                    status.style.display = 'none';
                    } else if (type === 'name') {
                    tutorname.style.display = 'block';
                    subject.style.display = 'none';
                    status.style.display = 'none';
                    } else {
                    status.style.display = 'block';
                    tutorname.style.display = 'none';
                    subject.style.display = 'none';
                    }
                    }
                </script>
            </head>
            <body>
                <h1>
                    <xsl:value-of select="@title"/>
                </h1>
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
        <form action="main.jsp" method="POST">
            <table>
                <tr>
                    <td>Search:</td>
                    <td>
                        <select onchange='userChoice(this.value)' name="choice">
                            <option value="subject">Subject</option>
                            <option value="name">Name</option>
                            <option value="status">Status</option>
                        </select> 
                    </td>
                    <td>
                        <xsl:apply-templates/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Search"/>
                    </td>
                </tr>
            </table>
        </form>
    </xsl:template>
    
    <xsl:template match="subject">
        <select name="subject" id="subject" style='display:block;'>
            <option value='WSD'>Web Services Development</option>
            <option value='USP'>Unix Systems Programming</option>
            <option value='SEP'>Software Engineering Practice</option>
            <option value='AppProg'>Application Programming</option>
            <option value='MobileApp'>Mobile Applications Development</option>
        </select>
    </xsl:template>
    
    <xsl:template match="tutorname">
        <input type="text" id="name" name="name" style='display:none;'/>
    </xsl:template>
    
    <xsl:template match="status">
        <select name="status" id="status" style='display:none;'>
            <option value="available">Available</option>
            <option value="unavailable">Unavailable</option>
        </select>
    </xsl:template>
    
    <xsl:template match="tutors">
        <table>
            <thead>
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Subject</th>
                </tr>
            </thead>
            <tbody>
                <xsl:apply-templates/>
            </tbody>
        </table>
    </xsl:template>
    
    <xsl:template match="tutor">
        <tr>
            <td><xsl:value-of select="email"/></td>
            <td><xsl:value-of select="firstname"/></td>
            <td><xsl:value-of select="lastname"/></td>
            <td><xsl:value-of select="subject"/></td>
        </tr>
    </xsl:template>
    
</xsl:stylesheet>
