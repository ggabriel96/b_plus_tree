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
		if (node.parent != null && node.parent.firstKey() > node.firstKey()) {
			node.parent.delChild(node.parent.firstKey());
			node.parent.addChild(node.firstKey(), node);
		}

		if (node.size() >= BPNode.MAX) {
			int i, nodeFirstKey = node.firstKey();
			Map.Entry<Integer, BPNode> entry;
			TreeMap<Integer, BPNode> newChildren = new TreeMap<Integer, BPNode>();
			BPNode left = new BPNode(), right = new BPNode();

			left.children = new TreeMap<Integer, BPNode>();
			for (i = 0; i < BPNode.MAX / 2; i++) {
				entry = node.firstEntry();
				left.addChild(entry.getKey(), entry.getValue());

				// updating parents, not "node" anymore, but "left"
				if (entry.getValue() != null) entry.getValue().parent = left;
				node.delChild(entry.getKey());
			}

			right.children = new TreeMap<Integer, BPNode>();
			for (; i < BPNode.MAX; i++) {
				entry = node.firstEntry();
				right.addChild(entry.getKey(), entry.getValue());

				// updating parents, not "node" anymore, but "right"
				if (entry.getValue() != null) entry.getValue().parent = right;
				node.delChild(entry.getKey());
			}

			if (node.parent == null) {
				newChildren.put(left.firstKey(), left);
				newChildren.put(right.firstKey(), right);

				left.parent = node;
				right.parent = node;

				node.children = new TreeMap<Integer, BPNode>(newChildren);
				this.root = node;
				node.parent = null;
			}
			else {
				node.parent.delChild(nodeFirstKey);
				node.parent.addChild(left.firstKey(), left);
				node.parent.addChild(right.firstKey(), right);

				left.parent = node.parent;
				right.parent = node.parent;
			}
		}
		
		if (node.parent != null) this.addFix(node.parent);
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
