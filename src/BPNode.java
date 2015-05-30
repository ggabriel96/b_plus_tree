import java.util.*;

class BPNode {
	public int count;
	// public static final int MAX = 340;
	public static final int MAX = 6;
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

	public int size() {
		return this.children.size();
	}

	public BPNode get(int index) {
		return this.children.get(index);
	}

	public BPNode find(int key) {
		if (this.children.floorEntry(key) == null || this.children.floorEntry(key).getValue() == null) {
			return this;
		}
		else {
			return this.children.floorEntry(key).getValue().find(key);
		}
	}

	public Map.Entry<Integer, BPNode> firstEntry() {
		return (Map.Entry<Integer, BPNode>)this.children.firstEntry();
	}

	public Map.Entry<Integer, BPNode> lastEntry() {
		return (Map.Entry<Integer, BPNode>)this.children.lastEntry();
	}

	public Integer firstKey() {
		return this.children.firstKey();
	}

	public Integer lastKey() {
		return this.children.lastKey();
	}

	public void addChild(int key, BPNode child) {
		if (this.children == null) {
			this.children = new TreeMap<Integer, BPNode>();
		}
		this.children.put(key, child);
		this.count++;
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
