/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is the sub class to create the title card
 */
package gui;

//Imports
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;	//Library for adding shadow effect to actors
import javafx.scene.effect.Glow;		//Library for adding a glowing effect to actors
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class TitleStage extends Stage{
	
	//Initialize objects
	private Stage titleStage;
	private UserInterface ui;
	
	/**Constructor for title card
	 * @param sceneX
	 * @param sceneY
	 * Create stage obj
	 * Create effects
	 * Create actors
	 * Create layout
	 * Create scene
	 * Set stage
	 */
	TitleStage(int sceneX, int sceneY) {
		
		//Create objects
		titleStage = new Stage();
		ui = new UserInterface();
			
		//Create effects
		Glow glow10 = new Glow(10);
		
		DropShadow shadow = new DropShadow();
		shadow.setRadius(5);
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		
		//Create actors for title card
		Label title = new Label("The Farmer");
		title.setId("label-title");
		title.setEffect(glow10);
		
		Label subTitle = new Label("A Farming Simulation");
		subTitle.setId("label-subTitle");
		subTitle.setEffect(shadow);
		
		Button start = new Button("Start");
		start.setId("button");
		start.setEffect(shadow);
		
		//Create layout for title card
		StackPane titleRoot = new StackPane();
		titleRoot.setId("root-titleCard");
		
		VBox titles = new VBox(title, subTitle);
		titles.setAlignment(Pos.CENTER);
		
		VBox layout = new VBox(200);
		layout.setAlignment(Pos.CENTER);
		
		layout.getChildren().addAll(titles, start);
		titleRoot.getChildren().add(layout);
		
		//Create scene
		Scene scene = new Scene(titleRoot, sceneX, sceneY);
		scene.getStylesheets().add(getClass().getResource("/resources/gui.css").toExternalForm());
		
		
		//Set and show stage
		titleStage.setScene(scene);
		
		//Handle when start button is clicked
		start.setOnAction(action -> {
			
			//Close stage
			titleStage.close();
			
			//Call main GUI class to create interface and start game
			ui.startGame();
			
		});
		
	}
	
	//Method to show stage, allows other processes to be completed beforehand
	public void showTitleStage() {
		
		//Show stage
		titleStage.show();
		
	}
	
}

