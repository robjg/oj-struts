<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%
	String refId = request.getParameter("refId");
	if (refId == null) {
		refId = "";
	}
	pageContext.setAttribute("refId", refId);		
	String refresh = (String) request.getSession().getAttribute("refresh");
	if (refresh != null) {
		response.setHeader("refresh", "" + refresh + "; URL=index.jsp?refId="
				+ refId);
	}
%>

<html>
	<head>
		<title>Oddjob</title>
	</head>
	<frameset cols="40%,60%">
		<html:frame action="oddjob-tree"
			paramId="refId" paramName="refId"/>
		<html:frame frameName="detail" action="detail"
			paramId="refId" paramName="refId"/>
	</frameset>
</html>