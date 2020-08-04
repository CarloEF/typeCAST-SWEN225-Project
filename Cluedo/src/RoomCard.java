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

	public String toString() {
		if (this.getCardName() == "Kitchen") {
			return "K";
		}
		else if (this.getCardName() == "Ballroom") {
			return "B";
		}
		else if (this.getCardName() == "Conservatory") {
			  return "C";
		  }
		else if (this.getCardName() == "Dining Room") {
			  return "D";
		  }
		else if (this.getCardName() == "Billiard Room") {
			  return "b";
		  }
		else if (this.getCardName() == "Library") {
			  return "L";
		  }
		else if (this.getCardName() == "Lounge") {
			  return "l";
		  }
		else if (this.getCardName() == "Hall") {
			  return "H";
		  }
		else if (this.getCardName() == "Study") {
			  return "S";
		  }
		else return null; // Should be unreachable: Only 9 Rooms.
	}
}