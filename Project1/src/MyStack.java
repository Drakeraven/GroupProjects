/**
 * Stephanie Day
 * Cassie Renz
 * TCSS 342 - Winter 2017
 * Based off textbook examples
 * of listNode, listStack by
 * Mark Allen Weiss
 */
public class MyStack <T>{
	
	/** The Linked List Node at the top of the stack.*/
	private Nodes<T> topOfStack;
	
	/** How many elements are in the stack.*/
	private int size; 
	
	MyStack () {
		topOfStack = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return topOfStack == null;
	}
	
	public void push (T item) {
		topOfStack = new Nodes<T>(item, topOfStack);
		size++;
	}
	
	public T pop() {
		
		T temp = topOfStack.myObject;
		topOfStack = topOfStack.myLink;
		size--;
		return temp;
	}
	
	public T peek() {
		return topOfStack.myObject;
	}
	
	public int size() {
		return size;
	}
	
	 @Override
	public String toString() {
		 
		 Nodes<T> iterate = topOfStack.myLink;
		 String output = "[" + topOfStack.myObject.toString();
		 
		 while (iterate != null) {
			 output += ", " + iterate.myObject.toString();
			 iterate = iterate.myLink;
		 }
		 output += "]";
		 return output;
	}
	 
	private class Nodes<AnyType> { 
		
		/**The Element being held in this list node */
		private AnyType myObject;
		
		/** Reference to the Node below this one.*/
		private Nodes<AnyType> myLink; 
		
		
		public Nodes(AnyType object) {			
			myObject = object;
		}
		
		public Nodes(AnyType object, Nodes<AnyType> link) {
			this(object);
			myLink = link;
		}
	 }
}
