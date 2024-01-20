/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is the interface for the Plant class
 */
package main;

public interface PlantInterface {
	
	/**Declare methods
	 * Getter and Setter methods
	 * Game function methods
	 */
	
	//Getter and Setter methods
	int getTimeToGrow();
	void setTimeToGrow(int timeToGrow);
	
	int getWaterNeeded();
	void setWaterNeeded(int waterNeeded);
	
	int getFoodNeeded();
	void setFoodNeeded(int foodNeeded);
	
	boolean getNeedsWater();
	void setNeedsWater(boolean needs);
	
	boolean getNeedsFood();
	void setNeedsFood(boolean needs);
	
	int getPlantHealth();
	void setPlantHealth(int plantHealth);
	
	int getSellPrice();
	void setSellPrice(int sellPrice);
	
	int getPurchasePrice();
	void setPurchasePrice(int purchasePrice);
	
	//Game function methods
	
	//Needs method
	void needFood();
	void needWater();
	
}
