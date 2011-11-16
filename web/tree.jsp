<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN"
"http://www.w3.org/TR/html4/frameset.dtd"> 

<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>
<head>
	<style type="text/css">
	@import url(tree.css);
	</style>
</head>
<body bgcolor="#FFFFFF">
	<tiles:insert definition="tree.view"/>
</body>
</html>