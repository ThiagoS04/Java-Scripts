/*Thiago Schuck November 10 2023
 * This program changes the previous lab to include super and sub classes
package chapter_12_lab;
*/
package chapter_13_lab;

//Imports
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**Method creates GUI
 * Create labels
 * Create text fields
 * Handle action events
 */
public class GraphicalUserInterfaceB {
	
	//Create variables
	ArrayList<Integer> cups = new ArrayList<Integer>();
	boolean joinedLoyalty = false;
	int stageX = 500;
	int stageY = 400;
	
	//Reference var to main class
	private SchuckLab13 mainObj;
	
	//Create reference var for SchuckBonusPoints
	SchuckBonusPoints points;
	
	/**Main method of GUI
	 * @param primaryStage
	 * Call initial scene method, method will call other scenes
	 */
	public void start(Stage primaryStage) {
		
		/**Call method to ask if user wants to join loyalty program
		 * This method will call another method to get cups purchased
		 */
		setLoyaltyScene(primaryStage);
		
	}
	
	/**Join loyalty scene
	 * @param primaryStage
	 * Create actors
	 * Set and show scene
	 * @return 
	 */
	public void setLoyaltyScene(Stage primaryStage) {
		
		//Create actors for joining loyalty program
		Label loyaltyLabel = new Label("Would you like to sign up for the loyalty program for an extra 10 points?");		//Label to ask if user wants to join loyalty program
		Button joinLoyalty = new Button("Sign up");		//Button if user wants to join loyalty program
		Button skip = new Button("Skip");		//Button if user does not want to join loyalty program
		
		//Create scene to ask user if they want to join loyalty program
		HBox yesNo = new HBox(10, joinLoyalty, skip);
		yesNo.setAlignment(Pos.CENTER);
		yesNo.setPadding(new Insets(10));
		
		VBox loyaltyLayout = new VBox(10, loyaltyLabel, yesNo);
		loyaltyLayout.setAlignment(Pos.CENTER);
		loyaltyLayout.setPadding(new Insets(10));
		
		Scene loyaltyScene = new Scene(loyaltyLayout, stageX, stageY);
		
		//Set and show loyalty scene
		primaryStage.setScene(loyaltyScene);
		primaryStage.show();
				
		//Handle if user wants to join loyalty program
		joinLoyalty.setOnAction( action -> {
			
			joinedLoyalty = true;
			
			//Call create object method in main
			points = SchuckLab13.createObject(points, joinedLoyalty);
			
			//Call method to create next scene
			cupsBoughtScene(primaryStage);
			
		});
		
		//Handle if user does not want to join loyalty program
		skip.setOnAction(action -> {
			
			//Call create object method in main
			points = SchuckLab13.createObject(points, joinedLoyalty);
			
			//Call method to create next scene
			cupsBoughtScene(primaryStage);
			
		});
	}
	
	/**Get cups bought scene
	 * @param primaryStage
	 * Declare var
	 * Loop until valid response entered
	 * Convert text into int
	 * Add to cups array list if > 0
	 */
	public void cupsBoughtScene(Stage primaryStage) {
		
		//Create actors for getting cups bought
		Label getCups = new Label("Enter cups purchased this month.");
		TextField cupsBought = new TextField();		//Text field for user to enter cups bought
		Button submit = new Button("Submit");		//Button to submit cups bought
		
		//Create actors for happy hour cups
		Label getHappyHourCups = new Label("Enter cups purchased on Wednesday between 2pm and 4pm");
		getHappyHourCups.setAlignment(Pos.CENTER);
		
		RadioButton cup0 = new RadioButton("0 cups");
		RadioButton cup1 = new RadioButton("1 cup");
		RadioButton cup2 = new RadioButton("2 cups");
		RadioButton cup3 = new RadioButton("3 cups");
		cup0.setSelected(true);
		
		ToggleGroup happyCups = new ToggleGroup();
		
		//Add radio buttons to toggle group
		cup0.setToggleGroup(happyCups);
		cup1.setToggleGroup(happyCups);
		cup2.setToggleGroup(happyCups);
		cup3.setToggleGroup(happyCups);
		
		//Put radio buttons into an hbox
		HBox happyCupOptions = new HBox(cup0, cup1, cup2, cup3);
		happyCupOptions.setAlignment(Pos.CENTER);
		happyCupOptions.setPadding(new Insets(10));
		
		//Create scene
		HBox question = new HBox(getCups, cupsBought);
		question.setAlignment(Pos.CENTER);
		question.setPadding(new Insets(10));
		
		GridPane cupsBoughtGrid = new GridPane();
		cupsBoughtGrid.setHgap(10);
		cupsBoughtGrid.setVgap(10);
		cupsBoughtGrid.setPadding(new Insets(50));
		cupsBoughtGrid.setAlignment(Pos.CENTER);
		
		//If user joined loyalty program tell them
		if (joinedLoyalty) {
			
			Label loyalty = new Label("Thank you for joining the loyalty program, 10 point have been added");
			cupsBoughtGrid.add(loyalty, 0, 0);
			
		}
		
		cupsBoughtGrid.add(question, 0, 1);
		cupsBoughtGrid.add(getHappyHourCups, 0, 2);
		cupsBoughtGrid.add(happyCupOptions, 0, 3);
		cupsBoughtGrid.add(submit, 0, 4);
		
		Scene cupsBoughtScene = new Scene(cupsBoughtGrid, stageX, stageY);
		
		//Set and how scene
		primaryStage.setScene(cupsBoughtScene);
		primaryStage.show();
		
		//Event handler when user submits cups bought
		submit.setOnAction(event -> {
			
			//Declare var
			int response = 0;		//Var to hold cups bought
			boolean test = false;	//Var to keep looping if invalid response entered
			//Var to hold total happy hour cups bought for every month
			int happyHourCups = 0;
			
			do {
				
				//Try to convert text entered into int
				try {
					//Convert cups bought to int
					response = Integer.parseInt(cupsBought.getText().trim());
					
					//Throw exception if cups bought is negative
					if (SchuckLab13.validateCups(response) == false) {
						throw new NumberFormatException();
					}
					
				}catch (NumberFormatException e) {		//Invalid cups bought entered
					
					//Tell user to enter a number greater than 0
					System.out.println("Please enter an integer greater than 0.");
					
				}
				
				//Add cups bought to cups array if > 0
				if (response != 0) {
					cups.add(response);
					test = true;
				}
				
				//Add wednesday bonus cups
				if (cup1.isSelected()) {
					
					happyHourCups = 1;
					
				}else if (cup2.isSelected()) {
					
					happyHourCups = 2;
					
				}else if (cup3.isSelected()) {
					
					happyHourCups = 3;
					
				}
				
				SchuckLab13.WednesdayBonusPoints(points, happyHourCups);	
				
			}while(!test);
			
			//Call method to ask if user wants to enter another month
			keepGoing(primaryStage);
			
		});
		
	}
	
	public void keepGoing(Stage primaryStage) {
		
		//Create actors to ask user if they want to enter another month
		Label keepGoing = new Label("Would you like to enter another month?");
		Button yes = new Button("Yes");
		Button no = new Button("No");
		
		//Create sceene for asking the user if they want to keep going
		HBox yesNo = new HBox(yes, no);
		yesNo.setAlignment(Pos.CENTER);
		yesNo.setPadding(new Insets(10));
		
		VBox keepGoingLayout = new VBox(keepGoing, yesNo);
		keepGoingLayout.setAlignment(Pos.CENTER);
		keepGoingLayout.setPadding(new Insets(10));
		
		Scene keepGoingScene = new Scene(keepGoingLayout, stageX, stageY);
		
		//Set and show scene
		primaryStage.setScene(keepGoingScene);
		primaryStage.show();
		
		//Event handler if user wants to enter another month
		yes.setOnAction(action -> {
			
			//Call method again
			cupsBoughtScene(primaryStage);
			
		});
		
		//Event handler if user doesn't want to enter another month
		no.setOnAction(action -> {
			
			//Calculate points
			points.setPoints(cups);
			points.setBonusPoints(cups);
			
			//Call method to get points purchased during happy hour
			printPointsScene(primaryStage);
			
		});
	
	}
	
	/**Method to print points
	 * @param primaryStage
	 * Create actors
	 * Create scene
	 * Set and show scene
	 */
	public void printPointsScene(Stage primaryStage) {
		
		//Create actors
		Label regularPoints = new Label(String.format("You have %.2f points", points.getPoints()));
		Label bonusPoints = new Label(String.format("And out of those points, %.2f are bonus points", points.getBonusPoints()));
		
		
		//Create scene
		VBox pointLabels = new VBox(regularPoints, bonusPoints);
		pointLabels.setAlignment(Pos.CENTER);
		
		GridPane pointsGrid = new GridPane();
		pointsGrid.setAlignment(Pos.CENTER);
		pointsGrid.add(pointLabels, 0, 1);
		
		//If qualifies for volume bonus tell user
		if (points.qualifiesVolumeBonus(cups)) {
			
			//Create label
			Label volumeBonus = new Label("Congratulations! You have qualified for the volume bonus! 9 points have been added");
			pointsGrid.add(volumeBonus, 0, 0);
			
		}
		
		Scene pointScene = new Scene(pointsGrid, stageX, stageY);
		
		//Set and show scene
		primaryStage.setScene(pointScene);
		primaryStage.show();
		
	}
}
