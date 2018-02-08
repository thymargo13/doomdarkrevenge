package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Local.Camera;
import Local.Cell;
import Local.Leaderboard;
import Local.Local;
import Local.Particle;



public class GameState {
	
	public static Leaderboard lb;
	public static Camera cam;
	private BufferedImage backBuffer;
	public static boolean playerCreated;
	private Graphics g;
	private JPanel jpanel;
	
	public GameState() {
		init();
	}
	
	public void init() {
		lb = new Leaderboard();
		cam = new Camera(0, 0, 1, 1);
		backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		playerCreated = false;
	}
	
	public void initDraw (Graphics graphics, JPanel jpanel) {
		g = graphics;
		this.jpanel = jpanel;
		draw();		
	}
	
	public void draw() {
		Graphics bbg = backBuffer.getGraphics();
		Graphics bbg2 = backBuffer.getGraphics();

		Graphics2D g2 = (Graphics2D) bbg;

		bbg.setColor(Color.BLACK);
		bbg.fillRect(0, 0, 800, 600);

		cam.set(bbg);
		
		ArrayList<Particle> pCopy = new ArrayList<Particle>(Particle.particles);
		for (Particle p : pCopy) {
			p.Draw(bbg);
		}

		for (Cell cell : Cell.cells) {
			cell.Draw(bbg);
		}

		cam.unset(bbg);

		for (Cell cell : Cell.cells) {
			if (cell.name.equals("Bruce")) {
				String pos = ("X: " + (int) cell.x + " Y: " + (int) cell.y);
				bbg2.drawString(pos, (Local.width - pos.length() * pos.length()), 10);
			}
		}

		lb.Draw(bbg2);

		g.drawImage(backBuffer, 0, 0, jpanel);
	}
	
	public void update() { 
		lb.Update();

		for (int i = 0; i < Cell.cells.size(); i++) {
			if (Cell.cells.get(i).name.equals("Bruce")) {
				cam.Update(Cell.cells.get(i));
			}
		}

		if (Cell.cellCount < 150) {
			Cell.cells.add(new Cell(("Cell " + Cell.cellCount), (int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 2801), false));
		}

		if (Particle.particleCount < 5000) {
			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), 1, false));
		}

		if (!playerCreated) {
			playerCreated = true;
			Cell.cells.add(new Cell("Bruce", (int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), true));
		}

		for (Iterator<Particle> it = Particle.particles.iterator(); it.hasNext();) {
			Particle p = it.next();
			if (!p.getHealth()) {
				p.Update();
			} else {
				it.remove();
			}
		}

		for (Cell cell : Cell.cells) {
			cell.Update();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		for (Cell cell : Cell.cells) {
			if (cell.name.equals("Bruce")) {
				cell.getMouseX((int) (e.getX() / cam.sX + cam.x));
				cell.getMouseY((int) (e.getY() / cam.sY + cam.y));
			}
		}
	}
}
