/*
 */
package org.oddjob.webapp.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.oddjob.webapp.struts.forms.DetailForm;

/**
 * The Struts Action which handles the detail of a tree node being
 * selected.
 *  
 * @author Rob Gordon.
 */
public class DetailPanelAction extends Action {
	private static final Logger logger = Logger.getLogger(DetailPanelAction.class);
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		DetailForm detailForm = (DetailForm) form;
		String refId = detailForm.getRefId();
		logger.debug("Select detail for job ref [" + refId + "]" );
		
		if (refId == null || "".equals(refId)) {
			return (mapping.findForward("nodetail"));
		}
		else {
			return (mapping.findForward("detail"));
		}
	}
}