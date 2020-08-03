package swen225.cluedo;

public class Weapon implements CluedoObject {
	private String name;
	//should always be a RoomTile
	private Tile tile;
	
	/**
	 * 
	 * @param name
	 * @param tile
	 */
	public Weapon(String name) {
		this.name = name;
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
	 */
	public void moveToRoom(Room room) {
		//TODO
	}
}
