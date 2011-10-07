/*
 * (c) Rob Gordon 2005
 */
package org.oddjob.webapp.struts.forms;

import junit.framework.TestCase;

import org.oddjob.Iconic;
import org.oddjob.Oddjob;
import org.oddjob.arooa.xml.XMLConfiguration;
import org.oddjob.images.IconHelper;
import org.oddjob.images.IconListener;
import org.oddjob.images.IconTip;
import org.oddjob.monitor.context.ExplorerContext;
import org.oddjob.monitor.model.MockExplorerContext;
import org.oddjob.util.ThreadManager;
import org.oddjob.webapp.model.IconRegistry;
import org.oddjob.webapp.model.JobInfoLookup;

public class TreeNodeBeanBuilderTest extends TestCase {

	class OurExplorerContext extends MockExplorerContext {
		
		@Override
		public ThreadManager getThreadManager() {
			return null;
		}
		
		@Override
		public Object getThisComponent() {
			return null;
		}
		
		@Override
		public ExplorerContext addChild(Object child) {
			return this;
		}
	}
	
	/** Test simply building a bean */
	public void testBuildBean() {
		String xml = 
			"<oddjob>" +
			" <job>" +
			"  <echo>Hello World</echo>" +
			" </job>" +
			"</oddjob>";
		
		Oddjob oj = new Oddjob();
		oj.setConfiguration(new XMLConfiguration("XML", xml));
		oj.setName("Test");
		oj.run();
		
		JobInfoLookup lookup = new JobInfoLookup(new IconRegistry());
		lookup.setRoot(oj, new OurExplorerContext());
		TreeNodeBeanBuilder builder = new TreeNodeBeanBuilder(lookup);
		TreeNodeBean bean = builder.buildRoot();
		
		assertNotNull(bean);
		assertTrue(bean.getHasChildren());
		assertEquals("Test", bean.getNodeName());
		
		bean.getExpand();
		assertEquals(1, bean.getChildCollection().size());
		
		// check child.
		TreeNodeBean[] children = (TreeNodeBean[]) bean.getChildCollection().toArray(new TreeNodeBean[0]);
		TreeNodeBean child = children[0];
		child.getExpand();
		assertEquals(0, child.getChildCollection().size());
	}

	/** More complicated bean - tracking down a bug where child displayed twice. */
	public void testBuildBean2() {
		String xml = 
			"<oddjob>" +
			" <job>" +
			"  <sequential name='Child 1'>" +
			"   <jobs>" +
			"    <sequential name='Child 2'>" +
			"     <jobs>" +
			"      <echo name='Child 3'>Hello World</echo>" +
			"     </jobs>" +
			"    </sequential>" +
			"   </jobs>" +
			"  </sequential>" +
			" </job>" +
			"</oddjob>";
		
		Oddjob oj = new Oddjob();
		oj.setConfiguration(new XMLConfiguration("XML", xml));
		oj.setName("Test");
		oj.run();
		
		JobInfoLookup lookup = new JobInfoLookup(new IconRegistry());
		lookup.setRoot(oj, new OurExplorerContext());
		TreeNodeBeanBuilder builder = new TreeNodeBeanBuilder(lookup);
		TreeNodeBean bean = builder.buildRoot();
		bean.getExpand();
		
		TreeNodeBean[] children;
		TreeNodeBean child;
		
		children = (TreeNodeBean[]) bean.getChildCollection().toArray(new TreeNodeBean[0]);
		child = children[0];
		assertEquals("Child 1", child.getNodeName());
		
		child.getExpand();
		children = (TreeNodeBean[]) child.getChildCollection().toArray(new TreeNodeBean[0]);
		child = children[0];
		assertEquals("Child 2", child.getNodeName());
		
		child.getExpand();
		children = (TreeNodeBean[]) child.getChildCollection().toArray(new TreeNodeBean[0]);
		child = children[0];
		assertEquals("Child 3", child.getNodeName());
	}
	
	public void testIcon() {
		class I implements Iconic {
			IconHelper ih = new IconHelper(this);
			boolean toggle;
			public void addIconListener(IconListener listener) {
				if (toggle) {
					ih.changeIcon(IconHelper.COMPLETE);
				} else {
					ih.changeIcon(IconHelper.EXCEPTION);
				}
				toggle = !toggle;
				ih.addIconListener(listener);
			}
			public IconTip iconForId(String id) {
				return ih.iconForId(id);
			}
			public void removeIconListener(IconListener listener) {
				ih.removeIconListener(listener);
			}
		}
		I i = new I();
		
		JobInfoLookup lookup = new JobInfoLookup(new IconRegistry());
		lookup.setRoot(i, null);
		TreeNodeBeanBuilder builder = new TreeNodeBeanBuilder(lookup);
		TreeNodeBean bean = builder.buildRoot();

		String iconId; 
		iconId = bean.getIconId();
		assertEquals(IconHelper.EXCEPTION, iconId);
		
		bean = builder.buildRoot();
		
		iconId = bean.getIconId();
		assertEquals(IconHelper.COMPLETE, iconId);
	}

}
