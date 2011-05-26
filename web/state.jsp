<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<bean:define id="exception" name="detailForm" property="exception"/>

<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>State</td>
		<td><bean:write name="detailForm" property="jobState"/></td>
	</tr>
	<tr>
		<td>Time</td>
		<td><bean:write name="detailForm" property="time"/></td>
	</tr>
	<tr>
		<td>Exception</td>
		<td><%= "".equals(exception) ? "&nbsp;" : exception %></td>
	</tr>
</table>

