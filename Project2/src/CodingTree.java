import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CodingTree {
	
	public Map<Character, String> codes = new HashMap<Character, String>();
	public String bits;
	private PriorityQueue<CharacterNode> myInput;
	private CharacterNode myRoot;

	public CodingTree(String Message) {
		codes = new HashMap<Character, String>();
		bits = "";
		myInput = new PriorityQueue<CharacterNode>();
		generateFrequencies(Message);
		huffmanTree(myInput);
		compressMessage(Message);
	}
	
	/**
	 * Reads in the input to get the frequency count of all the
	 * characters, then adds them to the PQ.
	 * @param message Input values.
	 */
	private void generateFrequencies(String message) {
		HashMap<Character, Integer> input = new HashMap<Character, Integer>();
		for (char eachChar : message.toCharArray()) {
			if (input.containsKey(eachChar)) {
				input.put(eachChar, input.get(eachChar) + 1);
			} else {
				input.put(eachChar, 1);
			}
		}
		inputNodes(input);
	}
	
	private void inputNodes(Map<Character, Integer> freq) {
		for (char eachKey : freq.keySet()) {
			myInput.add(new CharacterNode(eachKey, freq.get(eachKey)));
		}
		//System.out.println(myInput);	
	}
	
	private void huffmanTree(PriorityQueue<CharacterNode> input) {
		while(input.size() > 1) {
			input.add(new CharacterNode(input.poll(), input.poll()));
		}
		//System.out.println(myInput);
		myRoot = input.peek();
		generateBinary(myRoot, 2, "");
		outputCodes(myRoot);
		printHuffmanTree(myRoot);
	}
	
	private void generateBinary(CharacterNode key, int num, String bin) { 
		int temp = num;
		String tempBin = bin;
		
		if(key == null){
			return;
		}
		
		if(key.getLeftChild() == null && key.getRightChild() == null  && num == 2) { 
			key.setBinary("1");
		} else {			
			if (num == 0) {
				tempBin += "0";
			} else if (num == 1) { 
				tempBin += "1";
			}
			key.setBinary(tempBin);
			 
			generateBinary(key.getLeftChild(), 0, tempBin);
			
			generateBinary(key.getRightChild(), 1, tempBin);
		}
	}
	
	//Added 2/19 - Adds codes + character to the CODES hashmap
	private void outputCodes(CharacterNode key){ 
		if (key != null) { 
			if (key.getLetter() != '\0'){
				//System.out.println("My key: " + key.getLetter() + " My value: " + key.getBinary());
				codes.put(key.getLetter(), key.getBinary());
				//System.out.println("Is the map empty?  " + codes.isEmpty());
			}
			outputCodes(key.getLeftChild());
			outputCodes(key.getRightChild());
		}
		//System.out.println("Is the map empty?  " + codes.isEmpty());
	}
	
	public void printHuffmanTree(CharacterNode key) {
		if (key != null) {
			//System.out.println(key);
			printHuffmanTree(key.getLeftChild());
			printHuffmanTree(key.getRightChild());
		}
	}
	
	public void compressMessage(String message) {
		StringBuilder buffer = new StringBuilder();
		for (char eachChar : message.toCharArray()) {
			buffer.append(codes.get(eachChar));
		}
		buffer.trimToSize();
		bits = buffer.toString();
		
	}
	
	public String getEncoding() {
		return bits;
	}
	/**
	 * For Testing tree operations.
	 * @param args
	 */
	public static void main(String[] args) {
		CodingTree test = new CodingTree("ANNA HAS A BANANA IN A BANDANA");

	}

}
