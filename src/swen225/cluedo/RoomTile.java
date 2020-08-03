package swen225.cluedo;

public class RoomTile extends Tile {
	
	Room room;
	
	/**
	 * 
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 * @param room
	 */
	public RoomTile(boolean up, boolean down, boolean left, boolean right, int x, int y, Room room) {
		super(up, down, left, right, x, y);
		
		this.room = room;
		room.addTile(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public Room getRoom() {
		return room;
	}
	
	public String toString() {
		return String.valueOf(room.getName().charAt(0));
	}

}
