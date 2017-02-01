<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : affichProfil.xsl.xsl
    Created on : 11 octobre 2016, 10:46
    Author     : perrink
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:p="http://myGame/profile">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Profil de
                    <xsl:value-of select="p:profile/p:name"/>  
                </title>
                <link rel="stylesheet" href="../xslt/style.css"/>
            </head>
            <body>
                <xsl:apply-templates select="p:profile/p:avatar"/>
                <br/>
                Nom : <xsl:value-of select="p:profile/p:name"/>
                <br/>
                <i>Date de naissance : </i>
                <xsl:value-of select="p:profile/p:birthday"/>
                <br/>
                <table>
                    <xsl:apply-templates select="p:profile/p:games/p:game"/>
                </table>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="p:profile/p:avatar">
        <xsl:element name="img">
            <xsl:attribute name="src">
                <xsl:value-of select="."/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    
    <xsl:template match="p:profile/p:games/p:game">
        <tr>
            <td>
                <table id="tab">
                    <tr>
                        <th>Partie <xsl:value-of select="count(./preceding-sibling::*)+1"/> du <xsl:value-of select="@date"/>
                        </th>
                        <td>
                            Completion : <xsl:if test="@found">
                                <xsl:value-of select="@found" />
                            </xsl:if>
                            <xsl:if test="not(@found)">
                                <xsl:text>100%</xsl:text>
                            </xsl:if>
                        </td>
                        <td>
                            Temps de jeu : <xsl:value-of select="./p:time"/> minute(s)
                        </td>
                        <td>
                            Mot (niveau <xsl:value-of select="./p:word/@level"/>) : <xsl:value-of select="./p:word"/>  
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
