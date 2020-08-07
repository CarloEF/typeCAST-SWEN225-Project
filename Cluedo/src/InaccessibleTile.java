public class InaccessibleTile extends Tile {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public InaccessibleTile(int x, int y) {
		super(x, y);
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean passable() {		// The player cannot walk into this tile.
		return false;
	}
	
	public String toString() {		// Return a *. The notation for an inaccessible tile.
		return "*";
	}
}