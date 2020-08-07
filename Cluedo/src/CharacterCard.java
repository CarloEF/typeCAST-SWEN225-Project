public class CharacterCard extends Card {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private int initialCol;		// This specific character's starting Y coordinate.
	private int initialRow;		// This specific character's starting X coordinate.

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public CharacterCard(int initialRow, int initialCol, String Name) {
		super(Name);
		this.initialRow = initialRow;
		this.initialCol = initialCol;
	}
	public int getInitialCol() {
		return this.initialCol;
	}
	public int getInitialRow() {
		return this.initialRow;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public String toString() {
		if (this.getCardName().equals("Miss Scarlett")) {
			return "1";
		}
		else if (this.getCardName().equals("Colonel Mustard")) {
			return "2";
		}
		else if (this.getCardName().equals("Mrs. White")) {
			return "3";
		}
		else if (this.getCardName().equals("Mr. Green")) {
			return "4";
		}
		else if (this.getCardName().equals("Mrs. Peacock")) {
			return "5";
		}
		else if (this.getCardName().equals("Professor Plum")) {
			return "6";
		}
		else return null; // Should be unreachable: Only 6 Characters.

	}
}