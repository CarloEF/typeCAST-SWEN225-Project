import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	public static final int BOARD_HEIGHT = 25;
	public static final int BOARD_WIDTH = 24;
	private Board board;
	private ArrayList<Player> players;
	private int numPlayers;
	private ArrayList<Card> cardsWithMurder;
	private Suggestion murder;
	private ArrayList<CharacterCard> characterList;
	private ArrayList<RoomCard> roomList;
	private ArrayList<WeaponCard> weaponList;
	private ArrayList<Card> cards;

	//------------------------
	// CONSTRUCTOR AND SETUP
	//------------------------

	public Game() {
		this.board = new Board(BOARD_HEIGHT, BOARD_WIDTH);
		this.setCardSet();
	}

	public void setup() {
		Scanner s = new Scanner(System.in);
		int num = 0;
		do {
			System.out.print("How many players? 3-6.");
			try {
				num = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException error) {
				System.err.println("Please enter a valid number.");
			}
			System.out.println("You entered : " + this.numPlayers);
		} while (num > 6 || num < 3);

		this.numPlayers = num;
		this.setPlayers(numPlayers);
		this.setCardSet();
		this.setMurder();

	}

	public void setPlayers(int n) {

		ArrayList<Player> temp = new ArrayList<Player>();
		//ALWAYS add first 3 characters.
		temp.add(new Player(new CharacterCard(7, 24, "Miss Scarlett")));
		temp.add(new Player(new CharacterCard(0, 17, "Colonel Mustard")));
		temp.add(new Player(new CharacterCard(9, 0, "Mrs. White")));
		// IF statements for final 3.
		if (n > 3) {
			temp.add(new Player(new CharacterCard(14, 0, "Mr. Green")));
		}
		if (n > 4) {
			temp.add(new Player(new CharacterCard(23, 6, "Mrs. Peacock")));
		}
		if (n > 5) {
			temp.add(new Player(new CharacterCard(23, 19, "Professor Plum")));
		}
		this.players = temp;
	}

	public void setCardSet() {
		ArrayList<Card> temp = new ArrayList<Card>();
		// Adding Character Cards.
		ArrayList<CharacterCard> charTemp = new ArrayList<CharacterCard>();
		CharacterCard MS = new CharacterCard(7, 24, "Miss Scarlett");
		temp.add(MS); charTemp.add(MS);
		CharacterCard CM = new CharacterCard(0, 17, "Colonel Mustard");
		temp.add(CM); charTemp.add(CM);
		CharacterCard MW = new CharacterCard(9, 0, "Mrs. White");
		temp.add(MW); charTemp.add(MW);
		CharacterCard MG = new CharacterCard(14, 0, "Mr. Green");
		temp.add(MG); charTemp.add(MG);
		CharacterCard MP = new CharacterCard(23, 6, "Mrs. Peacock");
		temp.add(MP); charTemp.add(MP);
		CharacterCard PP = new CharacterCard(23, 19, "Professor Plum");
		temp.add(PP); charTemp.add(PP);

		// Adding Room Cards.
		ArrayList<RoomCard> roomTemp = new ArrayList<RoomCard>();
		RoomCard k = new RoomCard("Kitchen");
		temp.add(k); roomTemp.add(k);
		RoomCard b = new RoomCard("Ballroom");
		temp.add(b); roomTemp.add(b);
		RoomCard c = new RoomCard("Conservatory");
		temp.add(c); roomTemp.add(c);
		RoomCard d = new RoomCard("Dining Room");
		temp.add(d); roomTemp.add(d);
		RoomCard br = new RoomCard("Billiard Room");
		temp.add(br); roomTemp.add(br);
		RoomCard l = new RoomCard("Library");
		temp.add(l); roomTemp.add(l);
		RoomCard lo = new RoomCard("Lounge");
		temp.add(lo); roomTemp.add(lo);
		RoomCard h = new RoomCard("Hall");
		temp.add(h); roomTemp.add(h);
		RoomCard s = new RoomCard("Study");
		temp.add(s); roomTemp.add(s);

		// Adding Weapon Cards.
		ArrayList<WeaponCard> weapTemp = new ArrayList<WeaponCard>();
		WeaponCard C = new WeaponCard("Candlestick");
		temp.add(C); weapTemp.add(C);
		WeaponCard D = new WeaponCard("Dagger");
		temp.add(D); weapTemp.add(D);
		WeaponCard L = new WeaponCard("Lead Pipe");
		temp.add(L); weapTemp.add(L);
		WeaponCard R = new WeaponCard("Revolver");
		temp.add(R); weapTemp.add(R);
		WeaponCard RO = new WeaponCard("Rope");
		temp.add(RO); weapTemp.add(RO);
		WeaponCard S = new WeaponCard("Spanner");
		temp.add(S); weapTemp.add(S);
		this.characterList = charTemp;
		this.roomList = roomTemp;
		this.weaponList = weapTemp;
		this.cardsWithMurder = temp;
	}

	public void setMurder() {
		// Grabs a random Weapon, Room, and Character from their respective Lists.
		// The Object in the specific list should be the same as the one in the CardsWithMurder list.
		WeaponCard weapon = this.weaponList.get(new Random().nextInt(weaponList.size()));
		RoomCard room = this.roomList.get(new Random().nextInt(roomList.size()));
		CharacterCard chara = this.characterList.get(new Random().nextInt(characterList.size()));

		this.murder = new Suggestion(weapon, chara, room);
		// Establishes a new set without the murder cards.
		ArrayList<Card> temp = this.cardsWithMurder;
		temp.remove(weapon);
		temp.remove(room);
		temp.remove(chara);
		// This new set will be used to distribute the non-murder cards to players.
		this.cards = temp;
	}

	public void distributeCards() {
		// 18 cards left in cards ArrayList.
		ArrayList<Card> temp = this.cards;
		Collections.shuffle(temp);

		// While loop; Stops when every card has been removed
		while (!temp.isEmpty()) {
			// For loop, to cycle through the player list.
			for (int i = 0; i < this.numPlayers; i++) {
				// Inner If Statement: In case cards cannot be distributed evenly.
				if (!temp.isEmpty()) {
					// Removes the first Card in temp, adds it to the player's own Card List.
					this.players.get(i).addCards(temp.remove(0));	
				}
			}
		}
	}

	//------------------------
	// PLAYER MOVES
	//------------------------

	public boolean suggest(Player suggester, Suggestion suggestion) {
		// Clones a new player list, without the suggester.
		ArrayList<Player> temp = this.getPlayerList();
		temp.remove(suggester);
		for (Player p : temp) {
			if (p.refute(suggestion)) {
				return false;
			}
		}
		// Nobody was able to refute.
		return true;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public ArrayList<Player> getPlayerList() {
		return this.players;
	}



}
