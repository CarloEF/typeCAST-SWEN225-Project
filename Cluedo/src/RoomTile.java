public class RoomTile extends Tile {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private RoomCard room;		// This RoomTile's corresponding RoomCard.

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public RoomTile(int x, int y, RoomCard room) {
		super(x,y);
		this.room = room;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean passable() {		// The player can walk into this Tile.
		if (this.hasPlayer()) {
			return false;
		}
		return true;
	}
	
	public RoomCard getRoom() {		// Getter method for acquiring this RoomTile's RoomCard.
		return room;
	}

	public String toString() {
		if (this.hasPlayer()) {		// If this tile has a player on it, return the player's number.
			return Integer.toString(this.getCurrentPlayer().getNumber());		///  FIXXXXX
		}
		return this.room.toString();	// If not, return the room's letter notation.
	}
}