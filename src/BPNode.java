import java.util.*;

class BPNode {
	public int count;
	public BPNode parent;
	public static final int MAX = 6;
	// public static final int MAX = 340;
	public TreeMap<Integer, BPNode> children;

	public BPNode() {
		this.count = 0;
		this.parent = null;

		this.children = null;
	}

	public BPNode(int key) {
		this.count = 0;
		this.parent = null;

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
		Map.Entry<Integer, BPNode> entry = this.floorEntry(key);

		if (entry != null) {
			if (entry.getValue() != null) return entry.getValue().find(key);
			else return this;
		}
		else {
			if (this.size() == 0) return this;
			else {
				entry = this.firstEntry();
				if (entry.getValue() == null) return this;
				else return entry.getValue().find(key);
			}
		}
	}

	public boolean containsKey(int key) {
		return this.children.containsKey(key);
	}

	public Map.Entry<Integer, BPNode> firstEntry() {
		return (Map.Entry<Integer, BPNode>)this.children.firstEntry();
	}

	public Map.Entry<Integer, BPNode> lastEntry() {
		return (Map.Entry<Integer, BPNode>)this.children.lastEntry();
	}

	public Map.Entry<Integer, BPNode> floorEntry(int key) {
		return (Map.Entry<Integer, BPNode>)this.children.floorEntry(key);
	}

	public Integer firstKey() {
		return this.children.firstKey();
	}

	public Integer lastKey() {
		return this.children.lastKey();
	}

	public int index() {
		return 0;
	}

	public void addChild(int key, BPNode child) {
		if (this.children == null) {
			this.children = new TreeMap<Integer, BPNode>();
		}
		if (!this.containsKey(key)) {
			this.children.put(key, child);
			this.count++;
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
