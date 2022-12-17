package TicTacToeWithChatGUI;

public class Controller {
	//Using the same structure as in the lecture for the controller
	//with little change
	private Model model;
	private View view;
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	public void initView(String title) {
		view.init(title);
	}
}