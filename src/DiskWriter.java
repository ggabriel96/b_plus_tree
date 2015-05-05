import java.io.*;
import java.nio.*;
import java.util.*;

class DiskWriter {
	private String filename;
	private static final int SIZE = BPTree.MAX * 8 + 12; // 1372 bytes
	
	public DiskWriter(String filename) {
		 this.filename = filename;
	}
	
	private byte[] nodeToBytes(BPNode node) {
		int i;
		Key k;
		ByteBuffer b = ByteBuffer.allocate(SIZE);
		b.order(ByteOrder.LITTLE_ENDIAN);
		
		for (i = 0; i < BPTree.MAX; i++) {
			k = node.children.get(i); 
			if (k != null) {
				b.putInt(k.i);
				
				if (k.c != null) b.putInt(k.c.i);
				else b.putInt(-1);
			}
			else b.putLong(-1);
		}
		
		if (node.left != null) b.putInt(node.left.i);
		else b.putInt(-1);
		
		if (node.parent != null) b.putInt(node.parent.i);
		else b.putInt(-1);
		
		if (node.right != null) b.putInt(node.right.i);
		else b.putInt(-1);
		
		return b.array();
	}
	
	public void write(BPNode node) throws IOException {
		byte[] bytes = this.nodeToBytes(node);
		RandomAccessFile raf = new RandomAccessFile(this.filename, "rw");		
		
		raf.seek(node.i * SIZE);
		raf.write(bytes);
		/*
		children = new Key[node.key.size()];
		children = node.key.values().toArray(children);
		for (int i = 0; i < BPTree.MAX; i++) {
			k = children.get(i);
			if (k != null) {
				raf.writeInt(k.i);
			
				if (k.c != null) raf.writeInt(k.c.i);
				else raf.writeChars("    "); // 4 bytes
			}
			else raf.writeChars("        "); // 8 bytes
		}
		if (node.left != null) raf.writeInt(node.left.i);
		else raf.writeChars("    "); // 4 bytes
		
		if (node.parent != null) raf.writeInt(node.parent.i);
		else raf.writeChars("    "); // 4 bytes
		
		if (node.right != null) raf.writeInt(node.right.i);
		else raf.writeChars("    "); // 4 bytes
		*/
		
		node.saved = true;
		raf.close();
	}
}
