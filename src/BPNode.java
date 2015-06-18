import java.util.*;

class BPNode {
	public long count;
	public long parent, index;
	public static final long MAX = 6;
	public TreeMap<Long, Long> children;

	public BPNode() {
		this.count = 0;
		this.index = -1;
		this.parent = -1;

		this.children = null;
	}

	public BPNode(long key, long index) {
		this.count = 0;
		this.parent = -1;
		this.index = index;

		this.children = new TreeMap<>();
		this.addChild(key, -1);
	}

	public long size() {
		return this.children.size();
	}

	public BPNode get(long index) {
		return this.children.get(index);
	}

	public BPNode find(long key) {
		Map.Entry<Long, Long> entry = this.floorEntry(key);

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

	public boolean containsKey(long key) {
		return this.children.containsKey(key);
	}

	public Map.Entry<Long, Long> firstEntry() {
		return (Map.Entry<Long, Long>)this.children.firstEntry();
	}

	public Map.Entry<Long, Long> lastEntry() {
		return (Map.Entry<Long, Long>)this.children.lastEntry();
	}

	public Map.Entry<Long, Long> floorEntry(long key) {
		return (Map.Entry<Long, Long>)this.children.floorEntry(key);
	}

	public Long firstKey() {
		return this.children.firstKey();
	}

	public Long lastKey() {
		return this.children.lastKey();
	}

	public long index() {
		return 0;
	}

	public void addChild(long key, BPNode child) {
		if (this.children == null) {
			this.children = new TreeMap<>();
		}
		if (!this.containsKey(key)) {
			this.children.put(key, child);
			this.count++;
		}
	}

	public void delChild(long key) {
		this.children.remove(key);
	}

	public void graph() {
		for (Map.Entry<Long, Long> entry: this.children.entrySet()) {
			if (entry.getValue() != null) {
				System.out.prlongln("\t\"" + this.children.keySet().toString() +
									"\" -> \"" + entry.getValue().toString() +
									"\" [label = \"" + entry.getKey() + "\"];");

				entry.getValue().graph();
			}
			else {
				System.out.prlongln("\t\"" + this.children.keySet().toString() + "\" -> \"null\" [label = \"" + entry.getKey() + "\"];");
			}
		}
	}

	public String toString() {
		return this.children.keySet().toString();
	}
}
