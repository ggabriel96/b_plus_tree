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

	public BPNode find(int key) {
		if (this.children.floorEntry(key).getValue() == null) {
			return this;
		}
		else {
			return this.children.floorEntry(key).getValue().find(key);
		}
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
			Integer half = this.children.firstKey() + ((this.children.lastKey() - this.children.firstKey()) / 2);
			TreeMap<Integer, BPNode> newChildren = new TreeMap<Integer, BPNode>();
			BPNode left = new BPNode(), right = new BPNode();

			left.children = new TreeMap<Integer, BPNode>();
			left.children.putAll(this.children.headMap(half, true));

			right.children = new TreeMap<Integer, BPNode>();
			right.children.putAll(this.children.tailMap(half, false));

			newChildren.put(left.children.firstKey(), left);
			newChildren.put(right.children.firstKey(), right);

			this.children = new TreeMap<Integer, BPNode>(newChildren);

			// System.out.println("this.children:");
			// for (Map.Entry<Integer, BPNode> entry: this.children.entrySet()) {
			// 	System.out.println(entry);
			// } System.out.println();
			//
			// System.out.println("this.children[0]:");
			// for (Map.Entry<Integer, BPNode> entry: this.children.get(left.children.firstKey()).children.entrySet()) {
			// 	System.out.println(entry);
			// } System.out.println();
			//
			// System.out.println("this.children[1]:");
			// for (Map.Entry<Integer, BPNode> entry: this.children.get(right.children.firstKey()).children.entrySet()) {
			// 	System.out.println(entry);
			// }
			// System.out.println("-----------------------");
		}
	}

	public void delChild(int key) {
		this.children.remove(key);
	}

	public void graph() {
		for (Map.Entry<Integer, BPNode> entry: this.children.entrySet()) {
			if (entry.getValue() != null) {
				System.out.println("\t\"" + this.children.keySet().toString() +
									"\" -> \"" + entry.getValue().toString() +
									"\" [label = \"" + entry.getKey() + "\"];");

				entry.getValue().graph();
			}
			else {
				System.out.println("\t\"" + this.children.keySet().toString() + "\" -> \"null\" [label = \"" + entry.getKey() + "\"];");
			}
		}
	}

	public String toString() {
		return this.children.keySet().toString();
	}
}
