<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div class="tree">

<nested:form action="/oddjob-tree">
  
  <logic:present name="treeForm" property="root">
	  <nested:nest property="root" >
	  	<ul>
	    <jsp:include page="treenode.jsp" />
	  	</ul>
	  </nested:nest>
  </logic:present>
  <logic:empty name="treeForm" property="root">
  	<%= "Session lost - please enter url again." %>
  </logic:empty>
</nested:form>

</div>