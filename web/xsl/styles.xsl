<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : styles.xsl
    Created on : October 5, 2017, 7:22 PM
    Author     : limyandivicotrico
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="page">
        <html>
            <head>
                <link rel="stylesheet"   
                      href="./css/style.css" type="text/css"/>
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
                    function userType(type) {
                    var specialty = document.getElementById('specialty');
                    var specialtylabel = document.getElementById('specialtylabel');
                    if(type === 'tutor') {
                    specialty.style.display='block';
                    specialtylabel.style.display='block';
                    }
                    else {
                    specialty.style.display='none';
                    specialtylabel.style.display='none';
                    }
                    }
                </script>
                <title>
                    <xsl:value-of select="@title"/>
                </title>
            </head>
            <body>
                <h1>
                    <xsl:value-of select="@title"/>
                </h1>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="link">
        <p align="center"> 
            <a href="{@to}">
                <xsl:apply-templates/>
            </a> 
        </p>
    </xsl:template>
    
    <xsl:template match="inputs">
        <form action="{@action}" method="POST">
            <table>
                <xsl:apply-templates/>
                <tr>
                    <td>
                        <input type="submit" value="{@value}"/>
                    </td>
                </tr>
            </table>
        </form>
    </xsl:template>
    
    <xsl:template match="input">
        <tr>
            <td>
                <xsl:value-of select="@label"/>
            </td>
            <td>
                <input type="{@type}" name="{@name}" value="{.}" id="{@id}" style="{@style}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="select">
        <tr>
            <td>
                <xsl:value-of select="@label"/>
            </td>
            <td>
                <select onchange="{@onchange}" name="{@name}" id="{@id}" style="{@style}">
                    <xsl:apply-templates/>
                </select>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="option">
        <option value="{@value}">
            <xsl:apply-templates/>
        </option>
    </xsl:template>
    
    <!-- TODO: MAKE A CHANGE ON IT (NOT REUSABLE) -->
    <xsl:template match="delete">
        <form method="post" action="index.jsp" onsubmit="return confirm('do you really want to delete your account?')">
            <input type="submit" value="Delete account" name="delete"/>
        </form>
    </xsl:template>
    
    <xsl:template match="error">
        <tr>
            <td></td>
            <td>
                <xsl:apply-templates/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="para">
        <tr>
            <td>
                <p id="{@id}" style="{@style}">
                    <xsl:apply-templates/>
                </p>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="navigation">
        <header>
            <div class="headname">
                <img src="./css/logo.png"/>
                <h1>UTS Tutor</h1>
            </div>
            <div class="nav">
                <ul>
                    <li>
                        <a href="main.jsp">Home</a>
                    </li>
                    <li>
                        <a href="account.jsp">My Account</a>
                    </li>
                    <li>
                        <a href="booking.jsp">Booking</a>
                    </li>
                    <li>
                        <a href="index.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </header>
    </xsl:template>
    
    <xsl:template match="navnonauth">
        <header>
            <div class="headname">
                <img src="./css/logo.png"/>
                <h1>UTS Tutor</h1>
            </div>
            <div class="nav">
                <ul>
                    <li>
                        <a href="login.jsp">Login</a>
                    </li>
                    <li>
                        <a href="register.jsp">Register</a>
                    </li>
                </ul>
            </div>
        </header>
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
            <td><a href="createBooking.jsp?tutorEmail={email}"><xsl:value-of select="email"/></a></td>
            <td><xsl:value-of select="firstname"/></td>
            <td><xsl:value-of select="lastname"/></td>
            <td><xsl:value-of select="subject"/></td>
        </tr>
    </xsl:template>
    
</xsl:stylesheet>
