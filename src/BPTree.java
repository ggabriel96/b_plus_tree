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
		if (this.root == null) {
			this.root = new BPNode(key);
		}
		else {
			this.root.addChild(key);
		}
	}

	public void del(int key) {
		if (this.root != null) {
			this.root.delChild(key);
		}
	}
}
