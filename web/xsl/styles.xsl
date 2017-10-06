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
                <title><xsl:value-of select="@title"/></title>
            </head>
            <body>
                <h1><xsl:value-of select="@title"/></h1>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="link">
        <p align="center"> 
            <a href="{@to}"><xsl:apply-templates/></a> 
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
            <td><xsl:value-of select="@label"/></td>
            <td><input type="{@type}" name="{@name}" value="{.}"/></td>
        </tr>
    </xsl:template>
    
    <xsl:template match="select">
        <tr>
            <td><xsl:value-of select="@label"/></td>
            <td>
                <select name="{@name}">
                    <xsl:apply-templates/>
                </select>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="option">
        <option value="{@value}"><xsl:value-of select="."/></option>
    </xsl:template>
    
    <!-- TODO: MAKE A CHANGE ON IT (NOT REUSABLE) -->
    <xsl:template match="delete">
        <form method="post" action="index.jsp" onsubmit="return confirm('do you really want to delete your account?')">
            <input type="submit" value="Delete account" name="delete"/>
        </form>
    </xsl:template>
    
    <xsl:template match="error">
        <tr>
            <td></td><td><xsl:apply-templates/></td>
        </tr>
    </xsl:template>
    
    

</xsl:stylesheet>
