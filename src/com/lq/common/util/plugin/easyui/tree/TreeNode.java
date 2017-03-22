package com.lq.common.util.plugin.easyui.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** id of treenode */
	private String id;
	
	/** text of treenode */
	private String text;
	
	/** parentid of treenode */
	private String pid;

	/** icon url of treenode */
	private String iconCls;

	/** is view checkbox. default: false */
	private boolean checkbox = false;
	
	/** is checked. default :false */
	private boolean isChecked = false;

	/** isexpand. default: open/closed */
	private stateType state = stateType.closed;

	/** object value */
	private Object attribute;
	
	/**  */
	private List<TreeNode> children = new ArrayList<TreeNode>();
	
	/**  */
	private String target;

	/**  */
	public static enum stateType {
		open,
		closed
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public stateType getState() {
		return state;
	}

	public void setState(stateType state) {
		this.state = state;
	}

	public Object getAttribute() {
		return attribute;
	}

	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}
