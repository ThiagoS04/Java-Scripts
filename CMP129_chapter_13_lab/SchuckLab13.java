/**Thiago Schuck November 10 2023
 * This program changes the previous lab to include a GUI
 */
package chapter_13_lab;

import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.InputMismatchException;

public class SchuckLab13 extends Application{

	private GraphicalUserInterfaceB gui;
	
	@Override
	//Method that starts GUI
	public void start(Stage primaryStage) {
		
		//Launch GUI
		new GraphicalUserInterfaceB().start(primaryStage);
		
		//Create GUI object
		this.gui = new GraphicalUserInterfaceB();

	}
	
	//Main method
	public static void main(String[] args) {
				
		//Call launch on Application
		launch(args);
		
	}
	
	/**Method that sets cups bought during happy hour
	 * @param points
	 * @param cups
	 */
	public static void WednesdayBonusPoints(SchuckBonusPoints points, int cups) {
		
		//Set happy hour cups
		int happyHourCups = cups;
		
		//Send happy hour cups to method
		points.WednesdayBonusPoints(happyHourCups);
	}
	
	/**Method to validate cups variable
	 * 
	 * @param cups bought
	 * @return whether or not cups variable is valid
	 */
	public static boolean validateCups(int c) {		//Method to validate cups variable
		
		//Declare variables
		boolean t = false;
		
		//Validate cups
		if (c >= 0) {
			t = true;
		}
		
		//Return cups
		return t;
	}
	
	/**Method to create object
	 * 
	 * @param points
	 * @param userInput
	 * @return filled points object
	 */
	public static SchuckBonusPoints createObject(SchuckBonusPoints points, boolean choice) {
		
		//Create variable to store loyalty points
		double loyaltyPoints = choice ? 10.0 : 0.0;
		
		//Create object
		points = new SchuckBonusPoints(loyaltyPoints);
		
		//Return created obj
		return points;
		
	}
	
	/**Method to print points
	 * 
	 * @param points
	 */
	public static void printPoints(SchuckBonusPoints points) {
		
		//Display points
		System.out.printf("You have %,.2f points.\nOut of those points, %,.2f are bonus points.\n", points.getPoints(), points.getBonusPoints());
		
	}

}

/*
Would you like to sign up for the loyalty program for an extra 10 points?
yes
10 points have been added.
How many cups have you bought this month?
10
Would you like to enter another month? (yes/no)
yes
How many cups have you bought this month?
10
Would you like to enter another month? (yes/no)
yes
How many cups have you bought this month?
10
Would you like to enter another month? (yes/no)
no
Congratulations! You have qualified for the volume bonus! Thank you for your patronage, 9 points will be added.
How many cups did you purchase on Wednesday from 2pm to 4pm?
10
You have 110.850 points.
Out of those points, 24.00 are bonus points.
 */

