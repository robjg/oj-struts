/*
 * (c) Rob Gordon 2005
 */
package org.oddjob.webapp.struts.forms;

import org.apache.struts.action.ActionForm;

public class RefreshForm extends ActionForm {
	private static final long serialVersionUID = 20051221;

	private transient String refresh;	

	/** The reference id of the selected job */
	private transient String refId;

	/**
	 * Get the refresh rate.
	 * 
	 * @return The refresh rate.
	 */
	public String getRefresh() {
		return refresh;
	}

	/**
	 * Set refresh rate.
	 * 
	 * @param refresh The refresh rate in seconds.
	 */
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	
	/**
	 * Setter for refId.
	 * 
	 * @param refId The refId.
	 */
	public void setRefId(String refId) {
	    this.refId = refId;
	}
	
	/**
	 * Getter for refId.
	 * 
	 * @return The refId.
	 */
	public String getRefId() {
		return refId;
	}
}
