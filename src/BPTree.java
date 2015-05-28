import java.io.*;
import java.util.*;

class BPTree {
	public BPNode root;

	public BPTree() {
		this.root = null;
	}

	public BPTree(int key) {
		this.root = new BPNode(key);
	}

	public BPNode find(int key) {
		return this.root.find(key);
	}

	public void add(int key) {
		if (this.root == null) {
			this.root = new BPNode(key);
		}
		else {
			BPNode nodeToAdd = this.find(key);
			nodeToAdd.addChild(key, null);
			this.addFix(nodeToAdd);
		}
	}

	public void addFix(BPNode node) {
		if (node.children.size() >= BPNode.MAX) {
			Integer half = node.children.firstKey() + ((node.children.lastKey() - node.children.firstKey()) / 2);
			TreeMap<Integer, BPNode> newChildren = new TreeMap<Integer, BPNode>();
			BPNode left = new BPNode(), right = new BPNode();

			left.children = new TreeMap<Integer, BPNode>();
			left.children.putAll(node.children.headMap(half, true));

			right.children = new TreeMap<Integer, BPNode>();
			right.children.putAll(node.children.tailMap(half, false));

			newChildren.put(left.children.firstKey(), left);
			newChildren.put(right.children.firstKey(), right);

			node.children = new TreeMap<Integer, BPNode>(newChildren);

			// System.out.println("node.children:");
			// for (Map.Entry<Integer, BPNode> entry: node.children.entrySet()) {
			// 	System.out.println(entry);
			// } System.out.println();
			//
			// System.out.println("node.children[0]:");
			// for (Map.Entry<Integer, BPNode> entry: node.children.get(left.children.firstKey()).children.entrySet()) {
			// 	System.out.println(entry);
			// } System.out.println();
			//
			// System.out.println("node.children[1]:");
			// for (Map.Entry<Integer, BPNode> entry: node.children.get(right.children.firstKey()).children.entrySet()) {
			// 	System.out.println(entry);
			// }
			// System.out.println("-----------------------");
		}
	}

	public void del(int key) {
		if (this.root != null) {
			this.root.delChild(key);
		}
	}

	public void graph() {
        System.out.println("digraph BPTree {");
        this.root.graph();
        System.out.println("}");
    }
}
