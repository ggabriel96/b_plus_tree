import java.io.*;
import java.util.*;

class BPTree {
	BPNode root;
	DiskWriter dw;
	private static int count = 0;
	public static final int MAX = 340;
	
	public BPTree(int key) throws IOException {
		this.root = new BPNode(count, key);
		this.dw = new DiskWriter("bp.tree");
		this.dw.write(this.root);
		System.out.println(this.dw.read(0));
		count++;
	}
	
	public BPNode find(int key) throws IOException {
		int i, j;
		Key child = null ;
		BPNode node = null;
		
		for (i = 0; i * 4 < this.dw.fileLength(); i++) {
			node = this.dw.read(i);
			
			for (j = 0; j < BPTree.MAX; j++) {
				child = node.getChild(j);
				
				if (child != null) {
					// insert method of the TreeMap! \o/
					// just take care of the size of the node
				}
			}
		}
		
		return node;
	}
	
	public boolean add(int key) throws IOException {
		BPNode node = this.find(key);
		return false;
	}
}
