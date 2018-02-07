package Map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		bgPanel.setLayout(null);
		bgPanel.setBackground(new Color(0, 195, 0));
		bgPanel.setSize(bgWidth, bgHeight);
		NewPlayer();
		add(bgPanel);
	}
	
	public void NewPlayer() {
		player = new Mouse();
		String imgStr = player.getImg();
		ImageIcon playerIcon = new ImageIcon(getClass().getResource(imgStr));
		Image img =playerIcon.getImage();
		playerIcon.setImage(getScaledImage(img, 100, 100));
		JLabel playerLabel = new JLabel();
		playerLabel.setIcon(playerIcon);
		playerLabel.setAlignmentX(1500);
		playerLabel.setBounds(500, 300, playerIcon.getIconWidth(), playerIcon.getIconHeight());
		System.out.println(playerLabel.getBounds());
		bgPanel.add(playerLabel);
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

	public int getBgWidth() {
		return bgWidth;
	}

	public int getBgHeight() {
		return bgHeight;
	}
	
	
	
}