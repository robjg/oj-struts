/*
 * (c) Rob Gordon 2005
 */
package org.oddjob.webapp.struts.forms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.oddjob.logging.LogEvent;

/**
 * A form which contains information required to set the detail panel tab.
 * 
 * @author Rob Gordon.
 */
public class DetailForm extends ActionForm implements Serializable {
	private final static long serialVersionUID = 20051104;

	/** The reference id of the selected job */
	private transient String refId;
	
	/** Properties for the properties tab. */
	private transient Map<String, String> properties;
	
	/** Job state for the state tab. */
	private transient String jobState;
	private transient String time;
	private transient String Exception;
	
	/** Console Events for the console tab. */
	private transient List<LogEvent> consoleEvents;
	
	/** Log Events for the log tab. */
	private transient List<LogEvent> logEvents;
	
	/** The selected tab saved in the session. */
	private int selectedTab;
	
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
	 * Getter for exception. 
	 * 
	 * @return The exception string.
	 */
	public String getException() {
		return Exception;
	}

	/**
	 * Setter for the exception.
	 * 
	 * @param exception The exception.
	 */
	public void setException(String exception) {
		Exception = exception;
	}

	/**
	 * Getter for the state time.
	 * 
	 * @return The state time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Setter for the state time.
	 * 
	 * @param time The state time.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Getter for job state.
	 * 
	 * @return The job state.
	 */
	public String getJobState() {
		return jobState;
	}

	/**
	 * Setter for job state.
	 * 
	 * @param jobState The job state.
	 */
	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	/**
	 * Getter for properties.
	 * 
	 * @return A map of properties.
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * Setter for properties.
	 * 
	 * @param properties A map of properties.
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	/**
	 * Get for console events.
	 * 
	 * @return A list of LogEvent objects.
	 */
	public List<LogEvent> getConsoleEvents() {
		return consoleEvents;
	}

	/**
	 * Setter for console events.
	 * 
	 * @param consoleEvents A list of LogEvent objects.
	 */
	public void setConsoleEvents(List<LogEvent> consoleEvents) {
		this.consoleEvents = consoleEvents;
	}
	
	/**
	 * Getter for log events.
	 * 
	 * @return A list of LogEvent objects.
	 */
	public List<LogEvent> getLogEvents() {
		return logEvents;
	}

	/**
	 * Setter for log events.
	 * 
	 * @param logEvents A list of LogEvent objects.
	 */
	public void setLogEvents(List<LogEvent> logEvents) {
		this.logEvents = logEvents;
	}

	public int getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
	}
	
}