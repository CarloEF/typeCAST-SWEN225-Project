public class RoomTile extends Tile {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private RoomCard room;

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

	public RoomCard getRoom() {
		return room;
	}

	public String toString() {
		return this.room.toString();
	}
}