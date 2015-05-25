import java.util.*;

class BPNode {
	private int i; // index, 4 bytes
	private boolean saved; // SET TO FALSE WHENEVER THE NODE IS CHANGED, TRUE WHEN SAVED
	private TreeMap<Integer, Key> children;
	BPNode left, parent, right; // 12 bytes because I'll save their i
	
	public BPNode(int i, int k) {
		this.i = i;
		this.saved = false;
		this.left = this.parent = this.right = null;
		
		// the index must be the smallest key among all mapped values,
		// but the tree key must be an index as if it was an array
		this.children = new TreeMap<Integer, Key>();
		this.addChild(k, new Key(k, null));
	}
	
	public int index() {
		return this.i;
	}
	
	public Key getChild(int i) {
		return this.children.get(i);
	}
	
	public void addChild(int i, int c) {
		this.children.put(this.children.firstKey(), new Key(c, null));
	}
	
	public void setLeft(int i) {
		this.left = null;
	}
	
	public void setParent(int i) {
		this.parent = null;
	}
	
	public void setRight(int i) {
		this.right = null;
	}
	
	public void touch() {
		this.saved = !this.saved;
	}
	
	public String toString() {
		String s;
		
		s = "l: " + (this.left != null ? this.left.i : "nil") + "\n";
		s += "p: " + (this.parent != null ? this.parent.i : "nil") + "\n";
		s += "r: " + (this.right != null ? this.right.i : "nil") + "\n";
		s += "i: " + this.i + " [" + this.saved + "]\n";
		s += this.children.toString();
		
		return s;
	}
}
