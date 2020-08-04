import java.util.HashSet;

public class Player
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Player Attributes
	private HashSet<Card> startingCards;
	private CharacterCard currentChar;
	private Tile location;
	private int x;
	private int y;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Player(CharacterCard currentChar, Tile location) {
		this.currentChar = currentChar;
		this.location = location;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public void setStarterCards(HashSet<Card> starters) { // Should be called by GAME class when establishing players.
		this.startingCards = starters;
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

	public boolean refute(Suggestion suggestion) {
		if (this.startingCards.contains(suggestion.getWeapon()) ||		// If current refuter has the suggestion's weapon.
				this.startingCards.contains(suggestion.getCharacter()) ||	// If current refuter has the suggestion's character.
				this.startingCards.contains(suggestion.getRoom())) {		// If current refuter has the suggestion's room.

			/*
			 * ASK CURRENT REFUTER WHICH CARD THEY WOULD LIKE TO SHOW
			 */
			return true;		
		}
		else {
			System.out.println(this.currentChar.getCardName()+" cannot refute the suggestion.");
			return false;		// Current refuter doesn't have any cards in suggestion.
		}
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
}