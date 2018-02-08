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
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.*;


public class Board extends JPanel implements ActionListener {

	private final int B_WIDTH = 1600;
	private final int B_HEIGHT = 1200;
	private final int DELAY = 25;
	private Timer timer;

	private Player player;
	private Food redDotFood;

	public Board() {
		setLayout(null);
		initBoard();
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				player.setX(e.getX());
				player.setY(e.getY());
				player.setDx(e.getX());
				player.setDy(e.getY());
//				int new_x  =(int)(player.getX()+ ((e.getX()-player.getX())*0.1));
//				player.setX(new_x);
//				int new_y  =(int)(player.getY()+ ((e.getY()-player.getY())*0.1));
//				player.setX(new_y);
				//player.move();
//				player.setX(player.getX() +(int)(e.getX()*0.1));
//				player.setY(player.getY() + (int)(e.getY()*0.1));
			}
		});
	}

	private void initBoard() {
		player = new Mouse();
		redDotFood = new RedDotFood();
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
		player.draw(g2d,this);
		redDotFood.draw(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.move();
		repaint();
	}
}
