<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="page">
        <html>
            <head>
                <title>
                    <xsl:value-of select="@title"/>
                </title>
            </head>
            <body>
                <h2>
                    <xsl:value-of select="@title"/>
                </h2>
                <form action="loginAction.jsp" method="POST">
                    <table>
                        <xsl:apply-templates/>
                        <tr>
                            <td><input type="submit" value="Login"/></td>
                        </tr>
                    </table>
                </form>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="inputs">
        <tr>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>
    
    <xsl:template match="email">
            <td>Email:</td>
            <td>
                <input type="email" name="email" value="{.}"/>
            </td>
    </xsl:template>
    
    <xsl:template match="emailerror">
            <td>
                <xsl:value-of select="."/>
            </td>
    </xsl:template>
    
    <xsl:template match="password">
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" name="password" value="{.}"/>
            </td>
        </tr>
    </xsl:template>
    
    <xsl:template match="passworderror">
            <td>
                <xsl:value-of select="."/>
            </td>
    </xsl:template>
</xsl:stylesheet>