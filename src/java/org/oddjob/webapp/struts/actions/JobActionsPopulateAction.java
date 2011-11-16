/*
 */
package org.oddjob.webapp.struts.actions;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.oddjob.webapp.WebappConstants;
import org.oddjob.webapp.model.JobInfoLookup;
import org.oddjob.webapp.model.WebJobActions;
import org.oddjob.webapp.struts.forms.JobActionsForm;


/**
 * The Struts Action that populates the Job Action Buttons.
 *  
 * @author Rob Gordon.
 */
public class JobActionsPopulateAction extends Action {
//	private static final Logger logger = Logger.getLogger(JobActionsPopulateAction.class);
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JobActionsForm jobActionsForm = (JobActionsForm) form;	
		
		ServletContext context = request.getSession().getServletContext();
		JobInfoLookup lookup = (JobInfoLookup) context.getAttribute(WebappConstants.DETAIL_LOOKUP);
		WebJobActions actions = lookup.actionsFor(jobActionsForm.getRefId());
		Map<String, Boolean> commandMap = new LinkedHashMap<String, Boolean>();
		for (String command : actions.commands()) {
			commandMap.put(command, new Boolean(
					actions.isEnabled(command)));
		}
		jobActionsForm.setCommandMap(commandMap);
		
		/* forward to the tab state jsp */
		return (mapping.getInputForward());
	}
}