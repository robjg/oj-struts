<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<table id="tree">
<tr>
<td id="topbar">
	<tiles:insert attribute="tree.title"/>
</td>
</tr>
<tr>
<td id="ojtree">
	<tiles:insert attribute="tree.tree"/>
</td>
</tr>
</table>

