package Map;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Command.OnlineGame;
import Main.MenuPanel;

@SuppressWarnings("serial")
public class OnlineBoard extends JPanel implements ActionListener {
	
	private static final int WIDTH = 250;
	private static final int HEIGHT = 200;
	private String playerName;
	private JPanel panel;
	private JTextField text;
	int xx, xy;
	

	public OnlineBoard(MenuPanel bgPanel){
		setBackground(new Color(0,0,0,0));
		setBounds(280,400 , WIDTH, HEIGHT);	
		//setLayout(Grid)// dimension
		setFocusable(true);
		requestFocus();
		setVisible(true);
		
		JLabel label = new JLabel("Enter username");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//label.setForeground(Color.red);
		this.add(label);
		text = new JTextField();
		text.setBounds(150, 200,100, 100);
		this.add(text);
		text.setColumns(20);
		
		JButton button= new JButton("OK");
		button.setSize(500, 500);
		button.setBounds(600, 600, 500, 500);		
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//get username
				playerName = text.getText();
				System.out.println(playerName);
				//remove OnlineBoard panel and get HostJoinBoard panel
				HostJoinBoard hj = new HostJoinBoard(bgPanel);
				//bgPanel.remove(0);
				bgPanel.add(hj,0);
			}
		});
		this.add(button);
		//JButton button1 = new JButton("Back");
		
		//this.add(button1);
		
	
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
