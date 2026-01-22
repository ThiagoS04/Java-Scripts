/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is the sub class to create the store stage
 */

package gui;

import java.io.InputStream;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;

public class Store {

	//Declare variables
	private Stage storeStage;
	private final String[] cropsList = {"Potato", "Radish", "Wheat"};
	private final int[] prices = Main.getPurchasePrices();
	
	/**Constructor for store pop-up
	 * @param stageX
	 * @param stageY
	 * Create stage obj
	 * Create effects
	 * Create actors
	 * Create listeners
	 * Create border
	 * Create layout
	 * Create scene
	 * Set stage
	 */
	Store(int sceneX, int sceneY) {
		
		//Create stage obj
		storeStage = new Stage();
		
		//Create effects
		Glow glow10 = new Glow(10);
		
		DropShadow shadow = new DropShadow();
		shadow.setRadius(5);
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		
		//Create actors
		Label[] labels = new Label[cropsList.length];
		VBox[] buttons = new VBox[cropsList.length];
		
		for(int i = 0; i < cropsList.length; i++) {		//Create buy label and button for each crop
			
			//Create label and button
			Label crop = new Label(cropsList[i] + "\nCost: " + prices[i] + " gold");
			crop.setId("label-subTitle");
			crop.setAlignment(Pos.CENTER);
			crop.setTextAlignment(TextAlignment.CENTER);
			
			//Buy button
			Button buy = new Button("Buy " + cropsList[i]);
			buy.setId("button");
			buy.setEffect(shadow);
			
			//Sell button
			Button sell = new Button("Sell " + cropsList[i]);
			sell.setId("button");
			sell.setEffect(shadow);
			
			VBox buySell = new VBox(10, buy, sell);
			//Create listener for buttons
			buy.setOnAction(event -> {
				
				//Get parent vbox
				VBox clickedVBox = (VBox) buy.getParent();
				
				//Call method to handle purchase
				purchaseCrop(clickedVBox);
				
			});
			
			sell.setOnAction(event -> {
				
				//Get parent vbox
				VBox clickedVBox = (VBox) buy.getParent();
				
				//Call method to handle selling crop
				sellCrop(clickedVBox);
				
			});
			
			//Save reference
			labels[i] = crop;
			buttons[i] = buySell;
			
		}
		
		Button exitButton = new Button("Exit");
		exitButton.setId("button");
		
		//Create listener for exit button
		exitButton.setOnAction(event -> {
			
			storeStage.close();
			
		});
		
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
		StackPane storeRoot = new StackPane();
		storeRoot.setId("root-subCard");
		
		VBox[] items = new VBox[cropsList.length];
		for(int i = 0; i < cropsList.length; i++) {		//Fill vbox
			
			//Create vbox for each crop
			VBox item = new VBox(25, labels[i], buttons[i]);
			item.setAlignment(Pos.CENTER);
			
			//Add item to vbox
			items[i] = item;
			
		}
		
		HBox store = new HBox(50);
		for (VBox item : items) {		//Fill hbox
			
			store.getChildren().add(item);
			
		}
		store.setAlignment(Pos.CENTER);
		
		storeRoot.getChildren().add(store);
		storeRoot.getChildren().addAll(topImageView, bottomImageView, leftImageView, rightImageView);
		
		//Configure stackpane
		StackPane.setAlignment(store, Pos.CENTER);
		StackPane.setMargin(store, new Insets(50));
		
		StackPane.setAlignment(topImageView, Pos.TOP_CENTER);
		StackPane.setMargin(topImageView, new Insets(-75, 0, 0, 0));
		
		StackPane.setAlignment(bottomImageView, Pos.BOTTOM_CENTER);
		StackPane.setMargin(bottomImageView, new Insets(0, 0, -75, 0));
		
		StackPane.setAlignment(leftImageView, Pos.CENTER_LEFT);
		StackPane.setMargin(leftImageView, new Insets(0, 0, 0, -75));
		
		StackPane.setAlignment(rightImageView, Pos.CENTER_RIGHT);
		StackPane.setMargin(rightImageView, new Insets(0, -75, 0, 0));
		
		//Create scene
		Scene storeScene = new Scene(storeRoot, sceneX, sceneY);
		storeScene.getStylesheets().add(getClass().getResource("/resources/gui.css").toExternalForm());
		
		//Set stage
		storeStage.setScene(storeScene);
		storeStage.show();
		
	}
	
	//Method to purchase a crop
	private void purchaseCrop(VBox clickedVBox) {
		
		//Declare fields
		Button buyButton = null;
		Label cropLabel = null;
		String crop = "";
		VBox parentVBox = (VBox) clickedVBox.getParent();
		
		//Loop through nodes of vbox to create references
		for (Node node : parentVBox.getChildren()) {
			
			if (node instanceof VBox) {
				
				if (node == clickedVBox) {
					
					for(Node vboxNode : clickedVBox.getChildren()) {	//Loop through children in inner vbox to find buy button
						
						if (vboxNode instanceof Button) {

							if (((Button) vboxNode).getText().startsWith("Buy")) {	//Check if node found is buy button
								
								buyButton = (Button) vboxNode;
								
							}
							
						}
						
					}
					
				}
				
			}else if (node instanceof Label) {
				
				cropLabel = (Label) node;		//Create reference for label node
			}
			
		}
		
		class ShortMoney {
			
			//Text telling user they don't have money to VBox
			static final Label errorText = new Label("You don't have enough money");
			
			public static void createErrorText(Button button, VBox clickedVBox) {
				
				//Add text to vbox
				clickedVBox.getChildren().add(clickedVBox.getChildren().indexOf(button) + 1, errorText);	//Add error text
				
				//Create disappearing effect
				Timeline timeline = new Timeline();
				KeyValue endOpacity = new KeyValue(errorText.opacityProperty (), 0);
				KeyFrame timeToDisappear = new KeyFrame(Duration.seconds(2), endOpacity);
				timeline.getKeyFrames().add(timeToDisappear);
				timeline.play();
				
				//Remove text from vbox
				clickedVBox.getChildren().remove(errorText);
			}
			
		}
		
		if (Main.gold > 0) {		//Check if user has enough money to buy any crop
			
			//Get crop from label
			crop = cropLabel.getText().split("\n")[0];
			
			//Loop through crops list to see which crop user selected
			for (int i = 0; i < cropsList.length; i++) {
				
				if (crop.equals(cropsList[i])) {	//Find crop in cropsList
					
					if (Main.gold >= prices[i]) {	//Check if user has enough money
						
						//Take money from user and add crops in inventory
						Main.gold -= prices[i];
						Main.updateInventory(crop, 1);
						break;
						
					}else {
						
						//Call method to tell user they don't have money
						ShortMoney.createErrorText(buyButton, clickedVBox);
						
					}
					
				}
				
			}
			
		}else { 
			
			//Call method to tell user they don't have money
			ShortMoney.createErrorText(buyButton, clickedVBox);
			
		}
		
	}
	
	private void sellCrop(VBox clickedVBox) {
		
		//Declare fields
		Button buyButton = null;
		Label cropLabel = null;
		String crop = "";
		VBox parentVBox = (VBox) clickedVBox.getParent();
		
		//Loop through nodes of vbox to create references
		for (Node node : parentVBox.getChildren()) {
			
			if (node instanceof Label) {
				
				cropLabel = (Label) node;		//Create reference for label node
				
			}
			
		}
		
		//Get crop from label
		crop = cropLabel.getText().split("\n")[0];
		
		//Loop through crops list to see which crop user selected
		for (int i = 0; i < cropsList.length; i++) {
		
			//Check if iterated crop is what user selected
			if (cropsList[i].equals(crop)) {
				
				//Check if user has crop to sell
				if (Main.inventory.get(crop) != 0) {
					
					//Remove crop from inventory
					Main.updateInventory(crop, -1);
					
					//Add gold
					Main.gold += prices[i];
					
				}
				
			}
			
		}
		
	}
	
	//Method to show stage, allows other processes to be completed beforehand
	public void showStore() {
		
		//Show stage
		storeStage.show();
		
	}
	
}
