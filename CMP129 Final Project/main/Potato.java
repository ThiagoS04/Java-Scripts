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

public class Potato extends Plant{
	
	//Declare fields
	private static final int timeToGrow = 120 + 1;		//+1 so harvest won't be called with other needs
	private static final int waterNeeded = timeToGrow / 10;
	private static final int foodNeeded = waterNeeded * 2;
	private static final int sellPrice = 3;
	private static final int purchasePrice = 5;
	
	//Constructor that calls the super class
	public Potato(int[] gridPos) {
		
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
				
				if (Potato.this.getPlantHealth() > 0) {
					
					//Call method to harvest
					harvest("Potato", Potato.this);
					
					//Remove references
					Potato.this.setPlantHealth(0);		//Set plant health to 0 so timer methods will remove reference
					new callWater(Potato.this);
					new callFood(Potato.this);
					
				}
				
			}
			
		}, timeToGrow * 1000);
		
	}
	
	//Class to call food need
	private static class callFood extends TimerTask {
		
		//Declare var
		private Potato potato;
		
		//Constructor needed so specific potato object is called
		public callFood(Potato potato) {
			
			//Set potato object
			this.potato = potato;
			
		}
		
		@Override
		public void run() {
			
			Platform.runLater(() -> {
				
				if (potato.getPlantHealth() <= 0) {		//Check if plant is dead
					
					//Remove reference
					potato = null;
					cancel();
					
				}else {
					
					//Call need food method
					potato.needFood();
					
				}
				
			});
			
		}
		
	}
	
	//Class to call water need
	private static class callWater extends TimerTask {
		
		//Declare var
		private Potato potato;
		
		//Constructor needed so specific potato object is called
		public callWater(Potato potato) {
			
			//Set potato object
			this.potato = potato;
			
		}
		
		@Override
		public void run() {
			
			Platform.runLater(() -> {
				
				if (potato.getPlantHealth() <= 0) {		//Check if plant is dead
					
					//Remove reference
					potato = null;
					cancel();
					
				}else {
					
					//Call need food method
					potato.needWater();
					
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
