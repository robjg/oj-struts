package org.oddjob.webapp.servlets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.oddjob.images.IconTip;
import org.oddjob.webapp.WebappConstants;
import org.oddjob.webapp.model.IconRegistry;


/**
 *	This is still a work in progress. 
 */

public class IconServlet extends HttpServlet {
	private static final long serialVersionUID = 20051109;
	private static final Logger logger = Logger.getLogger(IconServlet.class);
	
	private final IconRegistry iconRegistry 
		= new IconRegistry();

	public void init(ServletConfig config) throws ServletException {

		ServletContext context = config.getServletContext();
		context.setAttribute(WebappConstants.ICON_REGISTRY, iconRegistry);		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String iconId = (String)request.getParameter("iconId");
		if (iconId == null) {
			logger.debug("No request paramter iconId.");
			return;
		}
		IconTip iconTip = iconRegistry.retrieve(iconId);
		if (iconTip == null) {
			logger.debug("No icon for [" + iconId + "]");
			return;
		}
		
		// Attempt to get IE to cache images
		response.setDateHeader("Expires", -1);
		response.setContentType("image/gif");
		response.getOutputStream().write(iconTip.getImageData());
	}
	
	public long getLastModified(HttpServletRequest request) {
		return 0;
	}
}
