public class Tile {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private int x;		// X coordinate of this tile within the board.
	private int y;		// Y coordinate of this tile within the board.
	private Player currentPlayer;		// Current player situated on this tile. Null otherwise.
	private WeaponCard currentWeapon;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.currentPlayer = null;
		this.currentWeapon = null;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public void setPlayerOnTile(Player player) {
		this.currentPlayer = player;
	}
	public void setWeaponOnTile(WeaponCard weapon) {
		this.currentWeapon = weapon;
	}
	public boolean hasPlayer() {			// Whether or not this tile has a Player on it.
		if (this.currentPlayer != null) {
			return true;
		}
		return false;
	}
	public boolean hasWeapon() {
		if (this.currentWeapon != null) {
			return true;
		}
		return false;
	}
	public Player getCurrentPlayer() {		// Getter method for acquiring the Player on this tile.
		if (this.currentPlayer != null) {
			return this.currentPlayer;
		}
		return null;		// Shouldn't be reached, as hasPlayer() should be called before calling this.
	}
	public WeaponCard getCurrentWeapon() {
		if (this.currentWeapon != null) {
			return this.currentWeapon;
		}
		return null;		// Shouldn't be reached, as hasWeapon() should be called before calling this.
	}
	public int getPlayerNumber() {			// Getter method for acquiring the Player's corresponding number (To display on the board).
		if (this.currentPlayer != null) {
			return this.currentPlayer.getNumber();
		}
		return -999999999;		// Shouldn't be reached, as hasPlayer() should be called before calling this.
	}

	public int getX() {			// Getter method for acquring this tile's X coordinate.
		return this.x;
	}
	public int getY() {			// Getter method for acquring this tile's Y coordinate.
		return this.y;
	}
	
	public boolean passable() {		// Should be unreachable: Simply an interface.
		return true;
	}
	public String toString() {
		return null; // Should be unreachable: Simply an interface.
	}
}