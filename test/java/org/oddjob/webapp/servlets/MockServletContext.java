package org.oddjob.webapp.servlets;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MockServletContext implements ServletContext {

	public Object getAttribute(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public Enumeration getAttributeNames() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public ServletContext getContext(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public String getInitParameter(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public Enumeration getInitParameterNames() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public int getMajorVersion() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public String getMimeType(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public int getMinorVersion() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public RequestDispatcher getNamedDispatcher(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public String getRealPath(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public RequestDispatcher getRequestDispatcher(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public URL getResource(String arg0) throws MalformedURLException {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public InputStream getResourceAsStream(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public Set getResourcePaths(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public String getServerInfo() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public Servlet getServlet(String arg0) throws ServletException {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public String getServletContextName() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public Enumeration getServletNames() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public Enumeration getServlets() {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public void log(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public void log(Exception arg0, String arg1) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public void log(String arg0, Throwable arg1) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public void removeAttribute(String arg0) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

	public void setAttribute(String arg0, Object arg1) {
		throw new RuntimeException("Unexpected from " + getClass());
	}

}
