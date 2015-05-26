class KeyNode {
	public Integer key;
	public BPNode node;
	
	public KeyNode(int key, BPNode node) {
		this.key = key;
		this.node = node;
	}
	
	public String toString() {
		return this.key.toString();
	}
}
