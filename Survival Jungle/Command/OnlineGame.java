package Command;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Map.OnlineBoard;


public class OnlineGame extends Command{

	private JPanel onlinegame;
	
	public OnlineGame() {
		onlinegame = new JPanel();
		onlinegame.add( new OnlineBoard());
	}
	public JPanel execute(){
		
		return onlinegame;
		
	}
}
