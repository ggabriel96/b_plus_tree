import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BPTree t = new BPTree(1);
		t.add(2);
		t.add(3);
		t.add(4);
		t.add(5);
		t.add(6);
		t.add(7);
		System.out.println(t.root);

		t.del(1);
		t.del(4);
		t.del(6);
		System.out.println(t.root);
	}
}
