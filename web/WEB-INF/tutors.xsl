<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-tutors" exclude-result-prefixes="x">
    <xsl:output method="html"/>

    <xsl:template match="x:tutors">
        <html>
            <head>
                <link rel="stylesheet"   
                 href="./bootstrap.css" type="text/css"/>
                <style>
                    
                </style>
            </head>
            <body>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Tutor First Name</th>
                            <th>Tutor Last Name</th>
                            <th>Email</th>
                            <th>Subject</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates/>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="x:tutor">
        <tr>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>
    
    <xsl:template match="x:firstname|x:lastname|x:email|x:subject|x:status">
        <td>
            <div align="center">
                <xsl:apply-templates/>
            </div>
        </td>
    </xsl:template>
    
    <xsl:template match="x:password|x:dateofbirth|x:usertype">
    </xsl:template>

</xsl:stylesheet>
