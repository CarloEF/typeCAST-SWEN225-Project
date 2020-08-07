import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Player
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Player Attributes
	private ArrayList<Card> startingCards;
	private CharacterCard currentChar;
	private int playerNumber;		// For easier access; Can be removed.
	private Tile location;
	private int x;					// For easier access; Can be removed.
	private int y;					// For easier access; Can be removed.
	private boolean inRoom;			//
	private RoomCard currentRoom;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Player(CharacterCard currentChar) {
		this.currentChar = currentChar;
		this.playerNumber = Integer.parseInt(currentChar.toString());
		this.x = currentChar.getInitialRow();
		this.y = currentChar.getInitialCol();
		this.startingCards = new ArrayList<Card>();
	}

	//------------------------
	// INTERFACE
	//------------------------

	public void addCards(Card card) { // Should be called by GAME class when establishing players.
		this.startingCards.add(card);
	}
	public void setNumber(int number) {		// Shouldn't really be used: Player number is set up in constructor.
		this.playerNumber = number;
	}
	public void setRoom(RoomCard room) {
		this.currentRoom = room;
		this.inRoom = true;
	}
	public void leftRoom() {
		this.currentRoom = null;
		this.inRoom = false;
	}
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean move(int x, int y)
	{
		/*
		 * LOGIC FOR MOVEMENT WILL GO HERE. GAME class will call this class.
		 */

		// If (able to move to these coordinates) {
		// 		return true; move to coordinates.
		// }
		// else { // Cannot move here
		// 		return false; 
		// }
		return false; // Temp.
	}

	public void suggest() {
		/*
		 * MIGHT IMPLEMENT THIS INTO GAME CLASS
		 */
	}

	public boolean refute(Suggestion suggestion, Scanner s) {		// While loop within Game until TRUE is reached, or all other players have been asked 
		if (this.startingCards.contains(suggestion.getWeapon()) ||			// If current refuter has the suggestion's weapon.
				this.startingCards.contains(suggestion.getCharacter()) ||	// If current refuter has the suggestion's character.
				this.startingCards.contains(suggestion.getRoom())) {		// If current refuter has the suggestion's room.

			/*
			 * ASK CURRENT REFUTER WHICH CARD THEY WOULD LIKE TO SHOW
			 */
			// Prints out the choices available
			System.out.println(suggestion.getCardList());
			System.out.println(this.currentChar.getCardName()+": player "+this.getNumber()+" is able to refute.");
			System.out.println("Which card would you like to refute?");

			String refute = s.nextLine();
			if (this.startingCards.contains(null)) {			// TO CHANGE: Needs a comparator.

			}

			// Ask player which Card they would like to show:
			// If they don't own a card that has the same cardName as the specified card, Output error.
			// 
			return true;		
		}
		else {
			System.out.println(this.currentChar.getCardName()+": player "+this.getNumber()+" cannot refute the suggestion.");
			return false;		// Current refuter doesn't have any cards in suggestion.
		}
	}
	public int getNumber() {
		return this.playerNumber;
	}

	public Tile getLocation()
	{
		return location;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public boolean isInRoom() {
		if (this.inRoom) {
			return true;
		}
		return false;
	}
	public RoomCard currentRoom() {
		if (this.inRoom) {
			return this.currentRoom;
		}
		return null;
	}
}