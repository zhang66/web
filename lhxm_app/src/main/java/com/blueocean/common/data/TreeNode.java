package com.blueocean.common.data;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 树状结构实体类
 * 
 * @author scl
 *
 */
public class TreeNode {

	private boolean show = false;
	private Object value;
	private String name;
	@JSONField(serialize = false)
	private Object pvalue;
	@JSONField(serialize = false)
	private int level;
	@JSONField(serialize = false)
	private boolean leaf;
	private TreeNode parent;
	private List<TreeNode> items = new ArrayList<>();

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@JsonIgnore(true)
	public Object getPvalue() {
		return pvalue;
	}

	public void setPvalue(Object pcode) {
		this.pvalue = pcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSONField(serialize = false)
	public int getLevel() {
		level = parent == null ? 1 : parent.getLevel() + 1;
		return level;
	}

	public List<TreeNode> getItems() {
		return items;
	}

	public void setItems(List<TreeNode> children) {
		this.items = children;
	}
}
