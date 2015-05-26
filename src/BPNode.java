import java.util.*;

class BPNode {
	public int count;
	public static final int MAX = 340;
	public TreeMap<Integer, KeyNode> children;
	public BPNode left, parent, right;
	
	public BPNode(int key) {
		this.count = 0;
		this.left = this.parent = this.right = null;
		
		this.children = new TreeMap<Integer, KeyNode>();
		this.addChild(key);
	}
	
	public KeyNode get(int index) {
		return this.children.get(index);
	}
	
	public void addChild(int key) {
		this.children.put(this.count, new KeyNode(key, null));
		this.count++;
	}

	public KeyNode[] childrenArr() {
		KeyNode[] nodes = new KeyNode[children.size()];
		return this.children.values().toArray(nodes);
	}
	
	public String toString() {
		String s = "{";
		KeyNode[] nodes = this.childrenArr();
		
		for (int i = 0; i < nodes.length; i++) {
			s += nodes[i].toString();
			
			if (i + 1 < nodes.length) s += ", ";
		}
		
		s += "}";
		
		return s;
	}
	
}
