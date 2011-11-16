<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<bean:define id="jobActionsForm" name="jobActionsForm" 
	type="org.oddjob.webapp.struts.forms.JobActionsForm"/>

<html:form action="jobActionsPerform" target="_top">	
<html:hidden property="refId"/>
<fieldset>
<legend>Job Actions</legend>
		<logic:iterate name="jobActionsForm" id="command" 
			property="commands">
			<% 
			if (jobActionsForm.isEnabled((String) command)) {
			%>
				<html:submit property="performCommand" 
					value="<%=command.toString()%>"/>
			<% } else { %>
				<html:button property="irrelivant-because-disabled" 
					disabled="true" value="<%=command.toString()%>"/>
			<% } %>
		</logic:iterate>
</fieldset>
</html:form>
