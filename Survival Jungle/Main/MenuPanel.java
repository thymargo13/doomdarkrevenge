package Main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Command.Command;

//import GameState.GameStateManager;

//make the menuPanel.
@SuppressWarnings("serial")
public class MenuPanel extends JLayeredPane implements Runnable {

	JFrame frame;

	// ALL Panel
	private JPanel btnPanel;
	// private JLayeredPane basePanel;
	private JPanel bgPanel;
	// arrayList for imgSrc
	private String[] imgSrc = { "/Resource/background/btn_local_2.png", "/Resource/background/btn_online_2.png",
			"/Resource/background/btn_setting_2.png", "/Resource/background/btn_exit_2.png" };

	// dimensions
	public static final int WIDTH = 800; // background width
	public static final int HEIGHT = 600; // background height

	// constructor for BasePanel
	public MenuPanel(JFrame frame) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		super();
		btnPanel = new JPanel(); // instance for btn panel.
		bgPanel = new JPanel(); // instance for bg panel
		this.frame = frame; // get the main frame
		setLayout(new BorderLayout()); // root panel layout
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));// root panel dimension
		setFocusable(true);
		requestFocus();

		init();
		setVisible(true);
		// gsm = new GameStateManager();
	}

	// build the base panel component.
	private void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		// running = true;
		setBackground();// set Background Panel
		setBtnPanel(); // set Button Panel
		add(btnPanel); // add Button Panel to BasePanel
		add(bgPanel); // add Background Panel to BasePanel
	}

	// Setting Background panel and add the background image.
	private void setBackground() {
		bgPanel.setBounds(0, 0, WIDTH, HEIGHT);
		Icon background_img = new ImageIcon(getClass().getResource("/Resource/background/bg.gif"));
		JLabel background = new JLabel(background_img);
		background.setIcon(background_img);
		bgPanel.add(background);
		bgPanel.setOpaque(false);
	}

	// Setting the Button Panel and add btn into bthPanel
	private void setBtnPanel() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		btnPanel.setBackground(new Color(0, 0, 0, 0));
		btnPanel.setBounds(280, 400, 250, 200);
		btnPanel.setOpaque(true);
		btnPanel.setLayout(new GridLayout(4, 0)); // GridLayout(int rows, int cols, int hgap, int vgap)

		for (int i = 0; i < imgSrc.length; i++) {
			btnPanel.add(new Button(imgSrc[i], i));
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}