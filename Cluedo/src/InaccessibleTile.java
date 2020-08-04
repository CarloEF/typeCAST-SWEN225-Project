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

	public String toString() {
		return "0";
	}
}
