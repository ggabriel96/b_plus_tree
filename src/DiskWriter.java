import java.io.*;
import java.nio.*;
import java.util.*;

class DiskWriter {
	private String filename;
	private static final int SIZE = BPNode.MAX * 8 + 16; // 1372 bytes

	public DiskWriter(String filename) {
		 this.filename = filename;
	}

	// private byte[] nodeToBytes(BPNode node) {
	// 	int i;
	// 	Key k;
	// 	ByteBuffer b = ByteBuffer.allocate(SIZE);
	// 	b.order(ByteOrder.LITTLE_ENDIAN);
	//
	// 	if (node.left != null) b.putInt(node.left.index());
	// 	else b.putInt(-1);
	//
	// 	if (node.parent != null) b.putInt(node.parent.index());
	// 	else b.putInt(-1);
	//
	// 	if (node.right != null) b.putInt(node.right.index());
	// 	else b.putInt(-1);
	//
	// 	for (i = 0; i < BPTree.MAX; i++) {
	// 		k = node.getChild(i);
	// 		if (k != null) {
	// 			b.putInt(k.i);
	//
	// 			if (k.c != null) b.putInt(k.c.index());
	// 			else b.putInt(-1);
	// 		}
	// 		else {
	// 			b.putInt(-1);
	// 			b.putInt(-1);
	// 		}
	// 	}
	//
	// 	return b.array();
	// }
	//
	// public void write(BPNode node) throws IOException {
	// 	byte[] bytes = this.nodeToBytes(node);
	// 	RandomAccessFile raf = new RandomAccessFile(this.filename, "rw");
	//
	// 	raf.seek(node.index() * SIZE);
	// 	raf.write(bytes);
	// 	raf.close();
	//
	// 	node.touch();
	// }
	//
	// public BPNode read(int i) throws IOException {
	// 	byte[] bytes = new byte[SIZE];
	// 	RandomAccessFile raf = new RandomAccessFile(this.filename, "rw");
	//
	// 	raf.seek(i * SIZE);
	// 	raf.read(bytes);
	// 	raf.close();
	//
	// 	return this.bytesToNode(i, bytes);
	// }
	//
	// public BPNode bytesToNode(int i, byte[] bytes) {
	// 	int j, index;
	// 	BPNode node = null;
	// 	ByteBuffer b = ByteBuffer.wrap(bytes);
	// 	b.order(ByteOrder.LITTLE_ENDIAN);
	// 	b.rewind();
	//
	// 	node = new BPNode(i, b.getInt());
	//
	// 	node.setLeft(b.getInt());
	// 	node.setParent(b.getInt());
	// 	node.setRight(b.getInt());
	//
	// 	for (j = 0; j < BPTree.MAX; j++) {
	// 		index = b.getInt();
	// 		if (index != -1) {
	// 			node.addChild(index, b.getInt());
	// 		}
	// 		// else b.getInt();
	// 		else break;
	// 	}
	//
	// 	return node;
	// }
	//
	// public long fileLength() throws IOException {
	// 	RandomAccessFile raf = new RandomAccessFile(this.filename, "rw");
	// 	long length = raf.length();
	// 	raf.close();
	// 	return length;
	// }
}
