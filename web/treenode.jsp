<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>


<nested:root>
 
  <img src="gfx/spacer.gif" width="<nested:write property="nodeIndent" />" height="1">
  
  <nested:equal property="showChildren" value="true"> 
  
    <nested:image src="gfx/km_minus.gif" property="collapse"/>
    <nested:notEmpty property="iconId">
    	<nested:img page="/icon" property="iconIdRequest"/>
    </nested:notEmpty>
    <nested:link href="index.jsp" target="_top"
    		property="request">
	    <nested:write property="nodeName" />
	</nested:link>
    <br> 
    <nested:iterate property="childCollection"> 
      <jsp:include page="treenode.jsp" /> 
    </nested:iterate> 
    
  </nested:equal>

  <nested:equal property="showChildren" value="false"> 
    
    <nested:equal property="hasChildren" value="true">
      <nested:image src="gfx/km_plus.gif" property="expand"/>
    </nested:equal>
    
    <nested:equal property="hasChildren" value="false">
      <img src="gfx/km_empty.gif">
    </nested:equal>
    
    <nested:notEmpty property="iconId">
    	<nested:img page="/icon" property="iconIdRequest"/>
    </nested:notEmpty>
    <nested:link href="index.jsp" target="_top"
    		property="request">
	    <nested:write property="nodeName" />
	</nested:link>
    <br> 
    
  </nested:equal>
   
</nested:root>
