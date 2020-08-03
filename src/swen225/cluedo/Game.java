/**
 * 
 */
package swen225.cluedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 *
 */
public class Game {
	
	//constants
	public static final int BOARD_WIDTH = 24;
	public static final int BOARD_HEIGHT = 25;
	
	public static final String[] playerOrder = {"Miss Scarlett", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
	
	int playerNum;
	
	List<Card> cards;
	
	Board board;
	
	Map<String, Player> players;
	Map<String, Weapon> weapons;
	Map<String, Room> rooms;
	
	List<Player> playersOrdered;
	
	boolean isRunning = true;
	
	Scanner input;
	
	/**
	 * 
	 * @param playerNum
	 */
	public Game() {
		cards = new ArrayList<Card>();
		
		players = new HashMap<String, Player>();
		weapons = new HashMap<String, Weapon>();
		rooms = new HashMap<String, Room>();
		
		playersOrdered = new ArrayList<Player>();
		
		input = new Scanner(System.in);
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
		
	}
	
	private void initBoard() {
		board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		
		
		//i = inaccessible
		//h = hallway
		//k = kitchen
		//b = ball room
		//c = conservatory
		//d = dining room
		//l = library
		//m = billiard room(too many letter conflicts)
		//a = hall
		//s = study
		//o = lounge
		board.setBoard("iiiiiiiiihiiiihiiiiiiiii" + 
					   "kkkkkkihhhbbbbhhhicccccc" + 
					   "kkkkkkhhbbbbbbbbhhcccccc" + 
					   "kkkkkkhhbbbbbbbbhhcccccc" + 
					   "kkkkkkhhbbbbbbbbhhcccccc" + 
					   "kkkkkkhhbbbbbbbbhhhcccci" +
					   "ikkkkkhhbbbbbbbbhhhhhhhh" +
					   "hhhhhhhhbbbbbbbbhhhhhhhi" +
					   "ihhhhhhhhhhhhhhhhhmmmmmm" +
					   "dddddhhhhhhhhhhhhhmmmmmm" +
					   "ddddddddhhiiiiihhhmmmmmm" +
					   "ddddddddhhiiiiihhhmmmmmm" + 
					   "ddddddddhhiiiiihhhmmmmmm" + 
					   "ddddddddhhiiiiihhhhhhhhi" +
					   "ddddddddhhiiiiihhhllllli" +
					   "ddddddddhhiiiiihhlllllll" +
					   "ihhhhhhhhhiiiiihhlllllll" +
					   "hhhhhhhhhhhhhhhhhlllllll" +
					   "ihhhhhhhhaaaaaahhhllllli" +
					   "ooooooohhaaaaaahhhhhhhhh" +
					   "ooooooohhaaaaaahhhhhhhhi" +
					   "ooooooohhaaaaaahhsssssss" + 
					   "ooooooohhaaaaaahhsssssss" + 
					   "ooooooohhaaaaaahhsssssss" + 
					   "ooooooihiaaaaaaihissssss",
		//bitwise flag for walls with / as delimiter
		//1 for left, 2 for up, 4 for right, 8 for down
		//convention is walls are on the room side
					   "0/0/0/0/0/0/0/0/0/7/0/0/0/0/7/0/0/0/0/0/0/0/0/0/" + 
					   "3/2/2/2/2/6/0/3/2/0/3/2/2/6/0/2/6/0/3/2/2/2/2/6/" +
					   "1/0/0/0/0/4/2/0/3/2/0/0/0/0/2/6/0/2/1/0/0/0/0/4/" +
					   "1/0/0/0/0/4/0/0/1/0/0/0/0/0/0/4/0/0/1/0/0/0/0/4/" +
					   "1/0/0/0/0/4/0/0/1/0/0/0/0/0/0/4/0/0/1/0/0/0/0/12/" +
					   "9/0/0/0/0/4/0/0/0/0/0/0/0/0/0/0/0/0/0/9/8/8/12/0/" +
					   "0/9/8/8/0/12/0/0/1/0/0/0/0/0/0/4/0/0/0/0/0/0/0/14/" +
					   "11/0/0/0/0/0/0/0/9/0/8/8/8/8/0/12/0/0/0/0/0/0/4/0/" +
					   "0/1/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/3/2/2/2/2/6/" +
					   "3/2/2/2/6/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/4/" +
					   "1/0/0/0/0/2/2/6/0/0/3/2/2/2/6/0/0/0/1/0/0/0/0/4/" +
					   "1/0/0/0/0/0/0/4/0/0/1/0/0/0/4/0/0/0/1/0/0/0/0/4/" +
					   "1/0/0/0/0/0/0/0/0/0/1/0/0/0/4/0/0/0/9/8/8/8/0/12/" +
					   "1/0/0/0/0/0/0/4/0/0/1/0/0/0/4/0/0/0/0/0/0/0/4/0/" +
					   "1/0/0/0/0/0/0/4/0/0/1/0/0/0/4/0/0/0/3/2/0/2/6/0/" +
					   "9/8/8/8/8/8/0/12/0/0/1/0/0/0/4/0/0/3/0/0/0/0/0/6/" +
					   "0/1/0/0/0/0/0/0/0/0/9/8/8/8/12/0/0/0/0/0/0/0/0/4/" +
					   "11/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/9/0/0/0/0/0/12/" +
					   "0/1/0/0/0/0/0/0/0/3/2/0/0/2/6/0/0/0/9/8/8/8/12/0/" +
					   "3/2/2/2/2/2/4/0/0/1/0/0/0/0/4/0/0/0/0/0/0/0/0/14/" +
					   "1/0/0/0/0/0/4/0/0/1/0/0/0/0/0/0/0/0/0/0/0/0/4/0/" +
					   "1/0/0/0/0/0/4/0/0/1/0/0/0/0/4/0/0/1/2/2/2/2/2/6/" +
					   "1/0/0/0/0/0/4/0/0/1/0/0/0/0/4/0/0/1/0/0/0/0/0/4/" + 
					   "1/0/0/0/0/0/12/0/8/1/0/0/0/0/4/8/0/9/0/0/0/0/0/4/" +
					   "9/8/8/8/8/8/12/0/13/0/9/8/8/8/8/12/0/13/0/9/8/8/8/8/9/",
					   rooms);
	}
	
	private void initCards() {
		/*Player missScarlett = new Player("Miss Scarlett", board.getTile(7, 24));
		Player colMustard = new Player("Col. Mustard", board.getTile(0, 17));
		Player mrsWhite = new Player("Mrs. White", board.getTile(9, 0));
		Player mrGreen = new Player("Mr. Green", board.getTile(14, 0));
		Player mrsPeacock = new Player("Mrs. Peacock", board.getTile(23, 6));
		Player profPlum = new Player("Prof. Plum", board.getTile(23, 19));
		
		Weapon candlestick = new Weapon("Candlestick");
		Weapon dagger = new Weapon("Dagger");
		Weapon leadPipe = new Weapon("Lead Pipe");
		Weapon revolver = new Weapon("Revolver");
		Weapon rope = new Weapon("Rope");
		Weapon spanner = new Weapon("Spanner");
		
		Room kitchen = new Room("Kitchen");
		Room ballRoom = new Room("Ball Room");
		Room conservatory = new Room("Conservatory");
		Room billiardRoom = new Room("Billiard Room");
		Room library = new Room("Library");
		Room study = new Room("Study");
		Room hall = new Room("Hall");
		Room lounge = new Room("Lounge");
		Room diningRoom = new Room("Dining Room");*/
		
		addPlayer(new Player("Miss Scarlett"));
		addPlayer(new Player("Col. Mustard"));
		addPlayer(new Player("Mrs. White"));
		addPlayer(new Player("Mr. Green"));
		addPlayer(new Player("Mrs. Peacock"));
		addPlayer(new Player("Prof. Plum"));
		
		addWeapon(new Weapon("Candlestick"));
		addWeapon(new Weapon("Dagger"));
		addWeapon(new Weapon("Lead Pipe"));
		addWeapon(new Weapon("Revolver"));
		addWeapon(new Weapon("Rope"));
		addWeapon(new Weapon("Spanner"));
		
		addRoom(new Room("Kitchen"));
		addRoom(new Room("Ball Room"));
		addRoom(new Room("Conservatory"));
		addRoom(new Room("Billiard Room"));
		addRoom(new Room("Library"));
		addRoom(new Room("Study"));
		addRoom(new Room("Hall"));
		addRoom(new Room("Lounge"));
		addRoom(new Room("Dining Room"));
		
	}
	
	private void addPlayer(Player player) {
		cards.add(player);
		players.put(player.getName(), player);
	}
	
	private void addWeapon(Weapon weapon) {
		cards.add(weapon);
		weapons.put(weapon.getName(), weapon);
	}
	
	private void addRoom(Room room) {
		cards.add(room);
		rooms.put(room.getName(), room);
	}
	
	/**
	 * Sets the players at their starting positions
	 */
	public void setPlayerTiles() {
		players.get("Miss Scarlett").setTile(board.getTile(7, 24));
		players.get("Col. Mustard").setTile(board.getTile(0, 17));
		players.get("Mrs. White").setTile(board.getTile(9, 0));
		players.get("Mr. Green").setTile(board.getTile(14, 0));
		players.get("Mrs. Peacock").setTile(board.getTile(23, 6));
		players.get("Prof. Plum").setTile(board.getTile(23, 19));
	}
	
	/**
	 * Handles all the logic at the start of the game
	 */
	public void startGame() {
		initCards();
		initBoard();
		setPlayerTiles();
		
		playerNum = getPlayerNum();
		
		orderPlayers();
		dealCards();
		
		while(isRunning) {
			startPlayerTurn(players.get("Miss Scarlett"));
		}
	}
	
	/**
	 * 
	 */
	private void orderPlayers() {
		for (int i=0;i<playerNum;i++) {
			playersOrdered.add(players.get(playerOrder[i]));
		}
	}
	
	/**
	 * Deals cards out
	 */
	private void dealCards() {
		Collections.shuffle(cards);
		
		//take out 1 player, 1 weapon, 1 room cards for the murder circumstance
		Player murderer = null;
		Weapon murderWeapon = null;
		Room murderRoom = null;
		int index = 0;
		while(murderer == null || murderWeapon == null || murderRoom == null) {
			Card card = cards.get(index);
			if (murderer == null && card instanceof Player) {
				murderer = (Player)card;
				cards.remove(index);
				continue;
			}
			if (murderWeapon == null && card instanceof Weapon) {
				murderWeapon = (Weapon)card;
				cards.remove(index);
				continue;
			}
			if (murderRoom == null && card instanceof Room) {
				murderRoom = (Room)card;
				cards.remove(index);
				continue;
			}
			index++;
		}
		
		//deal the other cards out
		for (int i=0, len=cards.size();i<len;i++) {
			Player player = playersOrdered.get(i % playerNum);
			
			player.addCard(cards.get(i));
		}
	}
	
	/**
	 * Gets input about the number of players.
	 * @return the number of players
	 */
	private int getPlayerNum() {
		
		System.out.println("How many players are playing?");
		
		int num = input.nextInt();
		
		while (num < 3 || num > 6) {
			System.out.println("Players(must be between 3 and 6):");
			
			num = input.nextInt();
		}
		
		return num;
	}
	
	/**
	 * 
	 * @param player
	 */
	public void startPlayerTurn(Player player) {
		System.out.println(board);
		System.out.println("");
		
		int step1 = diceRoll();
		int step2 = diceRoll();
		
		int stepNum = step1 + step2;
		
		System.out.printf("You rolled a %d and a %d, giving a total of %d\n", step1, step2, stepNum);
		
		System.out.println("Where do you want to move(give in pairs of coords or room name):");
		
		String move = input.next();
		//do checks on this
		
		
		
		
	}
	
	private int diceRoll() {
		return (int)Math.floor(Math.random()*6+1);
	}
	

}
