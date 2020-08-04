package swen225.cluedo;

/**
 * 
 * 
 *
 */
public class InaccessibleTile extends Tile {
	
	/**
	 * Inaccessible tiles always have all 4 walls since you can't get to them
	 * @param x
	 * @param y
	 */
	public InaccessibleTile(int x, int y) {
		super(true, true, true, true, x, y);
		
		
	}
	
	public boolean isAccessible() {
		return false;
	}
	
	public String toString() {
		return " ";
	}
}
