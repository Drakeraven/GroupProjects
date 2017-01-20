

public class Burger {
	
	private MyStack<String> myBurger; 
	
	private String[] baronBurger = {"Pickle", "Top Bun", 
			"Mayonnaise", "Baron Sauce", "Lettuce", "Tomato", "Onions",
			"Pepperjack", "Mozzarella", "Cheddar", "Patty", "Mushrooms", 
			"Mustard", "Ketchup", "Bottom Bun"};

	
	public Burger(boolean theWorks) { 
		if(theWorks){
			for(int i = baronBurger.length; i >= 0; i--){
				myBurger.push(baronBurger[i]);
			}
		} else { 
			myBurger.push("Bottom Bun");
			myBurger.push("Patty");
			myBurger.push("Top Bun");
		}
			
	}
	
	public void changePatties(String pattyType){
	
	}
	
	public void addPatty(){
		
	}
	
	public void removePatty(){ 
		
	}
	
	public void addCategory(String type){
		
	}
	
	public void removeCategory(String type){
		
	}
	
	public void addIngredient(String type){
		
	}
	
	public void removeIngredient(String Type){
		
	}
	
	public String toString(){
		return ""; 
	}
}
