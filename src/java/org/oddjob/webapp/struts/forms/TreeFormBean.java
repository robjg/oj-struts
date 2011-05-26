/*
 * (c) Rob Gordon 2005
 */
package org.oddjob.webapp.struts.forms;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.oddjob.webapp.WebappConstants;
import org.oddjob.webapp.model.JobInfoLookup;

/**
 * This is a class you write yourself in the <NeXt:recursion tutorial>
 */
public class TreeFormBean extends ActionForm {
	private final static long serialVersionUID = 20051104;
	
	private TreeNodeBean root;
	private String refId;
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		JobInfoLookup lookup = (JobInfoLookup) context.getAttribute(WebappConstants.DETAIL_LOOKUP);
		TreeNodeBeanBuilder builder = new TreeNodeBeanBuilder(lookup);
		if (this.root == null) {
			this.root = builder.buildRoot();			
		} else {
			 builder.refresh(root);
		}
	}
	
	/* returns the reference to the monkey tree */
	public TreeNodeBean getRoot() {
		return this.root;
	}

	public void setRoot(TreeNodeBean root) {
		this.root = root;
	}
				
	public void setRefId(String refId) {
	    this.refId = refId;
	}
	
	public String getRefId() {
	    return this.refId;
	}

}