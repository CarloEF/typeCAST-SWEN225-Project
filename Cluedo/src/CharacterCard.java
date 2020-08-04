public class CharacterCard extends Card {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private int initialCol;
	private int initialRow;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public CharacterCard(int initialCol, int initialRow, String Name) {
		super(Name);
		this.initialCol = initialCol;
		this.initialRow = initialRow;
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
		if (this.getCardName() == "Miss Scarlett") {
			return "1";
		}
		else if (this.getCardName() == "Colonel Mustard") {
			return "2";
		}
		else if (this.getCardName() == "Mrs. White") {
			return "3";
		}
		else if (this.getCardName() == "Mr. Green") {
			return "4";
		}
		else if (this.getCardName() == "Mrs. Peacock") {
			return "5";
		}
		else if (this.getCardName() == "Professor Plum") {
			return "6";
		}
		else return null; // Should be unreachable: Only 6 Characters.

	}
}