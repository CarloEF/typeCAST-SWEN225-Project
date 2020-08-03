/**
 * 
 */
package swen225.cluedo;

/**
 * 
 */
public interface CluedoObject extends Card {
	public Tile getTile();
	public void moveToRoom(Room room);
}
