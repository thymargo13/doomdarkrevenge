package Map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.xml.internal.security.Init;

import Command.Command;
import Entity.Mouse;
import Entity.Player;

public class GameBackground extends JPanel {
	private JPanel bgPanel;
	private Player player;
	private int bgWidth;
	private int bgHeight;
	
	public GameBackground() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setSize(800, 600);
		bgWidth = 1600;
		bgHeight = 1200;
		
		setBackground();
	}
	
	private void setBackground() {
		bgPanel = new JPanel();
		bgPanel.setBackground(new Color(0, 195, 0));
		bgPanel.setSize(bgWidth, bgHeight);
		
		
		NewPlayer();
		
		
		add(bgPanel);
	}
	
	public void NewPlayer() {
		player = new Mouse();
		String imgStr = player.getImg();
		
		
		Icon playerIcon = new ImageIcon(getClass().getResource(imgStr));
		JLabel playerLabel = new JLabel(playerIcon);
		playerLabel.setIcon(playerIcon);
		bgPanel.add(playerLabel);
	}

	public int getBgWidth() {
		return bgWidth;
	}

	public int getBgHeight() {
		return bgHeight;
	}
	
	
	
}