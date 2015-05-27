import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BPNode[] children;
		BPTree bpt = new BPTree(1);
		bpt.add(2);
		bpt.add(3);
		bpt.add(4);
		bpt.add(5);
		bpt.add(6);
		bpt.add(7);
		System.out.println(bpt.root);

		bpt.del(1);
		bpt.del(4);
		bpt.del(6);
		System.out.println(bpt.root);
	}
}
