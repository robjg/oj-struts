/*
 * (c) Rob Gordon 2005
 */
package org.oddjob.webapp.struts.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper for a TreeNode to keep the form as simple
 * as possible.
 */
public class TreeNodeBean implements Serializable {
	private static final long serialVersionUID = 20051229;
	
	private transient TreeNodeBeanBuilder builder;
	
	/** The reference id for this node. */
	private String refId;
	
	/** The node name */
	private transient String nodeName;
	
	/** The collection of child TreeNodeBeans. */
	private Map<String, TreeNodeBean> childCollection;
	
	/** Has this node been expanded */
	private boolean showChildren;
	
	/** The depth int the hiearchy. */
	private int depth;

	/** The icon id of the node */
	private transient String iconId;
	
	private boolean hasChildren;
	
	private String styleClass;
	
	public void setTreeNodeBeanBuilder(TreeNodeBeanBuilder builder){
		this.builder = builder;
	}
	
	/**
	 *  return the collection of children 
	 */
	public Collection<TreeNodeBean> getChildCollection() {
		if (childCollection == null) {
			return null;
		}
		return new ArrayList<TreeNodeBean>(childCollection.values());
	}

	void setChildMap(Map<String, TreeNodeBean> childMap) {
		this.childCollection = childMap;
	}
	
	Map<String, TreeNodeBean> getChildMap() {
		return childCollection;
	}
	
	/**
	 * The node name property.
	 * 
	 * @return The node name.
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * Set the node name.
	 * 
	 * @param nodeName The node name.
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 *  getter to return the depth.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Setter for depth.
	 * 
	 * @param depth The depth.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Getter for nodeIndent property.
	 * 
	 * @return The node indent.
	 */
	public int getNodeIndent() {
		return 20 * depth;
	}

	/**
	 * Getter method for the "showChildren" 
	 * property.
	 * 
	 * @return true if expanded.
	 */
	public boolean getShowChildren() {
		return this.showChildren;
	}

	/**
	 * Getter for the "hasChildren" property.
	 * 
	 * @return true if this node has child nodes 
	 */
	public boolean getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
		if (!hasChildren) {
			showChildren = false;
		}
	}
	
	/**
	 * A "fake" nested bean property (for image submit)
	 * <p>
	 * This is called twice per request processing. Don't know why.
	 */
	public TreeNodeBean getExpand() {
		showChildren = true;
		if (childCollection == null) {
			// someones clicked on the expand children image
			// so create the children ready for the form.
			childCollection = builder.buildChildren(this);
		}
		return this;
	}

	/**
	 * A "fake" nested bean property (for image submit) 
	 */
	public TreeNodeBean getCollapse() {
		showChildren = false;
		childCollection = null;
		return this;
	}
	
	/**
	 * Setter for the image submit's ".x" property
	 * 
	 * @param The unused x co-ordinate.
	 */
	public void setX(int i) {
	}

	/**
	 * Empty setter for the image submit's ".y" property 
	 * 
	 * @param The unused y co-ordinate.
	 */
	public void setY(int i) {
	}

	
	public String getRefId() {
	    return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}
	
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getIconId() {
		return iconId;
	}
	
	public Map<String, String> getRequest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("refId", refId);
		return map;
	}
	
	public Map<String, String> getIconIdRequest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("iconId", iconId);
		return map;
	}

	public String getStyleClass() {
		if (styleClass == null) {
			return "";
		}
		else {
			return styleClass;
		}
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
}