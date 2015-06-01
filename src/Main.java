import java.io.*;
import java.util.*;

class Main {
	public static final int ADD_COUNT = 100;
	public static final int SEARCH_COUNT = 5;
	// public static final long MAX = 4294967296L; // 2^32
	public static final int MAX = 2147483647; // 2^31 - 1

	public static void main(String[] args) throws IOException {
		Random prn = new Random(System.nanoTime());
		BPTree t = new BPTree();
		
		for (int i = 0; i < 50; i++) {
			//t.add(i);
			t.add(prn.nextInt(MAX) % 100);
			//t.graph();
		}
		t.graph();
	}
}
