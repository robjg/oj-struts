/*
 * (c) Rob Gordon 2005
 */
package org.oddjob.webapp.struts.forms;

import java.util.LinkedHashMap;
import java.util.Map;

import org.oddjob.webapp.model.JobInfoLookup;
import org.oddjob.webapp.model.NodeInfo;

/**
 * Build a TreeNodeBean hierarchy.
 * 
 * @author Rob Gordon
 */
public class TreeNodeBeanBuilder {

	private final JobInfoLookup lookup;

	/**
	 * Constructor.
	 * 
	 * @param lookup Lookup to use for building.
	 */
	public TreeNodeBeanBuilder(JobInfoLookup lookup) {
		if (lookup == null) {
			throw new NullPointerException("Lookup can't be null!");
		}
		this.lookup = lookup;
	}
	
	/**
	 * Build the root bean.
	 */
	public TreeNodeBean buildRoot() {
		TreeNodeBean root = new TreeNodeBean();
		root.setRefId(lookup.getRootRefId());
		buildBean(root, 0);
		return root;
	}

	public void refresh(TreeNodeBean bean) {
		String refId = bean.getRefId();
		NodeInfo nodeInfo = lookup.nodeInfoFor(refId);
		bean.setTreeNodeBeanBuilder(this);
		bean.setNodeName(nodeInfo.getNodeName());
		bean.setIconId(nodeInfo.getIconId());
		Map<String, TreeNodeBean> childMap = bean.getChildMap();
		bean.setHasChildren(nodeInfo.getHasChildren());
		if (childMap == null) {
			return;
		}
		Map<String, TreeNodeBean> newMap = 
			new LinkedHashMap<String, TreeNodeBean>();
		String[] childRefIds = nodeInfo.getChildRefIds();
		for (int i = 0; i < childRefIds.length; ++i) {
			TreeNodeBean child = (TreeNodeBean) childMap.get(childRefIds[i]); 
			if (child == null) {
				child = new TreeNodeBean();
				child.setRefId(childRefIds[i]);
				buildBean(child, bean.getDepth() + 1);
			}
			else {
				refresh(child);
			}
			newMap.put(child.getRefId(), child);				
		}
		bean.setChildMap(newMap);
	}
	
	/**
	 * Build the children.
	 * 
	 * @param parent
	 * @return
	 */
	public Map<String, TreeNodeBean> buildChildren(TreeNodeBean parent) {
		String refId = parent.getRefId();
		NodeInfo nodeInfo = lookup.nodeInfoFor(refId);
		
		Map<String, TreeNodeBean> children = new LinkedHashMap<String, TreeNodeBean>();
		String [] childRefIds = nodeInfo.getChildRefIds();
		for (int i = 0; i < childRefIds.length; ++i) {
			TreeNodeBean child = new TreeNodeBean();
			child.setRefId(childRefIds[i]);
			buildBean(child, parent.getDepth() + 1);
			children.put(child.getRefId(), child);
		}
		return children;		
	}
	
	/**
	 * Helper function to build the bean.
	 * 
	 * @param bean The bean we're building.
	 * @param level The depth the bean is at.
	 */
	void buildBean(TreeNodeBean bean, int level) {				
		String refId = bean.getRefId();
		NodeInfo nodeInfo = lookup.nodeInfoFor(refId);
		bean.setTreeNodeBeanBuilder(this);
		bean.setDepth(level);
		bean.setNodeName(nodeInfo.getNodeName());
		bean.setIconId(nodeInfo.getIconId());
		bean.setHasChildren(nodeInfo.getHasChildren());
	}

	
}
