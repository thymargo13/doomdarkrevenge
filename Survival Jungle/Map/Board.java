package Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.Mouse;
import Entity.Player;

public class Board extends JPanel implements ActionListener {

	private final int B_WIDTH = 1600;
	private final int B_HEIGHT = 1200;
	private final int DELAY = 25;
	private Timer timer;

	private Player player;
	
	private int i=0;

	public Board() {
		setLayout(null);
		initBoard();
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				player.setDx(e.getX());
				player.setDy(e.getY());
			}
		});
	}

	private void initBoard() {
		player = new Mouse();
		setBackground(new Color(0, 195, 0));
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setDoubleBuffered(true);
		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		draw(g);
	}

	private void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		player.draw(g2d, this);
		
//		g2d.rotate(Math.toRadians(i++), player.getX(), player.getY());
//		g2d.drawImage(player.getImage(), player.getX()-50, player.getY()-50, 100, 100, this);
//		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.move();
		repaint();
	}
}
