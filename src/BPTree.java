import java.io.*;
import java.util.*;

class BPTree {
	public long root, indexCount;
	public static final long NIL = -1;

	public BPTree() {
		this.root = NIL;
		this.indexCount = 0;
	}

	public BPTree(int key) {
		this.indexCount = 1;
		this.root = new BPNode(key, 0);
	}

	public BPNode find(int key) {
		return this.root.find(key);
	}

	public void add(int key) {
		if (this.root == null) {
			this.root = new BPNode(key, 0);
			this.indexCount++;
		}
		else {
			BPNode nodeToAdd = this.find(key);
			nodeToAdd.addChild(key, NIL);
			this.addFix(nodeToAdd);
		}
	}

	public void addFix(BPNode node) {
		if (node.parent != NIL && node.parent.firstKey() > node.firstKey()) {
			node.parent.delChild(node.parent.firstKey());
			node.parent.addChild(node.firstKey(), node);
		}

		if (node.size() >= BPNode.MAX) {
			Map.Entry<Long, Long> entry;
			int i, nodeFirstKey = node.firstKey();
			BPNode left = new BPNode(), right = new BPNode();
			TreeMap<Long, Long> newChildren = new TreeMap<>();

			left.children = new TreeMap<>();
			for (i = 0; i < BPNode.MAX / 2; i++) {
				entry = node.firstEntry();
				left.addChild(entry.getKey(), entry.getValue());

				// updating parents, not "node" anymore, but "left"
				if (entry.getValue() != null) entry.getValue().parent = left;
				node.delChild(entry.getKey());
			}

			right.children = new TreeMap<>();
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
