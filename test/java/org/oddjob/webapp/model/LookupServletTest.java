package org.oddjob.webapp.model;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import junit.framework.TestCase;

import org.oddjob.OJConstants;
import org.oddjob.Oddjob;
import org.oddjob.OddjobLookup;
import org.oddjob.arooa.xml.XMLConfiguration;
import org.oddjob.jobs.WaitJob;
import org.oddjob.state.ParentState;
import org.oddjob.state.StateConditions;
import org.oddjob.webapp.WebappConstants;
import org.oddjob.webapp.servlets.LookupServlet;

public class LookupServletTest extends TestCase {

	class OurConfig implements ServletConfig {
		
		Oddjob oj;
		
		JobInfoLookup lookup;
		
		public String getInitParameter(String arg0) {
			throw new RuntimeException("Unexpected.");
		}
		public Enumeration<?> getInitParameterNames() {
			throw new RuntimeException("Unexpected.");
		}
		public ServletContext getServletContext() {
			return new ServletContext() {

				public Object getAttribute(String arg0) {
					if (arg0.equals(WebappConstants.ODDJOB_INSTANCE)) {
						return oj;
					}
					if (arg0.equals(WebappConstants.ICON_REGISTRY)) {
						return new IconRegistry();
					}
					throw new RuntimeException("Unexpected.");
				}

				public Enumeration getAttributeNames() {
					throw new RuntimeException("Unexpected.");
				}

				public ServletContext getContext(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public String getInitParameter(String arg0) {
					if (arg0.equals(WebappConstants.LOG_FORMAT_PARAM)) {
						return OJConstants.DEFAULT_LOG_FORMAT;
					}

					if (arg0.equals(WebappConstants.ROOT_PARAM)) {
						return "echo";
					}
					throw new RuntimeException("Unexpected.");
				}

				public Enumeration getInitParameterNames() {
					throw new RuntimeException("Unexpected.");
				}

				public int getMajorVersion() {
					throw new RuntimeException("Unexpected.");
				}

				public String getMimeType(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public int getMinorVersion() {
					throw new RuntimeException("Unexpected.");
				}

				public RequestDispatcher getNamedDispatcher(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public String getRealPath(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public RequestDispatcher getRequestDispatcher(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public URL getResource(String arg0)
						throws MalformedURLException {
					throw new RuntimeException("Unexpected.");
				}

				public InputStream getResourceAsStream(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public Set getResourcePaths(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public String getServerInfo() {
					throw new RuntimeException("Unexpected.");
				}

				public Servlet getServlet(String arg0) throws ServletException {
					throw new RuntimeException("Unexpected.");
				}

				public String getServletContextName() {
					throw new RuntimeException("Unexpected.");
				}

				public Enumeration getServletNames() {
					throw new RuntimeException("Unexpected.");
				}

				public Enumeration getServlets() {
					throw new RuntimeException("Unexpected.");
				}

				public void log(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public void log(Exception arg0, String arg1) {
					throw new RuntimeException("Unexpected.");
				}

				public void log(String arg0, Throwable arg1) {
					throw new RuntimeException("Unexpected.");
				}

				public void removeAttribute(String arg0) {
					throw new RuntimeException("Unexpected.");
				}

				public void setAttribute(String arg0, Object arg1) {
					assertEquals(WebappConstants.DETAIL_LOOKUP, arg0);
					lookup = (JobInfoLookup) arg1;
				}

				@Override
				public String getContextPath() {
					throw new RuntimeException("Unexpected.");
				}
				
			};
		}
		public String getServletName() {
			throw new RuntimeException("Unexpected.");
		}
	}
	
	public void testInit() throws Exception {

		String xml = 
			"<oddjob>" +
			"<job>" +
			"<sequence from='1' id='echo'/>" +
			"</job>" +
			"</oddjob>";
		
		Oddjob oj = new Oddjob();
		oj.setConfiguration(new XMLConfiguration("XML", xml));

		oj.run();
		
		assertEquals(ParentState.COMPLETE, oj.lastStateEvent().getState());
		
		OddjobLookup lookup = new OddjobLookup(oj);
		
		Integer current = lookup.lookup("echo.current", Integer.class);
		assertEquals(new Integer(1), current);
		
		LookupServlet test = new LookupServlet();
		
		OurConfig config = new OurConfig(); 
		config.oj = oj;
		
		test.init(config);

		String refId = config.lookup.getRootRefId();
		
		WebJobActions actions = config.lookup.actionsFor(refId);
		actions.action("Hard Reset");
		
		Object sequence = lookup.lookup("echo");
		
		WaitJob wait = new WaitJob();
		wait.setFor(sequence);
		wait.setState(StateConditions.READY);
		wait.run();
		
		// The notification could arrive while oddjob is still locked
		// because of the hard reset.
		Thread.sleep(100);
		
		actions.action("Run");
		
		wait.hardReset();
		wait.setState(StateConditions.COMPLETE);
		wait.run();
		
		current = lookup.lookup("echo.current", Integer.class);
		assertEquals(new Integer(2), current);
		
		test.destroy();
	}
}
