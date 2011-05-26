/*
 */
package org.oddjob.webapp.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.oddjob.webapp.struts.forms.RefreshForm;

/**
 * The Struts Action which handles the refresh field.
 *  
 * @author Rob Gordon.
 */
public class RefreshPerformAction extends Action {
	private static final Logger logger = Logger.getLogger(RefreshPerformAction.class);
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		RefreshForm refreshForm = (RefreshForm) form;
		logger.debug("Refresh [" + refreshForm.getRefresh() + "]" );
		
		HttpSession session = request.getSession();
		
		String refresh = refreshForm.getRefresh();
		if (refresh != null && !"".equals(refresh)) {
			session.setAttribute("refresh", refresh);
		}
		else {
			session.removeAttribute("refresh");
		}
		
		/* forward to the tab state jsp */
		return (mapping.getInputForward());
	}
}