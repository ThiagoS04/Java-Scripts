/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is a plant subclass
 */
package main;

//Imports
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

public class Radish extends Plant{
	
	//Declare fields
	private static final int timeToGrow = 60 + 1;		//+1 so harvest won't be called with other needs
	private static final int waterNeeded = timeToGrow / 10;
	private static final int foodNeeded = waterNeeded * 2;
	private static final int sellPrice = 3;
	private static final int purchasePrice = 15;
	
	//Constructor that calls the super class
	public Radish(int[] gridPos) {
		
		//Call constructor
		super(timeToGrow, waterNeeded, foodNeeded, sellPrice, purchasePrice, gridPos);
		
		//Create timer
		Timer clock = new Timer();
		
		//Call needs after time intervals
		clock.scheduleAtFixedRate(new callFood(this), 0, foodNeeded * 1000);
		clock.scheduleAtFixedRate(new callWater(this), 0, waterNeeded * 1000);
		
		//Call harvest method after timeToGrow * 1000ms
		clock.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				if (Radish.this.getPlantHealth() > 0) {
					
					//Call method to harvest
					harvest("Radish", Radish.this);
					
					//Remove references
					Radish.this.setPlantHealth(0);		//Set plant health to 0 so timer methods will remove reference
					new callWater(Radish.this);
					new callFood(Radish.this);
					
				}
				
			}
			
		}, timeToGrow * 1000);
		
	}
	
	//Class to call food need
	private static class callFood extends TimerTask {
		
		//Declare var
		private Radish radish;
		
		//Constructor needed so specific potato object is called
		public callFood(Radish radish) {
			
			//Set potato object
			this.radish = radish;
			
		}
		
		@Override
		public void run() {
			
			Platform.runLater(() -> {
				
				if (radish.getPlantHealth() <= 0) {		//Check if plant is dead
					
					//Remove reference
					radish = null;
					cancel();
					
				}else {
					
					//Call need food method
					radish.needFood();
					
				}
				
			});
			
		}
		
	}
	
	//Class to call water need
	private static class callWater extends TimerTask {
		
		//Declare var
		private Radish radish;
		
		//Constructor needed so specific potato object is called
		public callWater(Radish radish) {
			
			//Set potato object
			this.radish = radish;
			
		}
		
		@Override
		public void run() {
			
			Platform.runLater(() -> {
				
				if (radish.getPlantHealth() <= 0) {		//Check if plant is dead
					
					//Remove reference
					radish = null;
					cancel();
					
				}else {
					
					//Call need food method
					radish.needWater();
					
				}
				
			});
			
		}
		
	}
	
	//Method to get purchasePrice
	public static int getCropPurchasePrice() {
		return purchasePrice;
	}
	
	//Method to get sellPrice
	public static int getCropSellPrice() {
		return sellPrice;
	}
	
}
