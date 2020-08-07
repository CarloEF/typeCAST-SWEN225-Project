public class Board {	

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	private Tile[][] board;
	private int width;
	private int height;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Board(int x, int y) {
		this.width = x;
		this.height = y;
		this.board = new Tile[x][y];
	}

	//------------------------
	// INTERFACE
	//------------------------

	public boolean checkAccessible(int x, int y) {
		// If out of bounds.
		if (x > this.width || y > this.height) {
			return false;
		}
		// If this Tile is passable.
		if (this.board[x][y].passable()) {
			return true;
		}
		return false;
	}

	public Tile[][] getBoard() {
		return this.board;
	}

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


}