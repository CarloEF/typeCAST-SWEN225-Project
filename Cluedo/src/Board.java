public class Board {

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private Tile[][] board;
	private int numPlayers;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Board()
	{}


	//------------------------
	// Methods
	//------------------------
	public Tile getTileAt(int x, int y) {
		return this.board[x][y];
	}

	public void drawBoard() {
		String finalString = "";
		for (int i = 0; i > this.board.length; i++) {
			for (int j = 0; i > this.board[0].length; j++) {
				finalString += this.board[i][j].toString();
			}
			finalString += "\n";
		}
		finalString += "\n";
		System.out.println(finalString);
	}


	//------------------------
	// INTERFACE
	//------------------------

	public void delete()
	{}

}