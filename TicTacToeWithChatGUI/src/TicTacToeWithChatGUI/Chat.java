package TicTacToeWithChatGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat extends JPanel implements ActionListener{
	private JTextArea text;
	protected JTextField chatField;
	private String word = "Tic Tac Toe Game";
	private boolean Suspend = false;
	
	public Chat()
	{
		//custom color
		float colorArr[] = new float[3];
		Color.RGBtoHSB(44, 47, 51, colorArr);
		
		//Setting layout, size, and color for the panel
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350, 300));
		setBackground(Color.getHSBColor(colorArr[0], colorArr[1], colorArr[2]));
		
		//Creating JTextArea to be put on the panel with some features
		text = new JTextArea(word);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setFont(new Font("SansSerif", Font.PLAIN, 20));
		text.setForeground(Color.WHITE);
		text.setEditable(false);	
		text.setBackground(Color.getHSBColor(colorArr[0], colorArr[1], colorArr[2]));

		//Adding it to the panel
		add(text, BorderLayout.PAGE_START);
		
		//Adding chat
		//custom color
		float Arr[] = new float[3];
		Color.RGBtoHSB(153, 170, 181, Arr);
				
		chatField = new JTextField();
		add(chatField, BorderLayout.PAGE_END);
		chatField.setActionCommand("CHAT");
		chatField.setSize(new Dimension(350,80));
		chatField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		chatField.setBackground(Color.getHSBColor(Arr[0], Arr[1], Arr[2]));
	}
	
	//To set the current word for display
	public void setWord(String a)
	{
		text.setText(text.getText() + "\n");
		text.setText(text.getText() + a);
	}
	
	public void setWinner(String a)
	{
		if(!Suspend)
		{
			setWord("System : Player " + a + " Has Won The Game!");
			Suspend = true;
		}

	}
}