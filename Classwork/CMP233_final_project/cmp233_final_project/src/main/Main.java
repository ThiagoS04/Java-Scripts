/** Thiago Schuck 7 May 2024
 This program is a chess game against a player or an AI
 This is the client class*/
package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import objects.*;

public class Main {
	
	public static void main(String[] args) {
		
		//Declare fields
		String playerOneName, playerTwoName;
		String playerOneColor, playerTwoColor;
		String move = null;				//[piece name][location of piece] [location to move to]
		String turn = "";
		String promotingTo;
		String startSquare;
		String endSquare;
		String[] startRowAndFile, endRowAndFile;
		String startFile, endFile;
		
		Scanner userInput = new Scanner(System.in);
		Random random = new Random();
		
		boolean inputError = false;
		boolean whiteCheckMate = false;
		
		int moveCount = 1;			//Player moves, white will be odd moves, black will be even
		int startRow, endRow;
		
		LinkedList<String> movesList = new LinkedList<String>();		//Linked list to hold moves
		
		//Print rules
		printRules();
		
		//Get player one name
		System.out.println("What is player one's name?");
		playerOneName = userInput.nextLine();
		
		//Get player two name
		System.out.println("What is player two's name?");
		playerTwoName = userInput.nextLine();
		
		//Get player color
		System.out.println("Would " + playerOneName + " like to play as white, black, or a random color?");
		
		do {
			
			playerOneColor = userInput.nextLine().toUpperCase();
			
			//Validate input
			if (!(playerOneColor.equals("WHITE")) && !(playerOneColor.equals("BLACK")) && !(playerOneColor.equals("RANDOM"))) {
				
				inputError = true;
				System.out.println(playerOneColor + " is an invalid player color, please enter white, black, or random.\n"
						+ "Enter player color:");
				
			} else {
				
				inputError = false;
				
				//Determine player color if random
				if (playerOneColor.equals("RANDOM")) {
					
					//Randomly generate number
					int randomNum = random.nextInt();
					
					//If random number is even set color to white otherwise black
					if (randomNum % 2 == 0) {
						
						playerOneColor = "WHITE";
						
					} else {
						
						playerOneColor = "BLACK";
						
					}
					
				}
				
			}
			
		} while (inputError);
		
		//Set second player's color
		if (playerOneColor.equals("WHITE")) {
			
			playerTwoColor = "BLACK";
			
		} else {
			
			playerTwoColor = "WHITE";
			
		}
		
		//Notify user players of their colors
		System.out.println(playerOneName + " will play as " + playerOneColor + ".\n"
				+ playerTwoName + " will play as " + playerTwoColor + ".");
		
		//Create player objects
		Player playerOne = new Player(playerOneName, playerOneColor);
		Player playerTwo = new Player(playerTwoName, playerTwoColor);
		
		//Initialize possible piece moves
		ChessBoard.initializePossibleMoves();
		
		//Tell players how to make a move
		System.out.println("To make a move, type the letter representing the piece, followed by it's location, if it's a rook, knight, bishop, or pawn, then type the location to move it to.\n"
				+ "Separate the square location with a '-' and put a space between each square location like this: Nb-1 c-3\n"
				+ "White plays first.");
		
		//Loop while neither player is in checkmate
		while(!(playerOne.getCheckmate()) && !(playerTwo.getCheckmate())) {
		
			//Print chess board
			ChessBoard.printBoard();
			
			//Loop until valid move is entered
			do {
				
				//Tell users who's move it is
				if (moveCount % 2 == 1) {
					
					turn = "WHITE";
					
				} else {
					
					turn = "BLACK";
					
				}
				
				System.out.println("It is " + turn + "'s turn");

				//Get a move
				System.out.println("Please enter a move:");
				move = userInput.nextLine();

				//Validate move
				if(validateMove(move, turn)) {			//Valid move
					
					inputError = false;
					
					//Get start and end square
					startSquare = getStartSquare(move);
					endSquare = getEndSquare(move);
					
					//Get row and file from end square
					endRowAndFile = getRowAndFile(endSquare);
					endFile = endRowAndFile[0];
					endRow = Integer.parseInt(endRowAndFile[1]);
					
					//Get row and file from start square
					startRowAndFile = getRowAndFile(startSquare);
					startFile = startRowAndFile[0];
					startRow = Integer.parseInt(startRowAndFile[1]);
					
					//Create references to pieces on starting and ending square
					Piece endSquarePiece = ChessBoard.getPiece(endFile, endRow - 1);
					Piece pieceToMove = ChessBoard.getPiece(startFile, startRow - 1);
					
					//Check if piece is capturing a king
					if (endSquarePiece instanceof King) {
						
						//Check which color is in checkmate
						if (endSquarePiece.getColor().equals("WHITE")) {
								
							whiteCheckMate = true;
							
						}
						
						//Check which player is player checkmated color
						if (playerOne.getColor().equals("WHITE") && whiteCheckMate) {		//Player one white, white checkmate
							
							playerOne.setCheckmate(true);
							
						} else if (playerOne.getColor().equals("WHITE")) {				//Player one white, black checkmate
							
							playerTwo.setCheckmate(true);
							
						} else if (playerTwo.getColor().equals("WHITE") && whiteCheckMate) {		//Player two white, white check mate
							
							playerTwo.setCheckmate(true);
							
						} else {
							
							playerOne.setCheckmate(true);			//Player one not black and black checkmate
							
						}
						
					}
					
					//Check if piece is promoting pawn
					if (pieceToMove instanceof Pawn && (((turn.equals("WHITE") && endRow == 8)) || (turn.equals("BLACK") && endRow == 1))) {
						
						//Get piece player wants to promote to
						System.out.println("What would you like to promote to?");
						promotingTo = userInput.nextLine();
						
						do {
							
							if (validatePiece(promotingTo) && (!(promotingTo.equals("K")) && !(promotingTo.equals("P")))) {
								
								//Call method to promote pawn
								((Pawn) pieceToMove).promote(promotingTo);
								inputError = false;
								
							} else {
								
								inputError = true;
								System.out.println(promotingTo + " is an invalid piece, please enter 'Q', 'R', 'B', or 'N'.");
								
							}
							
						} while(inputError);
						
					}
					
					//Call method to move piece
					movePiece(pieceToMove, endSquare);
					
					//Increment move count
					moveCount++;
					
					//Add move to movesList
					movesList.add(move);
					
					//Remove buffer
					userInput.nextLine();
					
					
				} else {			//Invalid move
					
					inputError = true;
					System.out.println("That is an invalid move, please enter again.");
					
				}
				
			} while(inputError);
	
		}
		
		//Print who won game
		if (playerOne.getCheckmate()) {
			
			System.out.println("Congratulations, " + playerTwo.getName() + "! You checkmated " + playerOne.getName());
			
		} else {
			
			System.out.println("Congratulations, " + playerOne.getName() + "! You checkmated " + playerTwo.getName());
			
		}
		
		//Print moves list
		for (int i = 1; i <= moveCount; i++) {			//Loop through every move
			
			//Print move
			System.out.println(i + ". " + movesList.get(i - 1));			//-1 to avoid index out of bounds error
			
		}
		
		//Close scanner
		userInput.close();
		
	}
	
	/** Method to print rules*/
	public static void printRules() {
		
		System.out.println("Piece color is represented by 'W' for white and 'B' for black.\n"
				+ "Kings can move 1 square in any direction and are represented by 'K'.\n"
				+ "Queens can move vertically, horizontally, or diagonally, up until they reach the edge of the board or another piece. Queens are represented by 'Q'.\n"
				+ "Rooks can move horizontally or vertically up until they reach the edge of the board or another piece. They are represented by 'R'.\n"
				+ "Bishops can move diagonally up until they reach the edge of the board or another piece. They are represented by 'B'.\n"
				+ "Knights move in L's, they can move 2 spaces horizontally or vertically and then 1 space perpendicularly. They are represented by 'N'.\n"
				+ "Pawns can move one square forward or 2 if they have not moved yet, and they capture in diagonally front of them or to the left or right with en passant. They are represented by 'P'.\n"
				+ "A pawn may only capture another pawn with en passant, and only after the pawn makes a 2 space advance and lands adjacent to the pawn.\n");
		
	}
	
	/** Method to validate move
	 * @param move - [Piece][file]-[row] [file]-[row]
	 * @return true if move is valid
	 * 
	 */
	public static boolean validateMove(String move, String turn) {
		
		//Create boolean to store validity of move, starting square, and ending square
		boolean validMove = false;
		boolean validStartSquare = false;
		boolean validEndSquare = false;
		
		//Determine if move has space
		if (move.contains(" ") && move.length() == 8) {

			//Split move into piece/current square and square to move to
			String pieceName = getPieceNameFromMove(move);
			String startSquare = getStartSquare(move);
			String endSquare = getEndSquare(move);
			
			//Validate squares if piece is valid
			if (validatePiece(pieceName)) {

				//Validate starting square if piece is a rook, bishop, knight, or pawn
				switch (pieceName) {
				
				case "R":
					
					//Check validity of starting square
					validStartSquare = validateSquare(startSquare);
					break;
					
				case "B":
					
					//Check validity of starting square
					validStartSquare = validateSquare(startSquare);
					break;
					
				case "N":
					
					//Check validity of starting square
					validStartSquare = validateSquare(startSquare);
					break;
					
				case "P":
					
					//Check validity of starting square
					validStartSquare = validateSquare(startSquare);
					break;
					
				default:
					
					validStartSquare = true;		//Only one king and queen piece\
					break;
				
				}
				
				//Check validity of ending square
				validEndSquare = validateSquare(endSquare);

				//Check if move is valid (valid start and end square, piece can move to end square)
				if (validStartSquare && validEndSquare && possibleMove(startSquare, endSquare)) {

					//Get row and file of piece
					String file = getRowAndFile(startSquare)[0];
					int row = Integer.parseInt(getRowAndFile(startSquare)[1]);
					
					//Check if piece is on the board and is the same piece
					if (ChessBoard.getPiece(file, row - 1) != null) {

						//Create reference to piece
						Piece piece = ChessBoard.getPiece(file, row - 1);
						
						//Check if piece is the same type and color
						if (piece.getPieceName().equals(pieceName) && piece.getColor().equals(turn)) {

							validMove = true;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return validMove;
		
	}
	
	/** Method to validate piece
	 * @param piece to validate
	 * @return true/false - validity of piece
	 * Compares piece to a list of possible pieces
	 * Returns true if piece is in list
	 */
	private static boolean validatePiece(String piece) {
		
		//Create list of possible pieces
		String[] pieceNames = {"K", "Q", "R", "B", "N", "P"};
		
		//Validate piece
		for (int i = 0; i < pieceNames.length; i++) {			//Loop through each possible piece
			
			if (piece.equals(pieceNames[i])) {		//Return true if piece is in possible piece list
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	/** Method to check validity of square
	 * @param square
	 * @return validity
	 * Check if syntax is correct
	 * Check validity of file and row
	 */
	private static boolean validateSquare(String square) {
		
		//Create boolean to store validity
		boolean validSquare = false;
		
		//Check if square has -
		if (square.contains("-")) {
			
			//Get row and file
			String file = getRowAndFile(square)[0];
			int row = Integer.parseInt(getRowAndFile(square)[1]);
			
			//Check validity of file and row
			if (validateFile(file) && validateRow(row)) {
				
				validSquare = true;
				
			}
			
		}
		
		return validSquare;
		
	}
	
	/** Method to validate file
	 * @param file to validate
	 * @return validity of file
	 * Compare file to list of possible files
	 */
	private static boolean validateFile(String file) {
		
		//Create boolean to store validity
		boolean validFile = false;
		
		//Create list of possible piece names and files
		String[] files = {"a", "b", "c", "d", "e", "f", "g", "h"};
		
		//Validate file
		for (int i = 0; i < files.length; i++) {			//Loop through each possible piece
			
			if (file.equals(files[i])) {		//Return true if piece is in possible piece list
				
				validFile = true;
				break;
				
			}
			
		}
		
		return validFile;
		
	}
	
	/** Method to validate row
	 * @param row to validate
	 * @return validity of row
	 * Compare row to lowest and highest possible value
	 */
	private static boolean validateRow(int row) {
		
		return row >= 1 && row <= 8;
		
	}
	
	/** Method to get the row and file from a square
	 * @param square
	 * @return array with row and file
	 * Split square and return result
	 */
	private static String[] getRowAndFile(String square) {
		
		//Split square into file and row
		String[] rowAndFile = square.split("-");
		
		return rowAndFile;
		
	}
	
	/** Method to return piece name
	 * @param move
	 * @return piece name
	 * split move and return piece name
	 */
	private static String getPieceNameFromMove(String move) {
		
		//Get and return piece name from move
		String pieceName = move.substring(0, 1).toUpperCase();
		return pieceName;
		
	}
	
	/** Method to get start square
	 * @param move
	 * @return start square
	 * split move and return start square
	 */
	private static String getStartSquare(String move) {
		
		//Get and return start square from move
		String startSquare = move.split(" ")[0].substring(1, 4);
		return startSquare;
		
	}
	
	/** Method to get end square
	 * @param move
	 * @return end square
	 * split move and return end square
	 */
	private static String getEndSquare(String move) {
		
		//Get and return end square from move
		String endSquare = move.split(" ")[1];
		return endSquare;
		
	}
	
	/** Method to determine if piece can move to square
	 * @param startSquare
	 * @param endSquare
	 * @return possible to move to square
	 */
	private static boolean possibleMove(String startSquare, String endSquare) {
		
		//Create validity boolean
		boolean possibleMove = false;
		
		//Get row and file
		String[] rowAndFile = getRowAndFile(startSquare);
		String file = rowAndFile[0];
		int row = Integer.parseInt(rowAndFile[1]);
		
		//Create reference to piece
		Piece piece = ChessBoard.getPiece(file, row - 1);			//-1 to avoid ArrayIndexOutOfBoundsException
		
		//Get possible piece moves
		List<String> possibleMoves = piece.getPossibleMoves();
		
		//Check if piece can move to square
		if (possibleMoves.contains(endSquare)) {
			
			possibleMove = true;
			
		}
		
		//Return validity
		return possibleMove;
		
	}
	
	/** Method that moves piece
	 * @param pieceToMove
	 * @param endSquare
	 * Removes piece from board
	 * Sets new position of piece
	 * Re-adds piece
	 */
	public static void movePiece(Piece pieceToMove, String endSquare) {
		
		//Move piece
		ChessBoard.removePiece(pieceToMove);		//Remove piece from board
		pieceToMove.setCurrentPos(endSquare);		//Set new piece position
		ChessBoard.addPiece(pieceToMove);			//Re-add piece to board
		
		//Call method to find possible moves for all pieces
		ChessBoard.initializePossibleMoves();
		
	}
	
}
