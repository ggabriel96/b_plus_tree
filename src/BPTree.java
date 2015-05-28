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

	public BPNode find(int key) {
		return this.root.find(key);
	}

	public void add(int key) {
		if (this.root == null) {
			this.root = new BPNode(key);
		}
		else {
			BPNode nodeToAdd = this.find(key);
			nodeToAdd.addChild(key, null);
			nodeToAdd.addFix();
		}
	}

	public void del(int key) {
		if (this.root != null) {
			this.root.delChild(key);
		}
	}

	public void graph() {
        System.out.println("digraph BPTree {");
        this.root.graph();
        System.out.println("}");
    }
}
