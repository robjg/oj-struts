<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<nested:form action="/oddjob-tree">
  
  <logic:present name="treeForm" property="root">
	  <nested:nest property="root" >
	    <jsp:include page="treenode.jsp" />
	  </nested:nest>
  </logic:present>
  <logic:empty name="treeForm" property="root">
  	<%= "Session lost - please enter url again." %>
  </logic:empty>
</nested:form>
