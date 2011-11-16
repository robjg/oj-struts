<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%-- 
  Tabs Layout .
  This layout allows to render several tiles in a tabs fashion.
  @param tabList A list of available tabs. We use MenuItem to carry data (name, body, icon, ...)
  @param selectedIndex Index of default selected tab
  @param parameterName Name of parameter carrying selected info in http request.
--%>

<%-- 
Use tiles attributes, and declare them as page java variable.
These attribute must be passed to the tile. 
--%>

<bean:define id="selectedTab" name="detailForm" property="selectedTab" type="java.lang.Integer"/>

<tiles:useAttribute name="parameterName" classname="java.lang.String" />
<tiles:useAttribute name="tabList" classname="java.util.List" />
<%
  
  int index = 0; // Loop index
  int selectedIndex = selectedTab.intValue();
  // Check selectedIndex bounds
  if( selectedIndex < 0 || selectedIndex >= tabList.size() ) selectedIndex = 0;
  String selectedBody = ((org.apache.struts.tiles.beans.MenuItem)tabList.get(selectedIndex)).getLink(); // Selected body
  
%>

<div class="tabs">

  <%-- Draw tabs --%>
<ul>
<logic:iterate id="tab" name="tabList" type="org.apache.struts.tiles.beans.MenuItem" >
<% // compute href
  String href = "detail.do" + "?"+parameterName + "=" + index + "&refId=" 
		  + request.getParameter("refId");
    // Don't add request URI prefix , but let the client compute the original URL
	// This allows to use a Struts action as page URL, and perform a forward.
	// Bug reported by Don Peterkofsky 
  //String href = "" + "?"+parameterName + "=" + index;
  String style = "notSelected";
  if( index == selectedIndex )
    {
	selectedBody = tab.getLink();
	style = "selected";
	} // enf if
  index++;
%>
  <li>
  <a class="<%=style%>" href="<%=href%>" ><%=tab.getValue()%></a>
  </li>
  
</logic:iterate>
</ul>
</div>


  <%-- Draw body --%>
  <div id="main">
  <tiles:insert name="<%=selectedBody%>" flush="true" />
  </div>

