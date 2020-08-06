package swen225.cluedo;

public class RoomTile extends Tile {
	
	private Room room;
	
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
	
	public boolean isAccessible() {
		return false;
	}
	
	public String toString() {
		String tileValue = super.toString();
		//if tile doesn't have an object on it return room's tile value
		if (tileValue.equals(" ")) {
			return room.toString();
		}
		return tileValue;
	}

}
