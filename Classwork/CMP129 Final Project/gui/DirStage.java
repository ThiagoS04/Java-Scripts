/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is the sub class to create the directions stage
 */
package gui;

//Imports
import javafx.stage.Stage;
import java.io.InputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;	//Library for adding shadow effect to actors
import javafx.scene.effect.Glow;		//Library for adding a glowing effect to actors
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DirStage {
	
	//Initialize stage obj
	private Stage dirStage;
	
	/**Constructor for directions pop-up
	 * @param stageX
	 * @param stageY
	 * Create stage obj
	 * Create effects
	 * Create actors
	 * Create border
	 * Create layout
	 * Create scene
	 * Set stage
	 * Handle user pressing ok
	 */
	DirStage(int sceneX, int sceneY) {
		
		//Create stage obj
		dirStage = new Stage();
		
		//Create effects
		Glow glow10 = new Glow(10);
		
		DropShadow shadow = new DropShadow();
		shadow.setRadius(5);
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		
		
		//Create actors
		Label title = new Label("Directions");
		title.setAlignment(Pos.CENTER);
		title.setId("label-title");
		title.setEffect(glow10);
		
		String directionsText = "This is a farming simulator\n"
				+ "You will have to plant and harvest different kinds of crops\n"
				+ "You'll start off with potatos and learn how to farm other crops\n"
				+ "Each crop requires different amounts food and water\n"
				+ "A pop-up icon will tell you what each crop needs\n"
				+ "Click on the icon to feed or water it\n"
				+ "Click on a dirt square to plant a crop\n";
		
		Label directionsLabel = new Label(directionsText);
		directionsLabel.setTextAlignment(TextAlignment.CENTER);
		directionsLabel.setFont(Font.font("fantasy"));
		directionsLabel.setEffect(shadow);
		directionsLabel.setId("label-subStageTitleText");
		directionsLabel.setPadding(new Insets (40));
		
		Button ok = new Button("OK");
		ok.setAlignment(Pos.CENTER);
		ok.setId("button");
		ok.setEffect(shadow);
		
		//Create border
		InputStream imagePathHor = getClass().getResourceAsStream("/resources/border-design.png");
		InputStream imagePathVer = getClass().getResourceAsStream("/resources/border-design-sides.png");
		
		Image horImage = new Image(imagePathHor, sceneX, 200, false, false);
		Image verImage = new Image(imagePathVer, 200, sceneY, false, false);
		
		ImageView topImageView = new ImageView(horImage);
		ImageView bottomImageView = new ImageView(horImage);
		ImageView leftImageView = new ImageView(verImage);
		ImageView rightImageView = new ImageView(verImage);
		
		//Create layout
		StackPane directionsRoot = new StackPane();
		directionsRoot.setId("root-subCard");
		
		VBox directions = new VBox(40, title, directionsLabel, ok);
		directions.setAlignment(Pos.CENTER);
		
		directionsRoot.getChildren().add(directions);
		directionsRoot.getChildren().addAll(topImageView, bottomImageView, leftImageView, rightImageView);
		
		StackPane.setAlignment(topImageView, Pos.TOP_CENTER);
		StackPane.setMargin(topImageView, new Insets(-75, 0, 0, 0));
		
		StackPane.setAlignment(bottomImageView, Pos.BOTTOM_CENTER);
		StackPane.setMargin(bottomImageView, new Insets(0, 0, -75, 0));
		
		StackPane.setAlignment(leftImageView, Pos.CENTER_LEFT);
		StackPane.setMargin(leftImageView, new Insets(0, 0, 0, -75));
		
		StackPane.setAlignment(rightImageView, Pos.CENTER_RIGHT);
		StackPane.setMargin(rightImageView, new Insets(0, -75, 0, 0));
		
		//Create scene
		Scene directionsScene = new Scene(directionsRoot, sceneX, sceneY);
		directionsScene.getStylesheets().add(getClass().getResource("/resources/gui.css").toExternalForm());
		
		//Set stage
		dirStage.setScene(directionsScene);
		
		//Handle pressing ok
		ok.setOnAction(action -> {
			
			//Close stage
			dirStage.close();
			
		});
		
	}
	
	//Method to show stage, allows other processes to be completed beforehand
	public void showDirections() {
		
		//Show stage
		dirStage.show();
		
	}

}

