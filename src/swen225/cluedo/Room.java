package swen225.cluedo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room implements Card {
	private List<RoomTile> tiles;
	
	private Set<Tile> exitTiles;
	
	private String name;
	
	public Room(String name) {
		this.name = name;
		tiles = new ArrayList<RoomTile>();
		exitTiles = new HashSet<Tile>();
	}
	
	public void addExitTile(Tile tile) {
		exitTiles.add(tile);
	}
	
	public void addTile(RoomTile tile) {
		tiles.add(tile);
	}
	
	public String getName() {
		return name;
	}
}
