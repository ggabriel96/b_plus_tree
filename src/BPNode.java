import java.util.*;

class BPNode {
	int i; // "count on disk"
	boolean saved; // SET TO FALSE WHENEVER THE NODE IS CHANGED, TRUE WHEN SAVED
	TreeMap<Integer, Key> children; // index and child, 340 * 4 = 1360 bytes
	BPNode left, parent, right; // 12 bytes
	
	public BPNode(int k, int i) {
		this.i = i;
		this.saved = false;
		this.left = this.parent = this.right = null;
		
		this.children = new TreeMap<Integer, Key>();
		this.children.put(i, new Key(k, null));
	}
}
