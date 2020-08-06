package swen225.cluedo;

public class Weapon implements CluedoObject {
	private String name;
	private String tileValue;
	//should always be a RoomTile
	private Tile tile;
	
	/**
	 * 
	 * @param name
	 * @param tile
	 */
	public Weapon(String name, String tileValue) {
		this.name = name;
		this.tileValue = tileValue;
		this.tile = null;
	}
	
	/**
	 * 
	 */
	public Tile getTile() {
		return tile;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param room - the room to move to
	 */
	public void moveToRoom(Room room) {
		Tile newTile = room.getFreeTile();
		
		if (tile != null) {
			tile.setObject(null);
		}
		
		newTile.setObject(this);
		tile = newTile;
	}
	
	public String toString() {
		return tileValue;
	}
}
