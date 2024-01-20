/**Thiago Schuck November 13 2023
 * This program is the final project of CMP129
 * It's purpose is to create a game for the user to play using everything I learned in class
 * This game is a planting game
 * This is the class for the main GUI
 */
package gui;

//Imports
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.InputStream;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import main.Plant;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class UserInterface extends Application {

	//Declare global variables
	private static int primarySceneX = 1350, primarySceneY = 850;
	private static int secondarySceneX = 600, secondarySceneY = 500;
	private static Stage primaryStage;
	private static Scene scene;
	private static GridPane plants;
	private static Label inventory = new Label();
	private static ListView<String> inventoryList = new ListView<>();
	
	public void start(Stage stage) {
		
		//Set stage and style sheet
		primaryStage = stage;
		
		//Create title card and directions stage
		TitleStage titleCard = new TitleStage(secondarySceneX, secondarySceneY);
		
		//Show title card
		titleCard.showTitleStage();
		
	}
	
	/**Method to create interface and start game
	 * Create actors
	 * Create listeners
	 * Create layout
	 * Create scene
	 * Set and show stage
	 * Show directions
	 */
	public void startGame() {
		
		//Create effects
		DropShadow shadow = new DropShadow();
		shadow.setRadius(5);
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		
		//Create actors
		InputStream dirtStream = getClass().getResourceAsStream("/resources/dirt.jpg");
		Image dirtImage = new Image(dirtStream, 120, 120, false, false);
		
		InputStream grassStream = getClass().getResourceAsStream("/resources/grass.jpg");
		Image grassImage = new Image(grassStream, 850, 850, false, false);
		ImageView grass = new ImageView(grassImage);
		
		Button directions = new Button("Directions");
		directions.setId("button");
		directions.setEffect(shadow);
		
		Button store = new Button("Store");
		store.setId("button");
		store.setEffect(shadow);
		
		//Create listeners
		//Listener for directions button
		directions.setOnAction(event -> {
			
			DirStage tempDirectionsStage = new DirStage(secondarySceneX, secondarySceneY);
			tempDirectionsStage.showDirections();
			
		});
		
		//Listener for store Button
		store.setOnAction(event -> {
			
			Store storeStage = new Store(secondarySceneX + 200, secondarySceneY);
			storeStage.showStore();
			
		});
		
		inventoryList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setInventory();		//Method to set inventory label and list
		
		//Create layout
		BorderPane root = new BorderPane();
		
		StackPane field = new StackPane();
		field.getChildren().add(grass);
		
		VBox rightMargin = new VBox(200, directions, inventory, store);
		rightMargin.setAlignment(Pos.CENTER);
		
		int xfield = 5;			//how many spots of dirt there are left to right
		int yfield = 5;			//how many spots of dirt there are up to down
		plants = new GridPane();
		plants.setAlignment(Pos.CENTER);
		
		//Fill gridpane
		for (int i = 0; i < yfield; i++) {
			
			for (int j = 0; j < xfield; j++) {
				
				ImageView dirt = new ImageView(dirtImage);
				dirt.setUserData("dirt");
				
				HBox needs = new HBox(10);
				
				AnchorPane plant = new AnchorPane();
				plant.getChildren().addAll(dirt, needs);
				plant.setPadding(new Insets(10));
				plant.setPadding(new Insets(10));
				plant.setOnMouseClicked(dirtClicked);
				
				plants.add(plant, i, j);
				
			}
			
		}
		
		field.getChildren().add(plants);
		
		root.setCenter(field);
		root.setRight(rightMargin);
		BorderPane.setAlignment(rightMargin, Pos.CENTER_RIGHT);
		BorderPane.setMargin(rightMargin, new Insets(30));
		
		root.setId("root-main");
		
		//Create scene
		scene = new Scene(root, primarySceneX, primarySceneY);
		scene.getStylesheets().add(getClass().getResource("/resources/gui.css").toExternalForm());
		
		//Set and show stage
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Create and show directions
		DirStage directionsStage = new DirStage(secondarySceneX, secondarySceneY);
		directionsStage.showDirections();
		
	}
	
	EventHandler<MouseEvent> dirtClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			
			//Get location of event
			int xPos = GridPane.getRowIndex((Node) event.getSource());
			int yPos = GridPane.getColumnIndex((Node) event.getSource());
			int[] gridPos = {xPos, yPos};
			int pos = Integer.parseInt(String.valueOf(gridPos[0]) + String.valueOf(gridPos[1]));
			
			//Check if inventory is empty before displaying listview and there isn't already a crop planted
			if (!inventoryList.getItems().isEmpty() && Main.references.get(pos) == null) {
				
				//Creating a temporary stage to display listview on
				Stage tempStage = new Stage();
				tempStage.initStyle(StageStyle.TRANSPARENT);
				tempStage.setX(event.getSceneX());
				tempStage.setY(event.getSceneY());
				
				StackPane tempScene = new StackPane(inventoryList);
				tempScene.setStyle("-fx-background-color: transparent;");
				
				Scene listViewScene = new Scene(tempScene, 100, 100, Color.TRANSPARENT);
				
				tempStage.setScene(listViewScene);
				tempStage.show();
				
				//Create listener to close stage when user clicks outside of stage
				tempStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
					
					if (!isNowFocused) {
						tempStage.close();
					}
					
				});
				
				//Create listener for listview so user can choose crop to plant
				inventoryList.setOnMouseClicked(selection -> {
					
					//Set parameters
					String crop = inventoryList.getSelectionModel().getSelectedItem();
					if (crop != null) {		//Check if user clicked on an item or empty space
						
						//Plant crop
						plantCrop(crop, gridPos);
						
						//Close listview
						tempStage.close();
						
					}
					
				});
				
			}
			
		}
		
	};
	
	//Method to set inventory in the beginning of game and after planting or harvesting
	public static void setInventory() {
		
		//Create fields
		String titles = "Inventory\nCrop	Amount\n";
		String separator = "\n";
		for (int i = 0; i < titles.length(); i++) {
			
			separator = "-" + separator;
			
		}
		String gold = "\nGold: " + Main.gold;
		String inventoryContent = titles + separator;
		
		if (Main.inventory.isEmpty()) {		//Check if inventory is empty
			
			inventoryContent += "Empty";
			
		}else {
		
			for (String crop : Main.inventory.keySet()) {		//Fill inventory content
				
				inventoryContent += crop + ":	" + Main.inventory.get(crop) + "\n";
				
			}
			
		}
		
		//Set inventory label
		inventory.setText(inventoryContent + gold);
		
		//Set inventory listview
		for (String key : Main.inventory.keySet()) {		//Loop through all the items in inventory
			
			//If key is not in inventoryList and is not 0, add key
			if (!inventoryList.getItems().contains(key) && Main.inventory.get(key) != 0) {
				
				inventoryList.getItems().add(key);
				
			}
			
		}
	}
	
	/**Method to create need icon
	 * @param need
	 * @param pos
	 * Create actors
	 * Determine which need is being created
	 * Create image
	 * Set imageView to that need
	 * Set shape of image
	 * Display image
	 */
	public void createNeedIcon(String need, int[] gridPos) {
		
		//Create actors
		ImageView needImage = new ImageView();
		needImage.setId("need-icon");
		
		InputStream food = getClass().getResourceAsStream("/resources/food.jpg");
		InputStream water = getClass().getResourceAsStream("/resources/water.jpg");
		
		/*Create listener for image
		 * Get potato object at location
		 * Call method
		 */
		EventHandler<MouseEvent> needClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				ImageView clickedImageView = (ImageView) event.getSource();		//Get clicked imageview
				String need = (String) clickedImageView.getUserData();		//Get user data of clicked image view
				AnchorPane plant = (AnchorPane) clickedImageView.getParent();		//Get anchorpane imageview is in
				
				//Get location of image view
				int xPos = GridPane.getRowIndex(plant);		
				int yPos = GridPane.getColumnIndex(plant);
				int[] gridPos = {xPos, yPos};
				int pos = Integer.parseInt(String.valueOf(gridPos[0]) + String.valueOf(gridPos[1]));
				
				//Get obj in the same anchorpane as the image view
				for (int loc : Main.references.keySet()) {
					
					if (pos == loc) {
						
						Plant wantedObj = Main.references.get(pos);
						
						//Call need
						if (need.equals("water")) {
							
							wantedObj.setNeedsWater(true);		//Tells the method the need is being met
							wantedObj.needWater();
							
						}else if (need.equals("food")) {
							
							wantedObj.setNeedsFood(true);
							wantedObj.needFood();
							
						}
						
					}
					
				}
				
			}
			
		};
				
		//Determine need
		if (need.equals("food")) {				//Need food
			
			//Create image
			Image image = new Image(food, 45, 45, false, false);
			
			//Set imageView to need
			needImage.setImage(image);
			needImage.setUserData("food");
			needImage.setOnMouseClicked(needClicked);
			
		}else if (need.equals("water")) {		//Need water
			
			//Create image
			Image image = new Image(water, 45, 45, false, false);
			
			//Set imageView to need
			needImage.setImage(image);
			needImage.setUserData("water");
			needImage.setOnMouseClicked(needClicked);
			
		}else {			//Invalid need
			
			//Print error if wrong need is sent
			System.out.println("Error: invalid need");
			
		}
		
		//Call method to display image
		displayImage(needImage, gridPos, true);
		
	} 
	
	/**Method for planting a crop
	 * @param crop
	 * @param xPos
	 * @param yPos
	 * Create crop object
	 * Create Image for crop
	 * Update inventory
	 */
	private void plantCrop(String crop, int[] gridPos) {
		
		//Call method to create object
		Main.createCrop(crop, gridPos);
		
		//Create image var
		InputStream cropInputStream;
		Image cropImage;
		ImageView cropImageView = new ImageView();
		cropImageView.setUserData("Crop");
		
		//Check which crop to create an image for
		switch(crop) {
		
		case "Potato":
			
			//Create potato image
			cropInputStream = getClass().getResourceAsStream("/resources/potato.png");
			cropImage = new Image(cropInputStream, 80, 80, false, false);
			cropImageView.setImage(cropImage);
			break;
			
		case "Radish":
			
			//Create radish image
			cropInputStream = getClass().getResourceAsStream("/resources/radish.png");
			cropImage = new Image(cropInputStream, 80, 80, false, false);
			cropImageView.setImage(cropImage);
			break;
			
		case "Wheat":
			
			//Create wheat image
			cropInputStream = getClass().getResourceAsStream("/resources/wheat.png");
			cropImage = new Image(cropInputStream, 80, 80, false, false);
			cropImageView.setImage(cropImage);
			break;
			
		default:	//Crop not found
			
			System.out.println("Crop not found");
			break;
			
		}
			
		//Call method to display image
		displayImage(cropImageView, gridPos, false);
		
		//Update inventory
		Main.updateInventory(crop, -1);
		if (Main.inventory.get(crop) == 0) {
			
			inventoryList.getItems().remove(crop);
			
		}
		
	}
	
	/**Method to display an image on the field gridpane
	 * @param imageView
	 * @param gridPos
	 * @param restrict
	 * Loop through all nodes in field to find wanted node
	 * Check if node is an anchorpane
	 * Check if image should be restricted to top or left
	 * If not restrict to center
	 * Add image to anchorpane
	 */
	private void displayImage(ImageView imageView, int[] gridPos, boolean restrict) {
		
		//Display image
		for (Node node: plants.getChildren()) {		//Check all elements in GridPane for wanted VBox
			
			if (GridPane.getRowIndex(node) == gridPos[0] && GridPane.getColumnIndex(node) == gridPos[1]) {		//Check if node is wanted node
				
				if (node instanceof AnchorPane) {		//Check if node found is AnchorPane
					
					AnchorPane plant = (AnchorPane) node;	//Cast node to Anchorpane to edit
					
					if (restrict) {		//Restrict to top left or top right
						
						//Restrict
						AnchorPane.setTopAnchor(imageView, 0.0);	//Restrict image to top
						
						if(imageView.getUserData().equals("food")) {	//Restrict food image to left and water image to right
							
							AnchorPane.setLeftAnchor(imageView, 0.0);
							
						}else {
							
							AnchorPane.setRightAnchor(imageView, 20.0);
							
						}
						
					}else {		//Restrict to center
						
						AnchorPane.setTopAnchor(imageView, 10.0);
						AnchorPane.setRightAnchor(imageView, 30.0);
						
					}
						
					//Add image to anchorpane
					plant.getChildren().add(imageView);
					imageView.toFront();
					break;
					
				}else {
					
					System.out.println("Error: AnchorPane not found.");
					
				}
				
			}
		}
		
	}
	
	/**Method to remove icon when need is met or when plant dies
	 * @param icon
	 * @param gridPos
	 */
	public static void removeIcon(String icon, int[] gridPos, boolean remove) {
		
		//Remove images
		for (Node node : plants.getChildren()) {		//Check all elements in GridPane for wanted VBox
		
			if (GridPane.getRowIndex(node) == gridPos[0] && GridPane.getColumnIndex(node) == gridPos[1]) {		//Check if node is wanted node
				
				if (node instanceof AnchorPane) {		//Check if node found is AnchorPane
					
					AnchorPane plant = (AnchorPane) node;	//Cast AnchorPane to node to get children
					
					
					plant.getChildren().removeIf(anchorNode -> {
						
						if (anchorNode instanceof ImageView) {
							
							ImageView imageNode = (ImageView) anchorNode;	//Cast ImageView to anchorpane to edit
							
							switch ((String) imageNode.getUserData()) {
							
							case "dirt":		//Check if ImageView is dirt
								
								return false;		//Don't remove from dirt anchorpane
							
							case "water":		//Check ImageView is food
								
								if (icon.equals("water") || remove) {		//Check wanted ImageView is water
									
									return true;
									
								}
								break;
								
							case "food":		//Check ImageView is food
								
								if (icon.equals("food") || remove) {		//Check wanted ImageView is food
									
									return true;
									
								}
								break;
								
							case "Crop":		//Check if ImageView is a crop
								
								if (remove) {		//Check wanted ImageView is a crop
									
									return true;
									
								}
								break;
								
							default:
								
								System.out.println("User data did not match");
								
							}
							
						}
						
						return false;
					});
					
				}else {
					
					System.out.println("Error: AnchorPane not found.");
					
				}
				
			}
		}
		
	}
	
}