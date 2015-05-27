import java.util.*;

class BPNode {
	public int count;
	// public static final int MAX = 340;
	public static final int MAX = 4;
	public TreeMap<Integer, BPNode> children;
	public BPNode left, parent, right;

	public BPNode() {
		this.count = 0;
		this.left = this.parent = this.right = null;

		this.children = null;
	}

	public BPNode(int key) {
		this.count = 0;
		this.left = this.parent = this.right = null;

		this.children = new TreeMap<Integer, BPNode>();
		this.addChild(key, null);
	}

	public BPNode get(int index) {
		return this.children.get(index);
	}

	public void addChild(int key, BPNode child) {
		if (this.children == null) {
			this.children = new TreeMap<Integer, BPNode>();
		}
		this.children.put(key, child);
		this.count++;
	}

	public void addFix() {
		if (this.children.size() >= BPNode.MAX) {
			BPNode newNode = this;
			BPNode left = new BPNode(), right = new BPNode();

			left.children = new TreeMap<Integer, BPNode>();
			left.children.putAll(this.children.headMap(this.children.lastKey() + ((this.children.firstKey() - this.children.lastKey()) / 2), false));

			right.children = new TreeMap<Integer, BPNode>();
			right.children.putAll(this.children.tailMap(this.children.lastKey() + ((this.children.firstKey() - this.children.lastKey()) / 2), true));

			System.out.println("Left: " + left);
			System.out.println("Right: " + right);
		}
	}

	public void delChild(int key) {
		this.children.remove(key);
	}

	public String toString() {
		return this.children.keySet().toString();
	}

}
