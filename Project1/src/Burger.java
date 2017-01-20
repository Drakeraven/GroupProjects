

public class Burger {
	
	private MyStack<String> myBurger; 
	
	private MyStack<String> myAux; 
	
	private String[] baronBurger = {"Pickle", "Top Bun", 
			"Mayonnaise", "Baron Sauce", "Lettuce", "Tomato", "Onions",
			"Pepperjack", "Mozzarella", "Cheddar", "Beef", "Mushrooms", 
			"Mustard", "Ketchup", "Bottom Bun"};
	
	

	private String[] myCheese = {"Pepperjack", "Mozzarella", "Cheddar"}; 
	
	private String[] mySauce = {"Mayonnaise", "Baron Sauce", "Mustard", "Ketchup"};
	
	private String[] myVeggies = {"Pickle", "Lettuce", "Tomato", "Onion", "Mushrooms"};
	
	public Burger(boolean theWorks) { 
		myAux = new MyStack<String>();
		if(theWorks){
			for(int i = baronBurger.length - 1; i >= 0; i--) {
				myBurger.push(baronBurger[i]);
			}
		} else { 
			myBurger.push("Bottom Bun");
			myBurger.push("Beef");
			myBurger.push("Top Bun");
		}
			
	}
	
	public void addPatty(){
		//iterate through stack find bottom patty push patty 
		while(!myBurger.isEmpty()) {
			if(myBurger.peek().equalsIgnoreCase("Beef"))
				myBurger.push("Beef");
		}
	}
	
	public void changePatties(String pattyType){
		//iterate through stack, find patty, pop, push new patty
		while (!myBurger.isEmpty()) {
			if(myBurger.peek().equalsIgnoreCase("Beef")) {
				myBurger.pop();
				myBurger.push(pattyType);
			} else { 
				myAux.push(myBurger.pop());
			}
		}
		reset();		
	}
	
	
	
	public void removePatty(){ 
		//iterate through stack, find patty, remove patty (check if there's more than one patty, dont do unless there is 2+) 
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
		
	}
	
	public void removeCategory(String type) {
		//for items in category sent to removeIngredient
	}
	
	public void addIngredient(String type) {
		//find the item after the ingredient in the array
		//push ingredient to the top 
		String location = ""; 
		for(int i = baronBurger.length - 1; i >= 0; i--) {
			if (baronBurger[i].equalsIgnoreCase(type)) {
				location = baronBurger[i+1];
			}
		}
		while (!myBurger.isEmpty()) {
			if (myBurger.peek().equalsIgnoreCase(location)) {
				myBurger.push(type);
			} else { 
				myAux.push(myBurger.pop());
			}
		}
	}
	
	public void removeIngredient(String Type) {
		//find the item in the stack, pop
		
	}
	
	public void reset() {
		while (!myAux.isEmpty()) {
			myBurger.push(myAux.pop());
		}
	}
	
	public String toString() {
		return myBurger.toString(); 
	}
}
