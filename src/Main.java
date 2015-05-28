import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BPTree t = new BPTree(1);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(11);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(10);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(9);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(8);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(7);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(2);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(3);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(4);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(5);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
		t.add(6);
		System.out.println(t.root);
		t.graph();
		System.out.println("************************************");
	}
}
