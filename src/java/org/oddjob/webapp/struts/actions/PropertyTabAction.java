/*
 */
package org.oddjob.webapp.struts.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.oddjob.webapp.WebappConstants;
import org.oddjob.webapp.model.JobInfoLookup;
import org.oddjob.webapp.struts.forms.DetailForm;

/**
 * The Struts Action which handles the property tab.
 *  
 * @author Rob Gordon.
 */
public class PropertyTabAction extends Action {
	private static final Logger logger = Logger.getLogger(PropertyTabAction.class);
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		DetailForm detailForm = (DetailForm) form;
		logger.debug("Retrieving properties for [" + detailForm.getRefId() + "]" );

		ServletContext context = request.getSession().getServletContext();
		JobInfoLookup lookup = (JobInfoLookup) context.getAttribute(WebappConstants.DETAIL_LOOKUP);
		
		String selectedRefId = detailForm.getRefId();
		detailForm.setProperties(lookup.propertiesFor(selectedRefId));

		/* forward to the tab action */
		return (mapping.getInputForward());
	}
}