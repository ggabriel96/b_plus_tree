import java.io.*;
import java.nio.*;
import java.util.*;

class DiskWriter {
	private String filename;
	private static final int SIZE = (BPNode.MAX + 1) * 4;
	// private static final int SIZE = BPNode.MAX * 8 + 16; // 1372 bytes

	public DiskWriter(String filename) {
		 this.filename = filename;
	}

	private byte[] nodeToBytes(BPNode node) {
		int i;
		BPNode child;
		ByteBuffer b = ByteBuffer.allocate(SIZE);
		b.order(ByteOrder.LITTLE_ENDIAN);

		if (node.parent != null) b.putInt(node.parent.index()); // should be the index
		else b.putInt(-1);

		for (i = 0; i < BPNode.MAX; i++) {
			child = node.get(i);

			if (child != null) {
				b.putInt(child.index()); // should be the index
			}
			else {
				b.putInt(-1);
			}
		}

		return b.array();
	}

	public void write(BPNode node) throws IOException {
		byte[] bytes = this.nodeToBytes(node);
		RandomAccessFile raf = new RandomAccessFile(this.filename, "rw");

		raf.seek(node.index() * SIZE);
		raf.write(bytes);
		raf.close();

		// node.touch(); // mark saved
	}

	public BPNode read(int i) throws IOException {
		byte[] bytes = new byte[SIZE];
		RandomAccessFile raf = new RandomAccessFile(this.filename, "rw");

		raf.seek(i * SIZE);
		raf.read(bytes);
		raf.close();

		return this.bytesToNode(i, bytes);
	}

	public BPNode bytesToNode(int i, byte[] bytes) throws IOException {
		int j, index;
		BPNode node = null;
		ByteBuffer b = ByteBuffer.wrap(bytes);
		b.order(ByteOrder.LITTLE_ENDIAN);
		b.rewind();

		// node = new BPNode(i, b.getInt());
		node = new BPNode(i);
		node.parent = this.read(b.getInt());

		for (j = 0; j < BPNode.MAX; j++) {
			index = b.getInt();

			if (index != -1) {
				// node.addChild(index, b.getInt());
				node.addChild(index, null);
			}
			else break;
		}

		return node;
	}

	public long fileLength() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(this.filename, "rw");
		long length = raf.length();
		raf.close();
		return length;
	}
}
