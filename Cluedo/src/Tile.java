public class Tile {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private int x;
	private int y;
	private Player currentPlayer;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.currentPlayer = null;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public void setPlayerOnTile(Player current) {
		this.currentPlayer = current;
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

	public String toString() {
		return null; // Should be unreachable: Simply an interface.
	}
}