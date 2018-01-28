<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <table class="currencies">
            <thead>
                <tr>
                    <th>Валюта</th>
                    <th>Курс</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="ValCurs/Valute[NumCode = 840 or NumCode = 978 or NumCode = 356]">
                    <tr>
                        <td>
                            <xsl:value-of select="Name"/>
                        </td>
                        <td>
                            <xsl:value-of select="Value"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </xsl:template>
</xsl:stylesheet>