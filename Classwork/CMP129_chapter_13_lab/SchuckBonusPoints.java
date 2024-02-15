/*Thiago Schuck November 10 2023
 * This program changes the previous lab to include super and sub classes
 */
package chapter_13_lab;
import java.util.ArrayList;

public class SchuckBonusPoints extends SchuckPointsEarnedD implements SchuckWednesday{

	private static double bonusPoints;
	
	//Default constructor
	public SchuckBonusPoints() {
		super();
		bonusPoints = 0;
	}
	
	/**
	 * Constructor that takes points param
	 * @param p
	 */
	public SchuckBonusPoints(double p) {
		super(p);
		bonusPoints += p;
	}
	
	/**
	 * Method that sets bonus points to both sub and super classes if volume bonus qualifications are met
	 * @param c
	 */
	public void setBonusPoints(ArrayList<Integer> c) {
		
		if (qualifiesVolumeBonus(c)) {
			bonusPoints += 9;
			super.addPoints(9);
		}
	}
	
	/**Getter method
	 * 
	 * @return total bonus points
	 */
	public double getBonusPoints() {
		return bonusPoints;
	}
	
	/**Method to determine if user qualifies for volume bonus
	 * User must have bought cups for at least 3 months and the average per month must be >= 5.1
	 * 
	 * @param c
	 * @return user qualifies or not
	 */
	public boolean qualifiesVolumeBonus(ArrayList<Integer> c) {
		
		//Declare variables
		double totalCupsBought = 0, monthsBought = 0, averageCupsBought;
		
		//Loop through array to get total # cups bought and # of months user bought cups
		for(int month : c) {
			if (month > 0) {
				monthsBought++;						//# of months user bought
				totalCupsBought += month;			//# of cups user bought in all months
			}
		}
		
		//Calculate average cups bought per month
		averageCupsBought = (float) totalCupsBought / monthsBought;
		
		//Return true or false if user qualifies for bonus
		if (monthsBought >= 3 && averageCupsBought >= 5.1) {
			return true;
		}else{
			return false;
		}
	}
	
	public void WednesdayBonusPoints(int cups) {
		
		//Declare fields
		double happyHourPoints;		//Cups bought during 2pm to 4pm on Wednesday
		
		//Calculate bonus points and add to total bonus points
		happyHourPoints = cups * 0.5;
		bonusPoints += happyHourPoints;
		
		//Add bonus points to total points
		super.addPoints(happyHourPoints);
		
	}
}
