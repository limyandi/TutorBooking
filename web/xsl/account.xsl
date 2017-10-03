<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="page">
        <html>
            <head>
                <title>Account Page</title>
            </head>
            <body>
                <h1>
                    <xsl:value-of select="@title"/>
                </h1>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="delete">
        <form method="post" action="index.jsp" onsubmit="return confirm('do you really want to delete your account?')">
            <input type="submit" value="Delete account" name="delete"/>
        </form>
    </xsl:template>
    
    <xsl:template match="inputs">
        <form action="account.jsp" method="POST">
            <table>
                <xsl:apply-templates/>
                <tr>
                    <td>
                        <input type="submit" value="Update"/>
                    </td>
                </tr>
            </table>
        </form>
    </xsl:template>
    
    <xsl:template match="firstname">
        <tr>
            <td>First Name:</td>
            <td>
                <input type="text" name="Fname" value="{.}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="lastname">
        <tr>
            <td>Last Name:</td>
            <td>
                <input type="text" name="Lname" value="{.}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="email">
        <tr>
            <td>Email:</td>
            <td>
                <input type="email" name="email" value="{.}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="password">
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" name="password" value="{.}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="dob">
        <tr>
            <td>DOB:</td>
            <td>
                <input type="date" name="dob" value="{.}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="specialty">
        <tr>
            <td>Subject:</td>
            <td>
                <select name="specialty">
                    <option value='WSD'>Web Services Development</option>
                    <option value='USP'>Unix Systems Programming</option>
                    <option value='SEP'>Software Engineering Practice</option>
                    <option value='AppProg'>Application Programming</option>
                    <option value='MobileApp'>Mobile Applications Development</option>
                </select>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>

