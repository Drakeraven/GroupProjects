import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CodingTree {
	
	public Map<Character, String> codes;
	public String bits;
	private PriorityQueue<CharacterNode> myInput;
	private CharacterNode myRoot;

	public CodingTree(String Message) {
		codes = new HashMap<Character, String>();
		bits = null;
		myInput = new PriorityQueue<CharacterNode>();
		generateFrequencies(Message);
		huffmanTree(myInput);
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
		System.out.println(myInput);	
	}
	
	private void huffmanTree(PriorityQueue<CharacterNode> input) {
		while(input.size() > 1) {
			input.add(new CharacterNode(input.poll(), input.poll()));
		}
		System.out.println(myInput);
		myRoot = input.peek();
		printHuffmanTree(myRoot);
	}
	
	public void printHuffmanTree(CharacterNode key) {
		if (key != null) {
			System.out.println(key);
			printHuffmanTree(key.getLeftChild());
			printHuffmanTree(key.getRightChild());
		}
	}
	
	/**
	 * For Testing.
	 * @param args
	 */
	public static void main(String[] args) {
		CodingTree test = new CodingTree("ANNA HAS A BANANA IN A BANDANA");
		
	}

}
