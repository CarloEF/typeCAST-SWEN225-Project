public class HallwayTile extends Tile {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public HallwayTile(int x, int y) {
		super(x, y);
	}

	//------------------------
	// INTERFACE
	//------------------------

	public String toString() {
		if (this.hasPlayer()) {		// If this tile has a player on it, return the player's number.
			return Integer.toString(this.getCurrentPlayer().getNumber());		// FIXXXXXX
		}
		return " ";					// If not, return a space; The hallway's notation.
	}
}