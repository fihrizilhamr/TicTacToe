package TicTacToeWithChatGUI;

public class Model {
	private String[] board;
	private String currentPlayer;
	private boolean Suspend = false;
	Model()
	{
		//initialize board to empty
		board = new String[9];
		currentPlayer = "X";
	}
	
	void placePieces(int places)
	{	
		if(!Suspend)
		{
			if(board[places] == null)
			{
				board[places] = currentPlayer;
				currentPlayer = currentPlayer == "X" ? "O" : "X";  
			}
		}
		//else do nothing
	}
	
	public boolean hasWinner() {
        return (board[0] != null && board[0].equals(board[1]) && board[0].equals(board[2]))
                || (board[3] != null && board[3].equals(board[4]) && board[3].equals(board[5]))
                || (board[6] != null && board[6].equals(board[7]) && board[6].equals(board[8]))
                || (board[0] != null && board[0].equals(board[3]) && board[0].equals(board[6]))
                || (board[1] != null && board[1].equals(board[4]) && board[1].equals(board[7]))
                || (board[2] != null && board[2].equals(board[5]) && board[2].equals(board[8]))
                || (board[0] != null && board[0].equals(board[4]) && board[0].equals(board[8]))
                || (board[2] != null && board[2].equals(board[4]) && board[2].equals(board[6]));
    }

	public boolean boardFilledUp() {
        for (int i = 0; i < board.length; ++i) {
            if (board[i] == null) {
                return false;
            }
        }

        return true;
    }
	public String getMark(int place)
	{
		return board[place];
	}
	
	public void SuspendBoard()
	{
		Suspend = true;
	}
}
