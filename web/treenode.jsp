<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>


<nested:root>
 
  <li>
 
  <nested:define id="styleClass" property="styleClass" type="java.lang.String"/> 
	  
  <nested:equal property="showChildren" value="true"> 
  
    <nested:image src="gfx/minus.png" property="collapse"/>
    <nested:notEmpty property="iconId">
    	<nested:img page="/icon" property="iconIdRequest"/>
    </nested:notEmpty>
    <nested:link href="index.jsp" target="_top"
    		property="request" styleClass="<%=styleClass%>">
	    <nested:write property="nodeName" />
	</nested:link>
    <nested:iterate property="childCollection"> 
    	<ul>
	      <jsp:include page="treenode.jsp" />
      	</ul> 
    </nested:iterate> 
    
  </nested:equal>

  <nested:equal property="showChildren" value="false"> 
    
    <nested:equal property="hasChildren" value="true">
      <nested:image src="gfx/plus.png" property="expand"/>
    </nested:equal>
      
    <nested:notEmpty property="iconId">
    	<nested:img page="/icon" property="iconIdRequest"/>
    </nested:notEmpty>
    <nested:link href="index.jsp" target="_top"
    		property="request" styleClass="<%=styleClass%>">
	    <nested:write property="nodeName" />
	</nested:link>
    
  </nested:equal>
  
  </li>
  
</nested:root>
