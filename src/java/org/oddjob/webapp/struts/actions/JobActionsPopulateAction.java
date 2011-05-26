/*
 */
package org.oddjob.webapp.struts.actions;

import java.util.Iterator;
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
 * The Struts Action which handles the state tab.
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
		Map commandMap = new LinkedHashMap();
		for (Iterator it = actions.commands().iterator(); it.hasNext();) {
			String command = (String) it.next();
			commandMap.put(command, new Boolean(
					actions.isEnabled(command)));
		}
		jobActionsForm.setCommandMap(commandMap);
		
		/* forward to the tab state jsp */
		return (mapping.getInputForward());
	}
}