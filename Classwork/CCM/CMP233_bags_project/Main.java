/* Thiago Schuck 15 February 2024
 * This is the bags project, it's goal is to use an ADT bag to determine the probability of picking a coin
 */
package bags_project;

public class Main {

	//Main method
	public static void main(String[] args) {
		
		//Declare variables
		double probabilityPercent;			//Holds probability of picking a specific coin
		int totalCoins, totalSpecific;		//Holds total # of coins in bag, holds total # of a specific coin in bag
		int coin;							//To determine which coin to add to bag
		BagInterface<Coin> bag = new ResizableArrayBag<Coin>();
		
		//Create coins to compare to
		Coin penny = new Coin("Penny", 1);
		Coin nickel = new Coin("Nickel", 5);
		Coin dime = new Coin("Dime", 10);
		Coin quarter = new Coin("Quarter", 25);
		
		//Randomly fill bag
		totalCoins = (int) (Math.random() * 21) + 10;		//Random int from 10-30
		for (int i = 0; i < totalCoins; i++) {
			
			//Get random coin
			coin = (int) (Math.random() * 4);
			
			//Determine coin
			switch(coin) {
			
			case 0:			//Penny
				
				bag.add(new Coin("Penny", 1));							//Create new coin to add to bag
				break;
				
			case 1:			//Nickel
				
				bag.add(new Coin("Nickel", 5));
				break;
				
			case 2:			//Dime
				
				bag.add(new Coin("Dime", 10));
				break;
				
			case 3:			//Quarter
				
				bag.add(new Coin("Quarter", 25));
				break;
			
			}//End switch
			
		}//End for loop
		
		//Test bag
		Object[] bagContents = bag.toArray();
		for (Object coinInBag: bagContents) {
			
			System.out.print(((Coin) coinInBag).getName() + ", ");
			
		}
		System.out.println("\n");
		
		System.out.println("Adding a quarter...");
		bag.add(quarter);
		
		System.out.println("Removing a coin...");
		bag.remove();
		for (Object coinInBag: bagContents) {
			
			System.out.print(((Coin) coinInBag).getName() + ", ");
			
		}
		System.out.println("\n");
		
		System.out.println("Bag contains quarter: " + bag.contains(quarter));
		
		System.out.println("Removing a quarter...");
		bag.remove(quarter);
		for (Object coinInBag: bagContents) {
			
			System.out.print(((Coin) coinInBag).getName() + ", ");
			
		}
		System.out.println("\n");
		
		System.out.println("Current size of bag is: " + bag.getCurrentSize());
		
		//Tell user selected coins are removed from bag
		System.out.println("Unless stated otherwise, selected coins are removed from bag.\n");
		
		bag.clear();
		
		//Randomly fill bag
		totalCoins = (int) (Math.random() * 21) + 10;		//Random int from 10-30
		for (int i = 0; i < totalCoins; i++) {
			
			//Get random coin
			coin = (int) (Math.random() * 4);
			
			//Determine coin
			switch(coin) {
			
			case 0:			//Penny
				
				bag.add(penny);							//Create new coin to add to bag
				break;
				
			case 1:			//Nickel
				
				bag.add(nickel);
				break;
				
			case 2:			//Dime
				
				bag.add(dime);
				break;
				
			case 3:			//Quarter
				
				bag.add(quarter);
				break;
			
			}//End switch
			
		}//End for loop
		
		//Scenario 1		1 penny, 1 quarter
		System.out.println("Scenario: 1 penny and then 1 quarter");
		
		totalSpecific = bag.getFrequencyOf(penny);		//Probability of penny
		totalCoins = bag.getCurrentSize();
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of selecting a penny: %" + String.format("%.3f", probabilityPercent));		//Print
		
		totalSpecific = bag.getFrequencyOf(quarter);	//Probability of quarter
		totalCoins = bag.getCurrentSize() - 1;			//-1 because penny was taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a quarter: %" + String.format("%.3f", probabilityPercent));	//Print
		
		probabilityPercent *= ((double) bag.getFrequencyOf(penny) / bag.getCurrentSize());		//Total Probability
		
		System.out.println("Total probability: %" + String.format("%.3f", probabilityPercent) + "\n");
		
		
		//Scenario 2		1 penny and put it back, 1 quarter
		System.out.println("Scenario: 1 penny, put the penny back, and then 1 quarter");
		
		totalSpecific = bag.getFrequencyOf(penny);		//Probability of penny
		totalCoins = bag.getCurrentSize();
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of selecting a penny: %" + String.format("%.3f", probabilityPercent));		//Print
		
		totalSpecific = bag.getFrequencyOf(quarter);	//Probability of quarter
		totalCoins = bag.getCurrentSize();
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a quarter: %" + String.format("%.3f", probabilityPercent));	//Print
		
		probabilityPercent *= ((double) bag.getFrequencyOf(penny) / bag.getCurrentSize());		//Total Probability
		
		System.out.println("Total probability: %" + String.format("%.3f", probabilityPercent) + "\n");
		
		
		//Scenario 3		1 dime, 1 nickel, 1 penny
		System.out.println("Scenario: 1 dime, 1 nickel, and then 1 penny");
		
		totalSpecific = bag.getFrequencyOf(dime);		//Probability of dime
		totalCoins = bag.getCurrentSize();
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of selecting a dime: %" + String.format("%.3f", probabilityPercent));		//Print
		
		totalSpecific = bag.getFrequencyOf(nickel);		//Probability of nickel
		totalCoins = bag.getCurrentSize() - 1;			//-1 because dime was taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a nickel: %" + String.format("%.3f", probabilityPercent));	//Print
		
		totalSpecific = bag.getFrequencyOf(penny);		//Probability of penny
		totalCoins = bag.getCurrentSize() - 2;			//-2 because dime and nickel were taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a penny: %" + String.format("%.3f", probabilityPercent));	//Print
		
		probabilityPercent *= ((double) bag.getFrequencyOf(dime) / bag.getCurrentSize()) * ((double) bag.getFrequencyOf(nickel) / (bag.getCurrentSize() - 1));		//Total Probability
		
		System.out.println("Total probability: %" + String.format("%.3f", probabilityPercent) + "\n");
		
		
		//Scenario 4		1 penny, 1 nickel, 1 dime
		System.out.println("Scenario: 1 penny, 1 nickel, and then 1 dime");
		
		totalSpecific = bag.getFrequencyOf(penny);		//Probability of penny
		totalCoins = bag.getCurrentSize();
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of selecting a penny: %" + String.format("%.3f", probabilityPercent));		//Print
		
		totalSpecific = bag.getFrequencyOf(nickel);		//Probability of nickel
		totalCoins = bag.getCurrentSize() - 1;			//-1 because dime was taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a nickel: %" + String.format("%.3f", probabilityPercent));	//Print
		
		totalSpecific = bag.getFrequencyOf(dime);		//Probability of dime
		totalCoins = bag.getCurrentSize() - 2;			//-2 because penny and nickel were taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a dime: %" + String.format("%.3f", probabilityPercent));	//Print
		
		probabilityPercent *= ((double) bag.getFrequencyOf(penny) / bag.getCurrentSize()) * ((double) bag.getFrequencyOf(nickel) / (bag.getCurrentSize() - 1));		//Total Probability
		
		System.out.println("Total probability: %" + String.format("%.3f", probabilityPercent) + "\n");
		
		
		//Scenario 5		1 penny, 1 nickel, 1 dime, 1 quarter
		System.out.println("Scenario: 1 penny, 1 nickel, 1 dime, and then 1 quarter");
		
		totalSpecific = bag.getFrequencyOf(penny);		//Probability of penny
		totalCoins = bag.getCurrentSize();
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of selecting a penny: %" + String.format("%.3f", probabilityPercent));		//Print
		
		totalSpecific = bag.getFrequencyOf(nickel);		//Probability of nickel
		totalCoins = bag.getCurrentSize() - 1;			//-1 because dime was taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a nickel: %" + String.format("%.3f", probabilityPercent));	//Print
		
		totalSpecific = bag.getFrequencyOf(dime);		//Probability of dime
		totalCoins = bag.getCurrentSize() - 2;			//-2 because penny and nickel were taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a dime: %" + String.format("%.3f", probabilityPercent));	//Print
		
		totalSpecific = bag.getFrequencyOf(quarter);	//Probability of quarter
		totalCoins = bag.getCurrentSize() - 3;			//-3 because penny, nickel, and dime were taken out
		probabilityPercent = ((double) totalSpecific / totalCoins);
		
		System.out.println("Probability of then selecting a quarter: %" + String.format("%.3f", probabilityPercent));
		
		probabilityPercent *= ((double) bag.getFrequencyOf(penny) / bag.getCurrentSize()) * ((double) bag.getFrequencyOf(nickel) / (bag.getCurrentSize() - 1)) * ((double) bag.getFrequencyOf(dime) / (bag.getCurrentSize() - 2));		//Total Probability
		
		System.out.println("Total probability: %" + String.format("%.3f", probabilityPercent) + "\n");
		
		
	}//End main method
	
}//End main class
