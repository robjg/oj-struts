<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<bean:define id="exception" name="detailForm" property="exception"/>

<table class="state">
		<colgroup>
			<col id="name"/>
			<col id="value"/>
		</colgroup>	<tr>
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

