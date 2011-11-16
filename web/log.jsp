<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<ul class="textarea">
	
	<logic:iterate name="detailForm" id="element" property="logEvents">
	<li>
		<bean:write name="element" property="message"/>
	</li>
	</logic:iterate>
	
</ul>
	