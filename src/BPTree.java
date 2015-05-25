import java.io.*;
import java.util.*;

class BPTree {
	BPNode root;
	DiskWriter dw;
	private int count = 0;
	public static final int MAX = 340;
	
	public BPTree(int key) throws IOException {
		this.root = new BPNode(this.count, key);
		this.dw = new DiskWriter("bp.tree");
		this.dw.write(this.root);
		System.out.println(this.dw.read(0));
		//System.out.println(this.dw.read(1));
		//System.out.println(this.dw.read(2));
		//System.out.println(this.dw.read(3));
		this.count++;
	}
	
	// returns the first node which 
	public BPNode find(int key) throws IOException {
		int i, j;
		Key child = null ;
		BPNode node = null;
		
		i = 0;
		while (i * 8 < this.dw.fileLength()) {
			node = this.dw.read(i);
			
			for (j = 0; j < BPTree.MAX; j++) {
				child = node.getChild(j);
				
				if (child != null) {
					if (child.i == key) { // already exists
						i = MAX + 4;
						node = null;
					}
					else if (key < child.i) { // advance
						i++;
					}
					else if (key > child.i) { // found
						// node = child.c; // ?
						i = MAX + 4;
						break;
					}
				}
				// else break; // ?
			}
		}
		
		return node;
	}
	
	public boolean add(int key) throws IOException {
		if (this.count >= MAX) return false;
		
		BPNode node = this.find(key);
		System.out.println(node);
		node.addChild(node, key);
		// use insert method of the TreeMap, just
		// gotta take care of the size of the node
		return true;
	}
}
