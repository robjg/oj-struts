<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<logic:iterate name="detailForm" id="element" property="properties">
	<tr>
		<td>
		<bean:write name="element" property="key"/>
		</td>
		<% Object value = ((java.util.Map.Entry )element).getValue();
		if (value == null || "".equals(value)) {
			value = "&nbsp;";
		}
		%>
		<td><%= value %></td>
	</tr>
	</logic:iterate>
</table>
	
