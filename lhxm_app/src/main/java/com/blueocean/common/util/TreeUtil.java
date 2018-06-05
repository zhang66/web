package com.blueocean.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blueocean.common.data.TreeNode;

/**
 * 树形结构组合工具类
 * 
 * @author scl
 *
 */
public class TreeUtil {

	public static Object packageTree(List<? extends TreeNode> datas) {
		List<TreeNode> nodeTree = new ArrayList<>();

		Map<Object, TreeNode> nodeMap = getNodeMap(datas);
		for (TreeNode node : datas) {
			// 子菜单
			TreeNode child = node;
			Object pid = node.getPvalue();
			if (pid == null || "".equals(pid) || 1 == Integer.valueOf(pid.toString())) {
				nodeTree.add(node);
			} else {
				// 父菜单
				TreeNode parentNode = nodeMap.get(child.getPvalue());
				// 组合父菜单和子菜单的关系
				parentNode.getItems().add(child);
				child.setParent(parentNode);
			}
		}
		return nodeTree;
	}

	private static Map<Object, TreeNode> getNodeMap(List<? extends TreeNode> datas) {
		Map<Object, TreeNode> nodeMap = new HashMap<Object, TreeNode>();
		datas.forEach(x -> nodeMap.put(x.getValue(), x));
		return nodeMap;
	}
}
