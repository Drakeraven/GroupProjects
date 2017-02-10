import java.util.ArrayList;

public class MyPriorityQueue<T> {
 private ArrayList<T> myDataStructure;
 
	public MyPriorityQueue() {
		// TODO Auto-generated constructor stub
	}

	public T deleteMin(T item) { //TODO nodes! 
		T itemTemp = myDataStructure.get(1);
		myDataStructure.set(1, myDataStructure.get(myDataStructure.size() -1));
		percolateDown(myDataStructure.get(1));
		return itemTemp;
	}
	
	private void percolateDown(T item) {
		//TODO recursive 
		if (myDataStructure.get(myDataStructure.indexOf(item) * 2).compareTo(item) < 0) {
			T itemTemp = item;
			myDataStructure.set(myDataStructure.indexOf(item) * 2, itemTemp);
			myDataStructure.set(myDataStructure.indexOf(item), myDataStructure.get(myDataStructure.indexOf(item) * 2));
		
		} // left child
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
