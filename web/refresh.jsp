<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div id="refresh">
<html:form action="refreshPerform" target="_top">
	<html:hidden property="refId"/>
	<table><tr>
	<td>Refresh Rate (seconds)<td>
	<td><html:text property="refresh"/></td>
	<td><html:submit value="Refresh"/></td>
	</tr></table>
</html:form>
</div>
