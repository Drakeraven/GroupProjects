
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
	
	 //TODO Left this for you Cassie if you need to test shit.
	public static void main(String[] theArgs) {
		MyStack<String> testStack = new MyStack<String>();
		
		testStack.push("put on first.");
		testStack.push("put on second.");
		testStack.pop();
		testStack.push("Should be left if pop is fine.");
		System.out.println(testStack);
		System.out.println(testStack.topOfStack.myObject.toString());
		System.out.println(testStack.size);
		if(testStack.isEmpty())
				System.out.println("this bich empty yeet"); 
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
