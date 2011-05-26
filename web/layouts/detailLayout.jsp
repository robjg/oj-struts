<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<table border="0"  cellspacing="0" cellpadding="0" id="detail">
<tr>
<td>
	<tiles:insert page="/refreshPopulate.do" />
</td>
</tr>
<tr>
<td>
	<tiles:insert attribute="detail.commands"/>
</td>
</tr>
<tr>
<td>
	<tiles:insert attribute="detail.tabs"/>
</td>
</tr>
</table>

