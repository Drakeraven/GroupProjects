public class Burger {
	
	private MyStack<String> myBurger; 
	
	private MyStack<String> myAux; 
	
	private String myPatty = "Beef"; 
	
	private String[] baronBurger = {"Pickle", "Top Bun", 
			"Mayonnaise", "Baron Sauce", "Lettuce", "Tomato", "Onions",
			"Pepperjack", "Mozzarella", "Cheddar", "Beef", "Mushrooms", 
			"Mustard", "Ketchup", "Bottom Bun"};
	
	private String[] myCheese = {"Pepperjack", "Mozzarella", "Cheddar"}; 
	
	private String[] mySauce = {"Mayonnaise", "Baron Sauce", "Mustard", "Ketchup"};
	
	private String[] myVeggies = {"Pickle", "Lettuce", "Tomato", "Onion", "Mushrooms"};
	
	public Burger(boolean theWorks) { 
		myAux = new MyStack<String>();
		myBurger = new MyStack<String>(); 
		
		if(theWorks){
			for(int i = baronBurger.length - 1; i >= 0; i--) {
				myBurger.push(baronBurger[i]);
			}
		} else { 
			myBurger.push("Bottom Bun");
			myBurger.push("Beef");
			myBurger.push("Top Bun");
			myBurger.push("Pickle");
		}
			
	}
	
	public void addPatty(){
		//iterate through stack find bottom patty push patty 
		boolean flag = true;
		while (!myBurger.isEmpty()) {
			if(myBurger.peek().equalsIgnoreCase("Beef") && flag) {
				myAux.push(myBurger.pop());
				myAux.push("Beef");
				flag = false;
			} else { 
				myAux.push(myBurger.pop());
			}
		}
		reset();
	}
	
	public void changePatties(String pattyType){
		//iterate through stack, find patty, pop, push new patty
		myPatty = pattyType;
		while (!myBurger.isEmpty()) {
			if(myBurger.peek().equalsIgnoreCase("Beef")) {
				myBurger.pop();
				myAux.push(pattyType);
			} else { 
				myAux.push(myBurger.pop());
			}
		}
		reset();	
	}
	
	public void removePatty(){ 
		//iterate through stack, find patty, remove patty 
		//TODO check how many patties, if 2+ remove
		while (!myBurger.isEmpty()){
			if(myBurger.peek().equalsIgnoreCase("Beef")) {
				myBurger.pop();
			} else {
				myAux.push(myBurger.pop());
			}
		}
		reset();
	}
	
	public void addCategory(String type) {
		//for items in category, send to addIngredient
		String[] category = {""}; 
		
		switch(type) {
			case "cheese": category = myCheese; 
				break;
			case "veggies": category = myVeggies; 
				break;
			case "sauces": category = mySauce;
		}
		
		for (int i = 0; i < category.length; i++){
			addIngredient(category[i]);
		}
		
	}
	
	public void removeCategory(String type) {
		//for items in category sent to removeIngredient
		String[] category = {""}; 
		
		switch(type) {
			case "cheese": category = myCheese; 
				break;
			case "veggies": category = myVeggies; 
				break;
			case "sauces": category = mySauce;
		}
		
		System.out.println(category.toString());
		
		for (int i = 0; i < category.length; i++){
			System.out.println("removeCat item sent: " + category[i]);
			removeIngredient(category[i]);
		}
	}
	
	
	public void addIngredient(String type) {
		//find the item after the ingredient in the array
		//push ingredient to the top 
		System.out.println(type + "- add ingredient sent");
		int location = 0; 
		boolean flag = false;
		for(int i = 0; i < baronBurger.length; i++) {
			if (baronBurger[i].equalsIgnoreCase(type)) {
				location = i + 1;
			}
		}
		int checkSpot = 0;
		System.out.println(baronBurger[location - 1] + " Item Above/to be placed");
		System.out.println(baronBurger[location] + " Item below");
		while (location + checkSpot < baronBurger.length || flag) {   //CHANGED BOUNDS, STILL HITTING OUT OF BOUNDS EXCEPTION
			while (!myBurger.isEmpty()) {
				System.out.println(baronBurger[location + checkSpot]);   //PUTS ONION AFTER FIRST BEEF??? FOR SOME REASON
				if (myBurger.peek().equalsIgnoreCase(baronBurger[location + checkSpot])) {
					myBurger.push(type);
					System.out.println("Hello" + myBurger);
					flag = true; 								//FLAG DOESN'T END THE LOOP, CONTINUES LOOPING AFTER PUSHING ONION
				} else {
					System.out.println(myBurger);
					myAux.push(myBurger.pop());
					//System.out.println(myAux);
				}	
				System.out.println(location + checkSpot);
				checkSpot++;
			}
			//checkSpot++;
			//System.out.println(myBurger);
			reset();
		}
		//System.out.println(myBurger);
		System.out.println("reset");
		reset();
	}
	
	//REWRITING IN AN ATTEMPT TO UNDERESTAN WHATS GOING ON.
	public void test (String type) {
		//want to figure out what item is adjacent to the TYPE in order
		//to put it in the right place
		
		int location = 0; 
		
		//First, figure out where the item is in the complete list 
		for (int i = 0; i < baronBurger.length; i++) { 
			if (type.equalsIgnoreCase(baronBurger[i])) {
				location = i; 
			}
		}
		
		//iterate over the stack, checking if the item below it exists in the stack
		//if not, reset. increment the baronBurger location, repeat above
		int add = 1; 
		boolean flag = false; 
		while (location + add < baronBurger.length) {
			while (!myBurger.isEmpty() || flag){
				if (myBurger.peek().equalsIgnoreCase(baronBurger[location + add])){
					myBurger.push(type);
					flag = true; 
				} else { 
					myAux.push(myBurger.pop());
				}
				add++; 
			}
			//add++;
			//flag = false;
		}
		 
	}
	
	public void removeIngredient(String type) {
		//find the item in the stack, pop
		while (!myBurger.isEmpty()) {
			if (myBurger.peek().equalsIgnoreCase(type)) {
				myBurger.push(type);
			} else 
				myAux.push(myBurger.pop()); 
		}
		
	}
	
	private void reset() {
		while (!myAux.isEmpty()) {
			myBurger.push(myAux.pop());
		}
	}
	
	public String toString() {
		return myBurger.toString(); 
	}
	
	public static void main (String[] theArgs) { 
		
	}
}
