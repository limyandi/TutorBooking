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
                <script type="text/javascript" src="./js/javascript.js"/> 
                <title>
                    <xsl:value-of select="@title"/>
                </title>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="pagetitle">
        <div class="heading"><xsl:apply-templates/></div>
    </xsl:template>
    
    
    <xsl:template match="link">
        <div align="center" class="wrapper"> 
            <p><xsl:value-of select="@label"/>
                <a href="{@to}">
                <xsl:apply-templates/>
                </a>
            </p>
        </div>
    </xsl:template>
    
    <xsl:template match="inputs">
        <div class="wrapper">
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
        </div>
    </xsl:template>
    
    <xsl:template match="input">
        <tr>
            <td class="label">
                <xsl:value-of select="@label"/>
            </td>
            <td>
                <input type="{@type}" name="{@name}" value="{.}" id="{@id}" style="{@style}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="select">
        <tr>
            <td class="label">
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
        <tr><td></td><td class="error"><xsl:apply-templates/></td></tr>
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
        <div class="wrapper">
        <table class="search">
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
        </div>
    </xsl:template>
    
    <xsl:template match="tutor">
        <tr>
            <td><a href="./createBooking.jsp?tutorEmail={email}"><xsl:value-of select="email"/></a></td>
            <td><xsl:value-of select="firstname"/></td>
            <td><xsl:value-of select="lastname"/></td>
            <td><xsl:value-of select="subject"/></td>
        </tr>
    </xsl:template>
    
    <xsl:template match="aboutus">
        <div class="wrapper">We are enthusiasts about web development, HTML, CSS, Javascript, PHP, JQuery, React, Angular, you name it!</div>
    </xsl:template>
</xsl:stylesheet>
