import java.io.*;
import java.util.*;

class BPTree {
	BPNode root;
	DiskWriter dw;
	private static int count = 0;
	public static final int MAX = 340;
	
	public BPTree(int key) throws IOException {
		this.root = new BPNode(key, count);
		this.dw = new DiskWriter("bp.tree");
		dw.write(this.root);
		count++;
	}
}
