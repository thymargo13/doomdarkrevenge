package Map;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Command.OnlineGame;
import Main.MenuPanel;

@SuppressWarnings("serial")
public class OnlineBoard extends JLayeredPane implements Runnable {

	//public MenuPanel panel;
	JFrame frame;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private JPanel btnPanel;
	private JPanel bgPanel;
	private String[] imgSrc = { "/Resource/background/ok_btn.png", "/Resource/background/Backbutton_1.png" };
	

	public OnlineBoard(JFrame frame) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		super();
		btnPanel = new JPanel();
		bgPanel = new JPanel();
																		// panel
		this.frame = frame; // get the main frame
		setLayout(new BorderLayout()); // root panel layout
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));// root
																					// dimension
		setFocusable(true);
		requestFocus();
		init();
		setVisible(true);
		
	}

	private void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		// running = true;
		setBackground();
		setBtnPanel(); // set Button Panel
		add(btnPanel); // add Button Panel to BasePanel
		add(bgPanel);
	}
	
	private void setBackground() {
		bgPanel.setBounds(0, 0, WIDTH, HEIGHT);
		Icon background_img = new ImageIcon(getClass().getResource("/Resource/background/bg.gif"));
		JLabel background = new JLabel(background_img);
		background.setIcon(background_img);
		bgPanel.add(background);
		bgPanel.setOpaque(false);
	}
	
	private void setBtnPanel() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		btnPanel.setBackground(new Color(0, 0, 0, 0));
		btnPanel.setBounds(280, 400, 250, 200);
		btnPanel.setOpaque(true);
		btnPanel.setLayout(new GridLayout(4, 0)); // GridLayout(int rows, int
													// cols, int hgap, int vgap)
		
		for (int i = 0; i < imgSrc.length; i++) {
			
				btnPanel.add(new Button(imgSrc[i]),i);
			}
	}
	
	
@Override
public void run() {
	// TODO Auto-generated method stub
	
}
}