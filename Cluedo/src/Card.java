public class Card {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private String cardName;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Card(String name) {
		this.cardName = name;
	}

	//------------------------
	// INTERFACE
	//------------------------
	
	public String getCardName() {
		return this.cardName;
	}
	
	public String toString() {
		return null; // Should be unreachable: Simply an interface.
	}

}