package swen225.cluedo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room implements Card {
	private List<RoomTile> tiles;
	
	private Set<Tile> exitTiles;
	
	private String name;
	
	private String tileValue;
	
	public Room(String name, String tileValue) {
		this.name = name;
		this.tileValue = tileValue;
		tiles = new ArrayList<RoomTile>();
		exitTiles = new HashSet<Tile>();
	}
	
	/**
	 * Gets a tile from this room that is free
	 * @return
	 */
	public Tile getFreeTile() {
		int index = 0;
		do {
			index = (int)Math.floor(Math.random()*tiles.size());
		} while (tiles.get(index).hasObject());
		return tiles.get(index);
	}
	
	public void addExitTile(Tile tile) {
		exitTiles.add(tile);
	}
	
	public Set<Tile> getExitTiles() {
		return exitTiles;
	}
	
	public void addTile(RoomTile tile) {
		tiles.add(tile);
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return tileValue;
	}
}
