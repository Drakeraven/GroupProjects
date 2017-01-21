import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Burger {
	
	private MyStack<String> myTopBurger; 
	
	private MyStack<String> myBottomBurger; 
	
	private MyStack<String> myAux;  
	
	private List<String> modelTop = new ArrayList<>(Arrays.asList("Pickle", "Top Bun", 
			"Mayonnaise", "Baron Sauce", "Lettuce", "Tomato", "Onions"));
	
	private List<String> modelBottom = new ArrayList<>(Arrays.asList("Pepperjack", "Mozzarella", "Cheddar", "Beef", "Mushrooms", 
			"Mustard", "Ketchup", "Bottom Bun"));
	
	private String[] myCheese = {"Pepperjack", "Mozzarella", "Cheddar"}; 
	
	private String[] mySauce = {"Mayonnaise", "Baron Sauce", "Mustard", "Ketchup"};
	
	private String[] myVeggies = {"Pickle", "Lettuce", "Tomato", "Onion", "Mushrooms"};
	
	public Burger(boolean theWorks) { 
		myAux = new MyStack<String>();
		myTopBurger = new MyStack<String>(); 
		myBottomBurger = new MyStack<String>();
		
		if(theWorks){
			for (int i = 0; i < modelTop.size(); i++) {
				myTopBurger.push(modelTop.get(i));
			}
			for (int i = (modelBottom.size() - 1); i >= 0; i--) {
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
		
		modelBottom.set(0, pattyType);
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
		
		switch(type) {
			case "cheese": category = myCheese; 
				break;
			case "veggies": category = myVeggies; 
				break;
			case "sauces": category = mySauce;
		}
		
		for (int i = 0; i < category.length; i++){
			//addIngredient(category[i]);
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
//		//find the item after the ingredient in the array
//		//push ingredient to the top 
//		System.out.println(type + "- add ingredient sent");
//		int location = 0; 
//		boolean flag = false;
//		for(int i = 0; i < baronBurger.length; i++) {
//			if (baronBurger[i].equalsIgnoreCase(type)) {
//				location = i + 1;
//			}
//		}
//		int checkSpot = 0;
//		System.out.println(baronBurger[location - 1] + " Item Above/to be placed");
//		System.out.println(baronBurger[location] + " Item below");
//		while (location + checkSpot < baronBurger.length || flag) {   //CHANGED BOUNDS, STILL HITTING OUT OF BOUNDS EXCEPTION
//			while (!myBurger.isEmpty()) {
//				System.out.println(baronBurger[location + checkSpot]);   //PUTS ONION AFTER FIRST BEEF??? FOR SOME REASON
//				if (myBurger.peek().equalsIgnoreCase(baronBurger[location + checkSpot])) {
//					myBurger.push(type);
//					System.out.println("Hello" + myBurger);
//					flag = true; 								//FLAG DOESN'T END THE LOOP, CONTINUES LOOPING AFTER PUSHING ONION
//				} else {
//					System.out.println(myBurger);
//					myAux.push(myBurger.pop());
//					//System.out.println(myAux);
//				}	
//				System.out.println(location + checkSpot);
//				checkSpot++;
//			}
//			//checkSpot++;
//			//System.out.println(myBurger);
//			reset();
//		}
//		//System.out.println(myBurger);
//		System.out.println("reset");
//		reset();
	}
	
	public void removeIngredient(String type) {
		//find the item in the stack, pop
		MyStack<String> temp;
		
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
		Burger myBurg = new Burger(true); 
//		System.out.println(myBurg.myTopBurger);
//		System.out.println(myBurg);
//		//myBurg.removePatty();
//		myBurg.addPatty();
//		System.err.println(myBurg.myTopBurger);
//		myBurg.addPatty();
//		myBurg.addPatty();
//		System.out.println(myBurg);
//		System.out.println(myBurg.modelTop);
//		System.out.println(myBurg.myTopBurger);
		
		
		
		Burger other = new Burger(false);
		//System.out.println(other);
		other.addPatty();
		other.changePatties("Chicken");
		System.out.println(other);
	}
}
