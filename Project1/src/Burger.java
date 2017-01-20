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
		
		for (int i = 0; i < category.length; i++){
			removeIngredient(category[i]);
		}
	}
	
	public void addIngredient(String type) {
		//find the item after the ingredient in the array
		//push ingredient to the top 
		System.out.println(type);
		System.out.println("add ingredient");
		int location = 0; 
		boolean flag = false;
		for(int i = 0; i < baronBurger.length; i++) {
			if (baronBurger[i].equalsIgnoreCase(type)) {
				location = i+1;
			}
		}
		System.out.println(baronBurger[location - 1]);
		while (location < baronBurger.length || flag) { 
			if (!myBurger.isEmpty()) {
				if (myBurger.peek().equalsIgnoreCase(baronBurger[location])) {
					System.out.println("if");
					myBurger.push(type);
					flag = true; 
				} else {
					System.out.println(myAux.toString());
					location++;
					myAux.push(myBurger.pop());
				}			
			}
		}
		System.out.println("reset");
		reset();
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
