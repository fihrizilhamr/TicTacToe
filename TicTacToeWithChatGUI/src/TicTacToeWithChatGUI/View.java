package TicTacToeWithChatGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.RMIFailureHandler;

import javax.swing.JFrame;

public class View implements ActionListener{
	//This is the GUI 
	
	//Making 3 object
	//we used 2 separate object to be used in the JFrame (first and second)
	private Chat chat = new Chat();
	private Game game = new Game();
	private Model model = new Model();
	public void init(String title) {
		
		//The same setup from the lecture code
	    JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame(title);
		Container pane = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(800, 500));
		frame.setResizable(false);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) screen.getWidth() / 2 - frame.getWidth() / 2,
                (int) screen.getHeight() / 2 - frame.getHeight() / 2);

        
        //Adding actionListener for the keypad or button for the GUI
        for(int i = 0 ; i < 9; i++)
        {
        	game.keypad[i].addActionListener(this);
        }
		chat.chatField.addActionListener(this);
        
		//Adding the first and second object to the GUI
		pane.add(game, BorderLayout.CENTER);
		pane.add(chat, BorderLayout.LINE_END);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 String s = e.getActionCommand();
		 if(s.equals("CHAT"))
		 {
			 String str = chat.chatField.getText();
			 chat.setWord(str);
		 }
		 else if (s.equals("ZERO")) 
		 {
			 model.placePieces(0);
			 game.setPieces(0, model.getMark(0));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(0));
			 }
		 } 
		 else if (s.equals("ONE")) 
		 {
			 model.placePieces(1);
			 game.setPieces(1, model.getMark(1));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(1));
			 }
		 } 
		 else if (s.equals("TWO")) 
		 {
			 model.placePieces(2);
			 game.setPieces(2, model.getMark(2));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(2));
			 }
		 } 
		 else if (s.equals("THREE")) 
		 {
			 model.placePieces(3);
			 game.setPieces(3, model.getMark(3));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(3));
			 }
		 }
		 else if (s.equals("FOUR")) 
		 {
			 model.placePieces(4);
			 game.setPieces(4, model.getMark(4));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(4));
			 }
		 }
		 else if (s.equals("FIVE")) 
		 {
			 model.placePieces(5);
			 game.setPieces(5, model.getMark(5));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(5));
			 }
		 }
		 else if (s.equals("SIX")) 
		 {
			 model.placePieces(6);
			 game.setPieces(6, model.getMark(6));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(6));
			 }
		 }
		 else if (s.equals("SEVEN")) 
		 {
			 model.placePieces(7);
			 game.setPieces(7, model.getMark(7));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(7));
			 }
		 }
		 else if (s.equals("EIGHT")) 
		 {
			 model.placePieces(8);
			 game.setPieces(8, model.getMark(8));
			 if(model.hasWinner())
			 {
				 model.SuspendBoard();
				 chat.setWinner(model.getMark(8));
			 }
		 }
	}
}