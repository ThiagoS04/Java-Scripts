/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is the main class
 */
package main;

//Imports
import java.util.HashMap;
import gui.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	//Global var
	public static HashMap<String, Integer> inventory = new HashMap<String, Integer>();	//HashMap to store crops in user inventory
	public static HashMap<Integer, Plant> references = new HashMap<Integer, Plant>();	//HashMap to store reference variables for obj
	public static int gold = 0;
	
	/**Method that starts gui 
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		
		//Attempt to start gui
		try {
			
			new UserInterface().start(primaryStage);
			
		//Catch and print any exception
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
	}
	
	/**Main method
	 * @param args
	 */
	public static void main(String[] args) {

		//Declare variables
		
		//Give user starting Potato
		inventory.put("Potato", 1);
		inventory.put("Radish", 1);
		inventory.put("Wheat", 1);
		gold = 15;
				
		//Launch gui
		launch(args);
		
	}
	
	/**Method to add or remove crops from inventory when harvesting or planting
	 * @param crop
	 * @param amount
	 * Add amount (amount will be negative if planting)
	 * If there are no more crops in inventory remove crop
	 */
	public static void updateInventory(String crop, int amount) {
		
		//Add amount
		if (!inventory.containsKey(crop)) {	//Check if inventory is empty
			
			inventory.put(crop, amount);
			
		}else {
			
			inventory.put(crop, inventory.get(crop) + amount);
			
		}
		
		//Reset inventory
		UserInterface.setInventory();
	}
	
	/**Method to create crop object
	 * @param crop
	 * @param xPos
	 * @param yPos
	 * Check which crop to create
	 * Create obj
	 */
	public static void createCrop(String crop, int[] gridPos) {
		
		int pos = Integer.parseInt(String.valueOf(gridPos[0]) + String.valueOf(gridPos[1]));	//gridPos as one int
		
		switch (crop) {
		
		case "Potato":
			
			//Create potato object
			Potato potato = new Potato(gridPos);
			
			//Save reference variable
			references.put(pos, potato);
			
			//Get rid of reference for deletion later on
			potato = null;
			break;
			
		case "Radish":
			
			//Create radis object
			Radish radish = new Radish(gridPos);
			
			//Save reference variable
			references.put(pos, radish);
			
			//Get rid of reference for deletion later on
			radish = null;
			break;
			
		case "Wheat":
			
			//Create wheat object
			Wheat wheat = new Wheat(gridPos);
			
			//Save reference variable
			references.put(pos, wheat);
			
			//Get rid of reference for deletion later on
			wheat = null;
			break;
			
		default:		//Crop not found
			
			System.out.println("Crop not found.");
		
		}
		
	}
	
	//Method to get prices
	public static int[] getPurchasePrices() {
		
		//Create array of purchase prices
		int[] prices = {Potato.getCropPurchasePrice(), Radish.getCropPurchasePrice(), Wheat.getCropPurchasePrice()};
		
		//Return array
		return prices;
		
	}
	
	//Method to get gold
	public static int getGold() {
		
		return gold;
		
	}
	
	//Method to set gold
	public static void setGold(int amount) {
		
		gold += amount;
		
	}

}
