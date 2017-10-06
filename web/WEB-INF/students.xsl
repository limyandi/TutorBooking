<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-students" exclude-result-prefixes="x">
    <xsl:output method="html"/>

    <xsl:template match="x:students">
        <html>
            <head>
                <link rel="stylesheet"   
                 href="../css/bootstrap.css" type="text/css"/>
                <style>
                    
                </style>
            </head>
            <body>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th align="center">Student First Name</th>
                            <th align="center">Student Last Name</th>
                            <th align="center">Email</th>
                            <th align="center">Date Of Birth</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates/>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="x:student">
        <tr>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>
    
    <xsl:template match="x:firstname|x:lastname|x:email|x:dateofbirth">
        <td>
            <div align="center">
                <xsl:apply-templates/>
            </div>
        </td>
    </xsl:template>
    
    <xsl:template match="x:usertype|x:password">
    </xsl:template>

</xsl:stylesheet>
