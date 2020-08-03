package swen225.cluedo;

import java.util.List;
import java.util.ArrayList;

public class Player implements CluedoObject {
	private List<Card> hand;
	private String name;
	private Tile tile;
	
	/**
	 * 
	 * @param name
	 * @param tile
	 */
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * 
	 * @param card
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/**
	 * 
	 * @param tile
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
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
		
	}
}
