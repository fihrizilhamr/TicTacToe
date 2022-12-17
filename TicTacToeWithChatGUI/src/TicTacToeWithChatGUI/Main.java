package TicTacToeWithChatGUI;

public class Main {
	public static void main(String[] args) {
		//Using the same structure as from the lecture code
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(model, view);
		controller.initView("Tic-Tac-Toe Game");
	}
}
