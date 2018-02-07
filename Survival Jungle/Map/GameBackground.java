package Map;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.xml.internal.security.Init;

public class GameBackground extends JPanel {
	private JPanel bgPanel;
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
		add(bgPanel);
	}

	public int getBgWidth() {
		return bgWidth;
	}

	public int getBgHeight() {
		return bgHeight;
	}
	
	
	
}