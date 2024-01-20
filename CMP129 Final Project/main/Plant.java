/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is the main plant class
 */
package main;

//Imports
import gui.UserInterface;
import javafx.application.Platform;

/**Plant superclass
 * Declare fields
 * Declare functions
 */
public class Plant implements PlantInterface {

	//Declare fields
	private int timeToGrow;			//Time(s) to grow
	private int waterNeeded;		//Time(s) before plant needs more water
	private int foodNeeded;			//Time(s)before plant needs more food
	private int sellPrice;		//Money plant sells for
	private int purchasePrice; //Money plant costs to purchase
	private int plantHealth;
	private int waterNeedLevel = 0, foodNeedLevel = 0;		//How thirsty/hungry a plant is
	
	private int[] gridPos = new int[2];			//Position of plant on interface
	
	//Boolean to store if plant needs are met
	boolean needsWater = false, needsFood = false;		//If plant already needs water/food
	
	UserInterface ui = new UserInterface();
	
	//Default constructor
	public Plant() {
		
		//Set fields to 0
		timeToGrow = 0;
		waterNeeded = 0;
		foodNeeded = 0;
		sellPrice = 0;
		purchasePrice = 0;
		plantHealth = 0;
		gridPos[0] = -1;		//Set to -1 because 00 exists in gridpane
		gridPos[1] = -1;		//^
		
	}
	
	/**Constructor with all the fields
	 * @param timeToGrow
	 * @param waterNeeded
	 * @param foodNeeded
	 * @param spaceNeeded
	 * @param gridPos
	 */
	public Plant(int timeToGrow, int waterNeeded, int foodNeeded, int sellPrice, int purchasePrice, int[] gridPos) {
		
		//Set fields to respective parameters
		this.timeToGrow = timeToGrow;
		this.waterNeeded = waterNeeded;
		this.foodNeeded = foodNeeded;
		this.sellPrice = sellPrice;
		this.purchasePrice = purchasePrice;
		this.gridPos = gridPos;
		plantHealth = 100;
		
	}
	
	//Game Function methods
	
	/**Methods for needs
	 * If need is being called
	 * 		Need has not been called yet
	 * 			Show icon
	 * 		Need has been called
	 * 			Remove health
	 * 			Check if plant is dead
	 * 		Increment need level
	 * If need is being met
	 * 		Set need level to 0
	 * 		Remove icon
	 * 		Heal plant
	 */
	
	//Method for needs water
	public void needWater() {
		
		//Check if need is being called or met
		if (!needsWater) {		//If need is called
			
			//Check if need has already been called
			if (waterNeedLevel == 0) {		//Has not been called already
				
				//Show icon over object
				ui.createNeedIcon("water", gridPos);
	
			}else {			//Has been called already
				
				//Start removing plant 10 * needLevel health every time need is called
				plantHealth -= 10 * waterNeedLevel;
				System.out.println("Removing " + 10 * waterNeedLevel + " health because of thirst");
				
				//Check if plant is dead
				if (plantHealth < 0) {
					
					//Call method to kill plant
					killPlant("Potato");
					
				}
				
			}
			
			//Increment need
			waterNeedLevel++;
			
		}else {					//If need is being met
			
			//Reset need level
			waterNeedLevel = 0;
		
			//Remove icon over object
			Platform.runLater(() -> {
				
				UserInterface.removeIcon("water", gridPos, false);
				
			});
	
			//Heal plant
			plantHealth += 10;
			needsWater = false;
			
		}
	}
	
	//Method for needs food
	public void needFood() {
		
		//Check if need is being called or met
		if (!needsFood) {		//If need is called
			
			//Check if need has already been called
			if (foodNeedLevel == 0) {		//Has not been called
				
				//Show icon over object
				ui.createNeedIcon("food", gridPos);
	
			}else {			//Has been called
				
				//Start removing plant 10 * needLevel health every time need is called
				plantHealth -= 10 * foodNeedLevel;	
				System.out.println("Removing " + 10 * foodNeedLevel + " health because of hunger");
				
				//Check if plant is dead
				if (plantHealth < 0) {
					
					//Call method to kill plant
					killPlant("Potato");
					
				}
				
			}
			
			//Increment need
			foodNeedLevel++;			
			
		}else {					//If need is being met
			
			//Reset need level
			foodNeedLevel = 0;
			
			//Remove icon over object
			Platform.runLater(() -> {
				
				UserInterface.removeIcon("food", gridPos, false);
				
			});
			
			//Heal plant
			plantHealth += 10;
			needsFood = false;
			
		}
		
	}
	
	/**Method to kill plant
	 * Delete obj
	 */
	private void killPlant(String crop) {
		
		Platform.runLater(() -> {
			
			//Call method with true as remove bool to remove every icon
			UserInterface.removeIcon("Crop", gridPos, true);
			
			//Delete references
			int pos = Integer.parseInt(String.valueOf(gridPos[0]) + String.valueOf(gridPos[1]));
			int ref = -1;
			for (int i : Main.references.keySet()) {	//Find reference
				
				if (i == pos) {
					
					ref = i;
					
				}
			}
			Main.references.put(ref, null);	//Set reference to null to delete obj
			Main.references.remove(pos);		//Remove reference from hashmap
			Runtime.getRuntime().gc();
			
		});
		
	}

	/**Method to harvest plant
	 * 
	 */
	public void harvest(String cropName, Plant crop) {
		
		Platform.runLater(() -> {
			
			//Call method with true as remove bool to remove every icon
			UserInterface.removeIcon("Crop", gridPos, true);
			
			//Update inventory
			Main.updateInventory(cropName, 2);
			
			//Remove reference
			int pos = Integer.parseInt(String.valueOf(gridPos[0]) + String.valueOf(gridPos[1]));
			int ref = -1;
			for (int i : Main.references.keySet()) {	//Find reference	
				
				if (i == pos) {
				
					ref = i;
					
				}
			}
			Main.references.put(ref, null);	//Set reference to null to delete obj
			Main.references.remove(pos);		//Remove reference from hashmap
			Runtime.getRuntime().gc();
			
		});
		
	}
	
	//Getter and Setter methods
	
	//Get time to grow
	@Override
	public int getTimeToGrow() {
		return timeToGrow;
	}

	//Set time to grow
	@Override
	public void setTimeToGrow(int timeToGrow) {
		this.timeToGrow = timeToGrow;
	}

	//Get water needed
	@Override
	public int getWaterNeeded() {
		return waterNeeded;
	}

	//Set water needed
	@Override
	public void setWaterNeeded(int waterNeeded) {
		this.waterNeeded = waterNeeded;
	}

	//Get food needed
	@Override
	public int getFoodNeeded() {
		return foodNeeded;
	}

	//Set food needed
	@Override
	public void setFoodNeeded(int foodNeeded) {
		this.foodNeeded = foodNeeded;
	}

	//Get sell price
	@Override
	public int getSellPrice() {
		return sellPrice;
	}

	//Set sell price
	@Override
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	//Get purchase price
	@Override
	public int getPurchasePrice() {
		return purchasePrice;
	}
	
	//Set purchase price
	@Override
	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	//Set needsWater
	@Override
	public void setNeedsWater(boolean needs) {
		
		needsWater = needs;
		
	}
	
	//Set needsWater
	@Override
	public boolean getNeedsWater() {
		
		return needsWater;
		
	}
	
	//Set needsWater
	@Override
	public void setNeedsFood(boolean needs) {
		
		needsFood = needs;
		
	}
	
	//Set needsWater
	@Override
	public boolean getNeedsFood() {
		
		return needsWater;
		
	}
	
	//Get plant health
	@Override
	public int getPlantHealth() {
		
		return plantHealth;
	
	}

	//Set plant health
	@Override
	public void setPlantHealth(int plantHealth) {
		
		this.plantHealth = plantHealth;
	
	}
}
