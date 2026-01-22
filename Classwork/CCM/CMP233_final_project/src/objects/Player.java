/** Thiago Schuck 7 May 2024
 This program is a chess game against a player or an AI
 This is the player object class*/
package objects;

public class Player {

	//Declare fields
	private String name;
	private static String color;
	private boolean checkmate = false;
	
	//Default constructor
	public Player() {
		
		//Fill fields
		name = "default";
		color = "gray";
		checkmate = false;
		
		//Create pieces
		createPieces();
		
	}
	
	//Constructor with name and color
	public Player(String name, String color) {
		
		//Fill fields
		this.name = name;
		this.color = color;
		checkmate = false;
		
		//Create pieces
		createPieces();
		
	}
	
	/** Method to create pieces
	 * Create king, queen
	 * Loop through twice creating rooks, bishops, and knights
	 * Loop through 10 times creating pawns
	 */
	private static void createPieces() {
		
		//Create king and queen
		Piece king = new King(color);
		Piece queen = new Queen(color, false);		//color, promotion boolean
		
		//Create rooks, bishops, and knights
		for (int i = 2; i < 4; i++) {			//King and queen take up indices 0,1 in pieces array
			
			//Create rook
			Piece rook = new Rook(color, i-1, false);		//Color of piece and which iteration, -1 for more accurate number (2 pieces each)
			
			//Create knight
			Piece knight = new Knight(color, i-1, false);
			
			//Create bishop
			Piece bishop = new Bishop(color, i-1, false);
			
		}
		
		//Create pawns
		for (int i = 11; i <= 18; i++) {			//Major and minor pieces take up indices 0-9
			
			//Create pawn
			Pawn pawn = new Pawn(color, i-10);			//-10 for more accurate number (1-8 for each file)
			
		}
		
		//Call method to find possible moves for each piece
		
	}
	
	//Getter methods
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean getCheckmate() {
		return checkmate;
	}
	
	//Setter methods
	public void setCheckmate(boolean checkmate) {
		this.checkmate = checkmate;
	}
	
}
