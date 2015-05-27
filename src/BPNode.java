import java.util.*;

class BPNode {
	public int count;
	public static final int MAX = 340;
	public TreeMap<Integer, BPNode> children;
	public BPNode left, parent, right;

	public BPNode(int key) {
		this.count = 0;
		this.left = this.parent = this.right = null;

		this.children = new TreeMap<Integer, BPNode>();
		this.addChild(key);
	}

	public BPNode get(int index) {
		return this.children.get(index);
	}

	public void addChild(int key) {
		this.children.put(key, null);
		this.count++;
		// if (this.count > BPNode.MAX) {
		// }
	}

	public void delChild(int key) {
		this.children.remove(key);
	}

	public String toString() {
		return this.children.keySet().toString();
	}

}
