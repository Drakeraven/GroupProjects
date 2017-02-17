import java.util.ArrayList;

public class MyPriorityQueue{
 private ArrayList<CharacterNode> myDataStructure;
 
	public MyPriorityQueue() {
		// TODO Fix!!
	}

	public CharacterNode deleteMin(CharacterNode theNode) { //TODO nodes! 
		CharacterNode itemTemp = myDataStructure.get(1);
		myDataStructure.set(1, myDataStructure.get(myDataStructure.size() -1));
		percolateDown(myDataStructure.get(1));
		return itemTemp;
	}
	
	private void percolateDown(CharacterNode item) {
		//TODO recursive 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
