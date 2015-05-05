import java.util.*;

class BPNode {
	int i; // index, 4 bytes
	private boolean saved; // SET TO FALSE WHENEVER THE NODE IS CHANGED, TRUE WHEN SAVED
	private TreeMap<Integer, Key> children;
	BPNode left, parent, right; // 12 bytes because I'll save their i
	
	public BPNode(int i, int k) {
		this.i = i;
		this.saved = false;
		this.left = this.parent = this.right = null;
		
		this.children = new TreeMap<Integer, Key>();
		this.children.put(i, new Key(k, null));
	}
	
	public Key getChild(int i) {
		return this.children.get(i);
	}
	
	public void addChild(int i, int c) {
		this.children.put(i, new Key(c, null));
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
		s += "[" + this.saved + "]: " + this.i + "\n";
		s += this.children.toString();
		
		return s;
	}
}
