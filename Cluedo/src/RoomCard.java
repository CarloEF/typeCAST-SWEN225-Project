public class RoomCard extends Card {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public RoomCard(String name) {
		super(name);
	}

	//------------------------
	// INTERFACE
	//------------------------

	public String toString() {			// use toString() for displaying on the board.
		if (this.getCardName().equals("Kitchen")) {
			return "K";
		}
		else if (this.getCardName().equals("Ballroom")) {
			return "B";
		}
		else if (this.getCardName().equals("Conservatory")) {
			  return "C";
		  }
		else if (this.getCardName().equals("Dining Room")) {
			  return "D";
		  }
		else if (this.getCardName().equals("Billiard Room")) {
			  return "b";
		  }
		else if (this.getCardName().equals("Library")) {
			  return "L";
		  }
		else if (this.getCardName().equals("Lounge")) {
			  return "l";
		  }
		else if (this.getCardName().equals("Hall")) {
			  return "H";
		  }
		else if (this.getCardName().equals("Study")) {
			  return "S";
		  }
		else return null; // Should be unreachable: Only 9 Rooms.
	}
}