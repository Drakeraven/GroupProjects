import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Burger {
	
	private MyStack<String> myTopBurger; 
	
	private MyStack<String> myBottomBurger; 
	
	private MyStack<String> myAux;  
	
	private List<String> modelTop = new ArrayList<>(Arrays.asList("Pickle", "Top Bun", 
			"Mayonnaise", "Baron Sauce", "Lettuce", "Tomato", "Onions"));
	
//	private List<String> modelBottom = new ArrayList<>(Arrays.asList("Pepperjack", "Mozzarella", "Cheddar", "Beef", "Mushrooms", 
//			"Mustard", "Ketchup", "Bottom Bun"));
	private List<String> modelBottom = new ArrayList<>(Arrays.asList("Bottom Bun", "Ketchup", "Mustard", "Mushrooms", 
			"Beef", "Cheddar", "Mozzarella", "Pepperjack"));
	
	private String[] myCheese = {"Pepperjack", "Mozzarella", "Cheddar"}; 
	
	private String[] mySauce = {"Mayonnaise", "Baron Sauce", "Mustard", "Ketchup"};
	
	private String[] myVeggies = {"Pickle", "Lettuce", "Tomato", "Onions", "Mushrooms"};
	
	public Burger(boolean theWorks) { 
		System.err.println("Burger Created");
		
		myAux = new MyStack<String>();
		myTopBurger = new MyStack<String>(); 
		myBottomBurger = new MyStack<String>();
		
		if(theWorks){
			for (int i = 0; i < modelTop.size(); i++) {
				myTopBurger.push(modelTop.get(i));
			}
			for (int i = 0; i < modelBottom.size(); i++) {  //TODO Flipped this along with the modelBottom.
				myBottomBurger.push(modelBottom.get(i));
			}
		} else { 
			myBottomBurger.push("Bottom Bun");
			myBottomBurger.push("Beef");
			myTopBurger.push("Top Bun");
			
		}
	}
	
	public void addPatty(){
		//adds new patties to the top of the topBurger 
		if (!myTopBurger.isEmpty()) { 
			myTopBurger.push("Beef");
		}
	}
	
	public void changePatties(String pattyType){
		//iterate through stack, find patty, pop, push new patty
		modelTop.add(pattyType);
		while (!myTopBurger.isEmpty()) {
			if(myTopBurger.peek().equalsIgnoreCase("Beef") 
					|| myTopBurger.peek().equalsIgnoreCase("Chicken")
					|| myTopBurger.peek().equalsIgnoreCase("Veggie")) {
				myTopBurger.pop();
				myAux.push(pattyType);
			} else { 
				myAux.push(myTopBurger.pop());
			}
		}
		reset(myTopBurger);	
		
		modelBottom.set(4, pattyType); //TODO THERE YOU ARE 
		while (!myBottomBurger.isEmpty()) { 
			if (myBottomBurger.peek().equalsIgnoreCase("Beef")
					|| myBottomBurger.peek().equalsIgnoreCase("Chicken")
					|| myBottomBurger.peek().equalsIgnoreCase("Veggie")) {
				myBottomBurger.pop();
				myAux.push(pattyType);
			} else {
				myAux.push(myBottomBurger.pop());
			}
		}
		reset(myBottomBurger);
	}
	
	public void removePatty(){ 
		//iterate through stack, find patty, remove patty 
		while (!myTopBurger.isEmpty()){
			if(myTopBurger.peek().equalsIgnoreCase("Beef") || myTopBurger.peek().equalsIgnoreCase("Chicken")
					|| myTopBurger.peek().equalsIgnoreCase("Veggie")) {
				myTopBurger.pop();
				break;
			} else {
				myAux.push(myTopBurger.pop());
			}
		}
		reset(myTopBurger);
	}
	
	public void addCategory(String type) {
		//for items in category, send to addIngredient
		String[] category = {""}; 
		String temp = type.toLowerCase();
	
		if (temp.equalsIgnoreCase("cheese")) {
			category = myCheese;
		} else if (temp.equalsIgnoreCase("veggies")) {
			category = myVeggies;
		} else {
			category = mySauce;
		}

		for (int i = 0; i < category.length; i++){
			addIngredient(category[i]);
		}
		
	}
	
	public void removeCategory(String type) {
		//for items in category sent to removeIngredient
		String[] category = {""}; 
		String temp = type.toLowerCase();
		
		switch(temp) {
			case "cheese": category = myCheese; 
				break;
			case "veggies": category = myVeggies; 
				break;
			case "sauces": category = mySauce;
		}
		
		for (int i = 0; i < category.length; i++){
			System.out.println("Sent: " + category[i]);
			removeIngredient(category[i]);
		}
	}
	
	
	public void addIngredient(String type) {
		MyStack<String> workingBurger;
		List<String> modelBurger;
		boolean notPlaced = true;
		
		if (this.modelTop.contains(type)) {
			modelBurger = modelTop;
			workingBurger = myTopBurger;
		} else if (this.modelBottom.contains(type)) {
			modelBurger = modelBottom;
			workingBurger = myBottomBurger;
		} else {
			modelBurger = null; 
			workingBurger = null;
			notPlaced = false; 
		}
		
		//int ingLocation = modelBurger.indexOf(type) - 1;
		
		//System.out.println(modelBurger);
		//System.out.println(workingBurger);
		//System.out.println(modelBurger.get(ingLocation));
		while (notPlaced) { 
			int ingLocation = modelBurger.indexOf(type) - 1;
			for (int i = 0; i < workingBurger.size(); i++) {
				//System.out.println(modelBurger.get(ingLocation));
				if ( workingBurger.peek().equalsIgnoreCase("Top Bun") //TODO maybe not an if?
						|| workingBurger.peek().equalsIgnoreCase("Bottom Bun")
						|| workingBurger.peek().equalsIgnoreCase(modelBurger.get(ingLocation))) {
					//System.out.println(workingBurger);
					workingBurger.push(type);
					notPlaced = false;
				} else {
					myAux.push(workingBurger.pop());
					//ingLocation--;
				}
			}
			ingLocation--;
		}
		reset(workingBurger);
	}
	
	public void removeIngredient(String type) {
		//find the item in the stack, pop
		MyStack<String> temp;
		System.out.println("Remove: " + type);
		
		if (modelTop.contains(type)) {
			temp = myTopBurger; 
		} else {
			temp = myBottomBurger; 
		}
			
		while (!temp.isEmpty()) {
			if (temp.peek().equalsIgnoreCase(type)) {
				temp.pop();
				break;
			} else 
				myAux.push(temp.pop()); 
		}
		reset(temp);
		
	}
	
	private void reset(MyStack<String> theBurger) {
		while (!myAux.isEmpty()) {
			theBurger.push(myAux.pop());
		}
	}
	
	public String toString() {
		//add something to put the top and bottom together
		while(!myTopBurger.isEmpty()){
			myBottomBurger.push(myTopBurger.pop()); 
		}
		
		//System.err.println(myTopBurger);
		
		return myBottomBurger.toString(); 
	}
	
	//Testing purposes 
	public static void main (String[] theArgs) { 
		Burger myBurg = new Burger(false); 
//		myBurg.removeCategory("Veggies");
//		myBurg.removeCategory("Sauce");
//		myBurg.addCategory("Baron Sauce");
//		System.out.println(myBurg);
		myBurg.addPatty();
		myBurg.addPatty();
		myBurg.changePatties("Chicken");
		//myBurg.addIngredient("Onions");
		myBurg.addCategory("Sauces");
		//myBurg.addCategory("Cheese");
		//myBurg.addCategory("Veggies");
		//myBurg.addIngredient("Pickle");
		//myBurg.addIngredient("Onions");
//		myBurg.addIngredient("Lettuce");
//		myBurg.addIngredient("Tomato");
//		myBurg.addIngredient("Mushrooms");
		//myBurg.removeIngredient("Cheddar");
		//myBurg.removeIngredient("Cheddar");

		myBurg.removeCategory("Veggies");
		myBurg.addIngredient("Lettuce");
		System.out.println(myBurg);
		
//		Burger other = new Burger(false);
//		other.addPatty();
//		other.addPatty();
//		other.changePatties("Turkey");
//		other.addIngredient("Onions");
//		other.addIngredient("Pepperjack");
//		other.addIngredient("Mushrooms");
//		//other.addCategory("Cheese");
//		//other.removeIngredient("Cheddar");
//		System.out.println(other);
	}
}
