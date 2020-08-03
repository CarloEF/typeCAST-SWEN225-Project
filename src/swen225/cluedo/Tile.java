package swen225.cluedo;

public abstract class Tile {
	boolean hasUpWall;
	boolean hasDownWall;
	boolean hasLeftWall;
	boolean hasRightWall;
	
	int x;
	int y;
	
	CluedoObject co;
	
	public Tile(boolean up, boolean down, boolean left, boolean right, int x, int y) {
		hasUpWall = up;
		hasDownWall = down;
		hasLeftWall = left;
		hasRightWall = right;
		
		this.x = x;
		this.y = y;
		
		co = null;
	}
	
	public void setObject(CluedoObject newCO) {
		co = newCO;
	}
	
	public abstract String toString();
}
