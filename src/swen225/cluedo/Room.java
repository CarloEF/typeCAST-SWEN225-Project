package swen225.cluedo;

import java.util.ArrayList;
import java.util.List;

public class Room implements Card {
	private List<Weapon> weapons;
	private List<Character> players;
	
	private List<RoomTile> tiles;
	
	private String name;
	
	public Room(String name) {
		this.name = name;
		tiles = new ArrayList<RoomTile>();
	}
	
	public void addTile(RoomTile tile) {
		tiles.add(tile);
	}
	
	public String getName() {
		return name;
	}
}
