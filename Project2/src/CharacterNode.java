public class CharacterNode implements Comparable<CharacterNode>{
	
	private final char myLetter;
	private int myCount = 0;
	private String myBinary;
	private CharacterNode myLeftChild;
	private CharacterNode myRightChild;
	
	/**
	 * Creates a Null letter node for the Huffman Tree.
	 * @param theLeftChild What will be the left Child (A letter).
	 * @param theRightChild What will be the Right Child (A letter).
	 */
	public CharacterNode(CharacterNode theLeftChild, CharacterNode theRightChild) {
		myLetter = '\0';
		myLeftChild = theLeftChild;
		myRightChild = theRightChild;
		myCount = theLeftChild.getCount() + theRightChild.getCount();
		myBinary = null;
	}
	
	/**
	 * Creates a Node with its frequency.
	 * @param theLetter Letter of this node.
	 * @param theWeight Frequency in the file of this letter.
	 */
	public CharacterNode(char theLetter, int theWeight) {
		myLetter = theLetter;
		myCount = theWeight;
		myLeftChild = null;
		myRightChild = null;
	}
	
	public int getCount() {
		return myCount;
	}
	
	/**
	 * Called to increase the frequency of this letter.
	 */
	public void incCount() {
		myCount++;
	}
	
	/**
	 * Builds the Binary code of the particular node.
	 * @param bit Bit value to be tacked on to the node, 0 or 1. 
	 */
	public void setBinary(char bit) {
		myBinary += bit;
	}
	
	/**
	 * Returns the binary encoding of this node/letter.
	 * @return
	 */
	public String getBinary() {
		return myBinary;
	}
	
	public char getLetter() {
		return myLetter;
	}
	
	public CharacterNode getLeftChild() {
		return myLeftChild;
	}
	
	public CharacterNode getRightChild() {
		return myRightChild;
	}
	
	//TODO may not need.
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other != null && this.getClass().equals(other.getClass())) {
			CharacterNode theOther = (CharacterNode) other;
			result = this.getLetter() == theOther.getLetter();
		}
		return result;
	}

	@Override
	public int compareTo(CharacterNode other) {
		if (this.getCount() < other.getCount()) {
			return -1;
		} else if (this.getCount() > other.getCount()) {
			return 1;
		}
		return 0;
		
	}
	//TODO: Maybe do String Builder for savings later.
	@Override
	public String toString() {
		String Output = "";
		Output += myLetter;
		Output += ":";
		Output += myCount;
		Output += ": ";
		Output += getBinary();
		
		return Output;
	}
}

