package tictactoe;

public class TicTacToeProtocol{
    
	public static int SESSION = 1;
    public static final char CROSS = 'X';
	public static final char CIRCLE = 'O';
	public static char lastPlayed = 'X';
	
	private static char cell[][] = new char[3][3];
	
	//constructor
	public TicTacToeProtocol() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = ' ';
            }
        }
    }
	
	//reset board
	synchronized public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = ' ';
            }
        }
        SESSION++;
    }
	
	//check if its valid move
	boolean isValidMove(char move, int row, int col) {
		if (move == CROSS || move == CIRCLE) {
			if (cell[row][col] == ' ')
				return true;
		}
		return false;
	}
	
	//check if its current player's turn
	private boolean isCurrentPlayerTurn(char move) {
		if (move == lastPlayed) {
			return false;
		}
		return true;
	}
	
	char currentMove() {
		if (lastPlayed == CROSS)
			return CIRCLE;
		else
			return CROSS;
	}
	
	//make a move
	synchronized String makeMove(char move, int row, int col) {
		if (!isCurrentPlayerTurn(move)) {
			return "~~ Its NOT your turn yet!!! ~~";
		}
		String data = "~~ Invalid Move, Bro! ~~";
		if (cell[row][col] == ' ') {
				cell[row][col] = move;
				lastPlayed = move;
				if (isWon(move)) {
					data = printBoard() + "\n$$$ Game Over! Good Game, " + move + " is the winner... $$$";
				} else if (isFull()) {
					data = printBoard() + "~~ Game Draw!... ~~";
				} else {
					data = printBoard() + "\nMove Updated!";
				}
		}
		return data;
	}
	
	//check if the board is full/ draw
	private boolean isFull() {
		// checking the entire board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cell[i][j] == ' ')
					return false;
			}
		}
		return true;
	}
	
	//check if current player achieve victory
	private boolean isWon(char token) {
		// checking main diagonal
		if ((cell[0][0] == token) && (cell[1][1] == token) && (cell[2][2] == token))
			return true;
		// checking second diagonal
		if ((cell[0][2] == token) && (cell[1][1] == token) && (cell[2][0] == token))
			return true;
		// checking rows
		for (int i = 0; i < 3; i++) {
			if ((cell[i][0] == token) && (cell[i][1] == token) && (cell[i][2] == token)) {
				return true;
			}
		}
		// checking columns
		for (int j = 0; j < 3; j++) {
			if ((cell[0][j] == token) && (cell[1][j] == token) && (cell[2][j] == token))
				return true;
		}
		return false;
	}
	
	//printing the entire board
	public String printBoard() {
        return ("|---|---|---|\n| " + cell[0][0] + " | " + cell[0][1] + " | " + cell[0][2] + " |\n|---|---|---|\n| " + cell[1][0] + " | " + cell[1][1] + " | " + cell[1][2] + " |\n|---|---|---|\n| " + cell[2][0] + " | " + cell[2][1] + " | " + cell[2][2] + " |\n|---|---|---|");
    }
}
