<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<div id="detail">


		<div class="control">

	<tiles:insert page="/refreshPopulate.do" />
	
	<tiles:insert attribute="detail.commands"/>
	
		</div>

	<tiles:insert attribute="detail.tabs"/>

</div>
