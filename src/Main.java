import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BPTree t = new BPTree(1);
		t.add(11);
		t.add(10);
		t.add(9);
		t.add(8);
		t.add(7);
		t.add(2);
		t.add(3);
		t.add(4);
		t.add(5);
		t.add(6);
		System.out.println(t.root);
		System.out.println("************************************");
		t.root.print();
		System.out.println("\\/");
		for (Map.Entry<Integer, BPNode> entry: t.root.children.entrySet()) {
			if (entry.getValue() != null) entry.getValue().print();
		}
		System.out.println();
	}
}
