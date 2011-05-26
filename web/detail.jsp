<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html>
<head>
	<style type="text/css">
	@import url(styles.css);
	</style>
</head>
<body>
	<tiles:insert definition="detail.view"/>
</body>
</html>