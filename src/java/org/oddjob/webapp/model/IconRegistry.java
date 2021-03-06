/*
 * Copyright � 2004, Rob Gordon.
 */
package org.oddjob.webapp.model;

import java.util.HashMap;
import java.util.Map;

import org.oddjob.Iconic;
import org.oddjob.images.IconTip;

/**
 * A registry of icons so they can be served up to 
 * the view. This registry works on the principle that
 * an icon id identifies the same icon for all jobs. 
 * 
 * @author Rob Gordon.
 */
public class IconRegistry {

	/** The icons. */
	final private Map icons = new HashMap();

	/**
	 * Register an iconId. If the icon id isn't
	 * registered already, the icon is looked up.
	 * 
	 * @param iconId The iconId.
	 * @param iconic The Iconic that can provide 
	 * the lookup.
	 */
	public void register(String iconId, Iconic iconic) {
		IconTip iconTip = null;
		synchronized (icons) {
			iconTip = (IconTip)icons.get(iconId);
			if (iconTip == null) {
				iconTip = iconic.iconForId(iconId);
				icons.put(iconId, iconTip);
			}
		}
	}

	/**
	 * Retrieve an IconTip for a given icon id.
	 * 
	 * @param iconId The iconId.
	 * @return The IconTip, null if none exists if
	 * nothing is registered for that id.
	 */
	public IconTip retrieve(String iconId) {
		synchronized (icons) {
			return (IconTip)icons.get(iconId);
		}
	}
}
