/*
 * (c) Rob Gordon 2005
 */
package org.oddjob.webapp.struts.forms;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author Rob Gordon.
 */
public class JobActionsForm extends ActionForm implements Serializable {
	private final static long serialVersionUID = 20051104;

	/** The reference id of the selected job */
	private String refId;
	
	private transient String performCommand;
	
	private transient Map commandMap;
	
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

	/**
	 * Set the command map. Called by the populate action to populate
	 * the commands.
	 * 
	 * @param commandMap The <String, Boolean> command map.
	 */
	public void setCommandMap(Map commandMap) {
		this.commandMap = commandMap;
	}

	public Collection getCommands() {
		return commandMap.keySet();
	}
	
	public boolean isEnabled(String command) {
		Boolean enabled = (Boolean) commandMap.get(command);
		return enabled.booleanValue();
	}

	public String getPerformCommand() {
		return performCommand;
	}

	public void setPerformCommand(String command) {
		this.performCommand = command;
	}
}