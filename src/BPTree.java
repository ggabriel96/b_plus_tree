import java.io.*;
import java.util.*;

class BPTree {
	public BPNode root;
	
	public BPTree() {
		this.root = null;
	}
	
	public BPTree(int key) {
		this.root = new BPNode(key);
	}
	
	public void add(int key) {
		this.root.addChild(key);
	}
}
