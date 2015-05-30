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
		if (node.size() >= BPNode.MAX) {
			int i, firstBackup = node.firstKey();
			Map.Entry<Integer, BPNode> first, last;
			TreeMap<Integer, BPNode> newChildren = new TreeMap<Integer, BPNode>();
			BPNode left = new BPNode(), right = new BPNode();

			left.children = new TreeMap<Integer, BPNode>();
			right.children = new TreeMap<Integer, BPNode>();
			for (i = 0; i < BPNode.MAX / 2; i++) {
				first = node.firstEntry();
				left.addChild(first.getKey(), first.getValue());
				node.delChild(first.getKey());

				last = node.lastEntry();
				right.addChild(last.getKey(), last.getValue());
				node.delChild(last.getKey());
			}
			left.parent = node;
			right.parent = node;

			System.out.println("left: " + left);
			System.out.println("right: " + right);

			if (node.parent == null) {
				newChildren.put(left.firstKey(), left);
				newChildren.put(right.firstKey(), right);

				node.children = new TreeMap<Integer, BPNode>(newChildren);
				this.root = node;
				node.parent = null;
			}
			else {
				node.parent.delChild(firstBackup);
				node.parent.addChild(left.firstKey(), left);
				node.parent.addChild(right.firstKey(), right);

				this.addFix(node.parent);
			}
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
