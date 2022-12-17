package TicTacToeWithChatGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Game extends JPanel{
	//Creating 9 button
	protected JButton keypad[] = new JButton[912];;
	
	public Game()
	{
		//Setting the layout size and grid and also the location
		setLayout(new GridLayout(3, 3));
		setSize(new Dimension(300, 300));
		setLocation(new Point(0, 0));
		
		//Giving each JButton a text
		JButton one = new JButton();
		JButton two = new JButton();
		JButton three = new JButton();
		JButton four = new JButton();
		JButton five = new JButton();
		JButton six = new JButton();
		JButton seven = new JButton();
		JButton eight = new JButton();
		JButton nine = new JButton();

		
		
		//assigning each JButton to the keypad
		keypad[0] = one;
		keypad[1] = two;
		keypad[2] = three;
		
		keypad[3] = four;
		keypad[4] = five;
		keypad[5] = six;
		
		keypad[6] = seven;
		keypad[7] = eight;
		keypad[8] = nine;
		

		
		//Giving command for each keypad
		keypad[0].setActionCommand("ZERO");
		keypad[1].setActionCommand("ONE");
		keypad[2].setActionCommand("TWO");
		keypad[3].setActionCommand("THREE");
		keypad[4].setActionCommand("FOUR");
		keypad[5].setActionCommand("FIVE");
		keypad[6].setActionCommand("SIX");
		keypad[7].setActionCommand("SEVEN");
		keypad[8].setActionCommand("EIGHT");

		
		
		//custom color
		float colorArr[] = new float[3];
		Color.RGBtoHSB(114, 137, 218, colorArr);
		
		
		//Giving feature for each keypad
		for(int i = 0; i < 9; i++){
			keypad[i].setFocusable(false);
			keypad[i].setVerticalTextPosition(JButton.CENTER);
			keypad[i].setHorizontalTextPosition(JButton.CENTER);
			keypad[i].setBackground(Color.getHSBColor(colorArr[0], colorArr[1], colorArr[2]));
			keypad[i].setForeground(Color.black);
			keypad[i].setBorder(BorderFactory.createLineBorder(null, 3));
		}

		//Adding all keypad to the panel
		for(int i= 0; i < 9; i++){
			add(keypad[i]);
		}
	}
	
	public void setPieces(int place, String mark)
	{
		keypad[place].setLabel(mark);
		keypad[place].setFont(new Font("SansSerif", Font.PLAIN, 40));
	}
}