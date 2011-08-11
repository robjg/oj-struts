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
import org.oddjob.state.StateEvent;
import org.oddjob.webapp.WebappConstants;
import org.oddjob.webapp.model.JobInfoLookup;
import org.oddjob.webapp.struts.forms.DetailForm;

/**
 * The Struts Action which handles the state tab.
 *  
 * @author Rob Gordon.
 */
public class StateTabAction extends Action {
	private static final Logger logger = Logger.getLogger(StateTabAction.class);
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		DetailForm detailForm = (DetailForm) form;
		logger.debug("Retrieving state for [" + detailForm.getRefId() + "]" );
		
		ServletContext context = request.getSession().getServletContext();
		JobInfoLookup lookup = (JobInfoLookup) context.getAttribute(WebappConstants.DETAIL_LOOKUP);
		
		StateEvent jobStateEvent = lookup.stateFor(detailForm.getRefId());
		
		detailForm.setJobState(jobStateEvent.getState().toString());
		detailForm.setTime(jobStateEvent.getTime().toString());
		Throwable t = jobStateEvent.getException();
		if (t == null) {
			detailForm.setException("");
		}
		else {
			// TODO: full stack trace.
			detailForm.setException(t.getMessage());
		}
		
		/* forward to the tab state jsp */
		return (mapping.getInputForward());
	}
}