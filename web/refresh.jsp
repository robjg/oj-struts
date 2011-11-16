<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="refreshPerform" target="_top">
	<html:hidden property="refId"/>
	<fieldset id="refresh-set">
		<legend>Refresh</legend>
				<label for="refresh">Rate (seconds)</label> 
	<html:text property="refresh" styleId="refresh"/>
	</fieldset>
	
	<html:submit value="Refresh" styleId="refresh-submit"/>
	
</html:form>
