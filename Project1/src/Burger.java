import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Burger {
	
	private MyStack<String> myTopBurger; 
	
	private MyStack<String> myBottomBurger; 
	
	private MyStack<String> myAux;  
	
	private List<String> modelTop = new ArrayList<>(Arrays.asList("Pickle", "Top Bun", 
			"Mayonnaise", "Baron-Sauce", "Lettuce", "Tomato", "Onions"));
	
	private List<String> modelBottom = new ArrayList<>(Arrays.asList("Bottom Bun", "Ketchup", "Mustard", "Mushrooms", 
			"Beef", "Cheddar", "Mozzarella", "Pepperjack"));
	
	private String[] myCheese = {"Pepperjack", "Mozzarella", "Cheddar"}; 
	
	private String[] mySauce = {"Mayonnaise", "Baron-Sauce", "Mustard", "Ketchup"};
	
	private String[] myVeggies = {"Pickle", "Lettuce", "Tomato", "Onions", "Mushrooms"};
	
	public Burger(boolean theWorks) { 
		
		myAux = new MyStack<String>();
		myTopBurger = new MyStack<String>(); 
		myBottomBurger = new MyStack<String>();
		
		if(theWorks){
			for (int i = 0; i < modelTop.size(); i++) {
				myTopBurger.push(modelTop.get(i));
			}
			for (int i = 0; i < modelBottom.size(); i++) { 
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
		
		modelBottom.set(4, pattyType); 
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
		String[] category = {""}; 
		String temp = type.toLowerCase();
		
		switch(temp) {
			case "cheese": category = myCheese; 
				break;
			case "veggies": category = myVeggies; 
				break;
			case "sauce": category = mySauce;
		}
		
		for (int i = 0; i < category.length; i++){
			//System.out.println("Sent: " + category[i]);
			removeIngredient(category[i]);
		}
	}
	
	
	public void addIngredient(String type) {
		MyStack<String> workingBurger = null;
		List<String> modelBurger = null ;

		if (type.equalsIgnoreCase("Pickle")) {
			addPickle();
		} else if (type.equalsIgnoreCase("Pepperjack") 
				|| type.equalsIgnoreCase("Cheddar")
				|| type.equalsIgnoreCase("Mozzarella")) {
			addCheese(type);			
		} else if (!(type.equalsIgnoreCase("with") || (type.equalsIgnoreCase("but")))){

			if (this.modelTop.contains(type)) {
				modelBurger = modelTop;
				workingBurger = myTopBurger;
				workingBurger = myTopBurger;

			} else if (this.modelBottom.contains(type)) {
				modelBurger = modelBottom;
				workingBurger = myBottomBurger;
			}
			
			addIngredient(modelBurger, workingBurger, type);
			}
	}
	
	
	private void addIngredient(List<String> modelBurger, MyStack<String> workingBurger, String type) {
		int ingLocation = modelBurger.indexOf(type) - 1;
		boolean notPlaced = true;
		
		while (notPlaced) { 
			for (int i = 0; i < workingBurger.size(); i++) {
				if (workingBurger.peek().equalsIgnoreCase("Bottom Bun") 
						||workingBurger.peek().equalsIgnoreCase(modelBurger.get(ingLocation)) 
						|| workingBurger.peek().equalsIgnoreCase("Top Bun")) {
					workingBurger.push(type);
					notPlaced = false;
					break;
				} else {
					myAux.push(workingBurger.pop());
				}
			}
			ingLocation--;
			if (ingLocation == -1 && modelBurger.equals(modelBottom)) {
				ingLocation++;
			}
		}
		reset(workingBurger);
	}
	
	public void removeIngredient(String type) {
		//find the item in the stack, pop
		MyStack<String> temp;
		if (!type.equalsIgnoreCase("but")) {
			if (modelTop.contains(type)) {
				temp = myTopBurger; 
			} else {
				temp = myBottomBurger; 
			}
				
			while (!temp.isEmpty()) {
				if (temp.peek().equalsIgnoreCase(type)) {
					temp.pop();
				} else 
					myAux.push(temp.pop()); 
			}
			reset(temp);
		}	
	}
	
	private void addPickle() {
		while (!myTopBurger.isEmpty()) {
			myAux.push(myTopBurger.pop());
		}
		myTopBurger.push("Pickle");
		reset(myTopBurger);
	}
	
	private void addCheese(String type) {  
		boolean notPlaced = true;
		int ingLocation = modelBottom.indexOf(type) - 1;
		
		while (notPlaced) { 
			for (int i = 0; i < myBottomBurger.size(); i++) {
				if (myBottomBurger.peek().equalsIgnoreCase(modelBottom.get(ingLocation)) 
						|| myBottomBurger.peek().equalsIgnoreCase("Beef")
						|| myBottomBurger.peek().equalsIgnoreCase("Chicken")
						|| myBottomBurger.peek().equalsIgnoreCase("Veggie")) {
					myBottomBurger.push(type);
					notPlaced = false;
					break;
				} else {
					myAux.push(myBottomBurger.pop());
				}
			}
			ingLocation--;
			if (ingLocation == -1) {
				ingLocation++;
			}
		}
		reset(myBottomBurger);
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
		return myBottomBurger.toString(); 
	}
}
