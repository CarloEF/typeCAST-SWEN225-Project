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
	
	public static final String[] PLAYER_ORDER = {"Miss Scarlett", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
	
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
	public static final String ROOM_BOARD = "iiiiiiiiihiiiihiiiiiiiii" + 
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
											"ooooooihiaaaaaaihissssss";
	
	//bitwise flag for walls with / as delimiter
	//1 for left, 2 for up, 4 for right, 8 for down
	//convention is walls are on both tiles they are connected to
	public static final String WALL_BOARD = "0/0/0/0/0/0/0/0/0/7/0/0/0/0/7/0/0/0/0/0/0/0/0/0/" + 
											"3/2/2/2/2/6/0/3/10/12/3/2/2/6/9/10/6/0/3/2/2/2/2/6/" +
											"1/0/0/0/0/4/3/4/3/2/0/0/0/0/2/6/1/6/1/0/0/0/0/4/" +
											"1/0/0/0/0/4/1/4/1/0/0/0/0/0/0/4/1/4/1/0/0/0/0/4/" +
											"1/0/0/0/0/4/1/4/1/0/0/0/0/0/0/4/1/4/1/0/0/0/0/12/" +
											"9/0/0/0/0/4/1/0/0/0/0/0/0/0/0/0/0/0/4/9/8/8/12/0/" +
											"0/9/8/8/0/12/1/4/1/0/0/0/0/0/0/4/1/0/0/2/2/2/2/14/" +
											"11/2/2/2/0/2/0/4/9/0/8/8/8/8/0/12/1/0/8/8/8/8/12/0/" +
											"0/9/8/8/8/0/0/0/2/0/2/2/2/2/0/2/0/4/3/2/2/2/2/6/" +
											"3/2/2/2/6/9/8/8/0/0/8/8/8/8/8/0/0/0/0/0/0/0/0/4/" +
											"1/0/0/0/0/2/2/6/1/4/3/2/2/2/6/1/0/4/1/0/0/0/0/4/" +
											"1/0/0/0/0/0/0/4/1/4/1/0/0/0/4/1/0/4/1/0/0/0/0/4/" +
											"1/0/0/0/0/0/0/0/0/4/1/0/0/0/4/1/0/4/9/8/8/8/0/12/" +
											"1/0/0/0/0/0/0/4/1/4/1/0/0/0/4/1/0/0/10/10/2/10/12/0/" +
											"1/0/0/0/0/0/0/4/1/4/1/0/0/0/4/1/0/12/3/2/0/2/6/0/" +
											"9/8/8/8/8/8/0/12/1/4/1/0/0/0/4/1/4/3/0/0/0/0/0/6/" +
											"0/3/2/2/2/2/0/2/0/4/9/8/8/8/12/1/0/0/0/0/0/0/0/4/" +
											"11/0/0/0/0/0/0/0/0/8/10/2/2/10/10/0/4/9/0/0/0/0/0/12/" +
											"0/9/8/8/8/8/0/0/4/3/2/0/0/2/6/1/0/6/9/8/8/8/12/0/" +
											"3/2/2/2/2/2/4/1/4/1/0/0/0/0/4/1/0/0/2/2/2/2/2/14/" +
											"1/0/0/0/0/0/4/1/4/1/0/0/0/0/0/0/0/0/8/8/8/8/12/0/" +
											"1/0/0/0/0/0/4/1/4/1/0/0/0/0/4/1/4/1/2/2/2/2/2/6/" +
											"1/0/0/0/0/0/4/1/4/1/0/0/0/0/4/1/4/1/0/0/0/0/0/4/" + 
											"1/0/0/0/0/0/12/1/12/1/0/0/0/0/4/9/4/9/0/0/0/0/0/4/" +
											"9/8/8/8/8/12/0/13/0/9/8/8/8/8/12/0/13/0/9/8/8/8/8/9/";
	
	//stores the number of players
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
	
	/**
	 * Initiates the board
	 */
	private void initBoard() {
		board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		
		board.setBoard(ROOM_BOARD, WALL_BOARD, rooms);
	}
	
	/**
	 * Initiates the cards
	 */
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
	
	/**
	 * Adds a player to collections
	 * @param player
	 */
	private void addPlayer(Player player) {
		cards.add(player);
		players.put(player.getName(), player);
	}
	
	/**
	 * Adds a weapon to collections
	 * @param weapon
	 */
	private void addWeapon(Weapon weapon) {
		cards.add(weapon);
		weapons.put(weapon.getName(), weapon);
	}
	
	/**
	 * Adds a room to collections
	 * @param room
	 */
	private void addRoom(Room room) {
		cards.add(room);
		rooms.put(room.getName(), room);
	}
	
	/**
	 * Adds the room exits
	 */
	private void addRoomExits() {
		rooms.get("Kitchen").addExitTile(board.getTile(4, 7));
		
		rooms.get("Ball Room").addExitTile(board.getTile(7, 5));
		rooms.get("Ball Room").addExitTile(board.getTile(9, 8));
		rooms.get("Ball Room").addExitTile(board.getTile(14, 8));
		rooms.get("Ball Room").addExitTile(board.getTile(16, 5));
		
		rooms.get("Conservatory").addExitTile(board.getTile(18, 5));
		
		rooms.get("Billiard Room").addExitTile(board.getTile(17, 9));
		rooms.get("Billiard Room").addExitTile(board.getTile(22, 13));
		
		rooms.get("Library").addExitTile(board.getTile(20, 13));
		rooms.get("Library").addExitTile(board.getTile(16, 16));
		
		rooms.get("Study").addExitTile(board.getTile(17, 20));

		rooms.get("Hall").addExitTile(board.getTile(15, 20));
		rooms.get("Hall").addExitTile(board.getTile(12, 17));
		rooms.get("Hall").addExitTile(board.getTile(11, 17));
		
		rooms.get("Lounge").addExitTile(board.getTile(6, 18));

		rooms.get("Dining Room").addExitTile(board.getTile(6, 16));
		rooms.get("Dining Room").addExitTile(board.getTile(8, 12));
	}
	
	/**
	 * Sets the players at their starting positions
	 */
	private void setPlayerTiles() {
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
		addRoomExits();
		setPlayerTiles();
		
		playerNum = getPlayerNum();
		
		orderPlayers();
		dealCards();
		
		//could be random
		int currentPlayerIndex = 0;
		
		while(isRunning) {
			Player currentPlayer = playersOrdered.get(currentPlayerIndex);
			startPlayerTurn(currentPlayer);
			
			currentPlayerIndex = (currentPlayerIndex + 1) % playerNum;
		}
	}
	
	/**
	 * Orders the players depending on how many players there are
	 */
	private void orderPlayers() {
		for (int i=0;i<playerNum;i++) {
			playersOrdered.add(players.get(PLAYER_ORDER[i]));
		}
	}
	
	/**
	 * Deals cards out, including picking the murder cards
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
		
		//TODO catch potential errors on input
		int num = input.nextInt();
		
		while (num < 3 || num > 6) {
			System.out.println("Players(must be between 3 and 6):");
			
			num = input.nextInt();
		}
		
		return num;
	}
	
	/**
	 * Starts a player's turn, taking input
	 * @param player
	 */
	public void startPlayerTurn(Player player) {
		
		System.out.println(player.getName() + "'s turn:");
		
		System.out.println(board);
		System.out.println("");
		
		int step1 = diceRoll();
		int step2 = diceRoll();
		
		int stepNum = step1 + step2;
		
		int playerX = player.getTile().getX()+1;
		int playerY = BOARD_HEIGHT - player.getTile().getY();
		
		System.out.printf("You rolled a %d and a %d, giving a total of %d\n", step1, step2, stepNum);
		System.out.printf("You are at (%d, %d)\n", playerX, playerY);
		System.out.println("Where do you want to move(give in pairs of coords or room name):");
		
		int newX = input.nextInt();
		int newY = input.nextInt();
		
		newX--;
		newY = BOARD_HEIGHT - newY;
		
		System.out.println(board.isValidMove(stepNum, player, newX, newY));
		
		
		
	}
	
	/**
	 * Simulates a dice roll
	 * @return a random number between 1 and 6
	 */
	private int diceRoll() {
		return (int)Math.floor(Math.random()*6+1);
	}
	

}
