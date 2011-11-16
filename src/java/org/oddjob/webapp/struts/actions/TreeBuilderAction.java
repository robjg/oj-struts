/*
 */
package org.oddjob.webapp.struts.actions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.oddjob.webapp.WebappConstants;
import org.oddjob.webapp.model.JobInfoLookup;
import org.oddjob.webapp.struts.forms.TreeFormBean;
import org.oddjob.webapp.struts.forms.TreeNodeBeanBuilder;

/**
 * Struts action to build the tree view.
 * 
 * @author Rob Gordon.
 */
public class TreeBuilderAction extends Action {
//	private static final Logger logger = Logger.getLogger(TreeBuilderAction.class);
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TreeFormBean treeForm = (TreeFormBean) form;
		
		ServletContext context = request.getSession().getServletContext();
		JobInfoLookup lookup = (JobInfoLookup) context.getAttribute(WebappConstants.DETAIL_LOOKUP);

		String currentRefId = request.getParameter("refId");
		
		TreeNodeBeanBuilder builder = new TreeNodeBeanBuilder(lookup,
				currentRefId);
		
		if (treeForm.getRoot() == null) {
			treeForm.setRoot(builder.buildRoot());			
		} else {
			 builder.refresh(treeForm.getRoot());
		}
		
		/* return back to the page we came */
		return (mapping.findForward("input"));
	}
	
}