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
		// Vain attempt to for IE to cache images
		response.setDateHeader("Expires", -1);
		ImageIcon imageIcon = new ImageIcon(iconTip.getImageData());
		response.setContentType("image/jpeg");
		int width = imageIcon.getIconWidth();
		int height = imageIcon.getIconHeight();
		BufferedImage bufferedIcon = new BufferedImage(
				width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D)bufferedIcon.getGraphics();
		graphics.setBackground(Color.WHITE);
		graphics.clearRect(0, 0, width, height);
		imageIcon.paintIcon(null, graphics, 0, 0);
		ImageIO.write(bufferedIcon, "jpg", response.getOutputStream());
	}
	
	public long getLastModified(HttpServletRequest request) {
		return 0;
	}
}
