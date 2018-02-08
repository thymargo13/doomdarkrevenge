package Local;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Local extends JFrame implements MouseMotionListener {

	public static boolean playerCreated = false;

	boolean isRunning = true;
	int fps = 60;
	public static int width = 800;
	public static int height = 600;

	BufferedImage backBuffer;
	Insets insets;
	public static Leaderboard lb = new Leaderboard();
	public static Camera cam = new Camera(0, 0, 1, 1);
	//
	// public static void main(String[] args){
	// Local local = new Local();
	// local.run();
	// System.exit(0);
	// }

	public void run() {
		initialize();

		while (isRunning) {
			long time = System.currentTimeMillis();

			update();
			draw();
			time = (1000 / fps) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}

		setVisible(false);
	}

	public void initialize() {
		requestFocus();
		setTitle("Local Game");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		addMouseMotionListener(this);
		addKeyListener(new KeyInput(this));

		insets = getInsets();
		setSize(insets.left + width + insets.right, insets.top + height + insets.bottom);

		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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

	public void draw() {
		Graphics g = getGraphics();

		Graphics bbg = backBuffer.getGraphics();
		Graphics bbg2 = backBuffer.getGraphics();

		Graphics2D g2 = (Graphics2D) bbg;

		bbg.setColor(new Color(0, 195, 0));
		bbg.fillRect(0, 0, width, height);

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

		g.drawImage(backBuffer, insets.left, insets.top, this);
	}

	public void mouseMoved(MouseEvent e) {
		System.out.print("X: " + e.getX() + ", Y: " + e.getY());
		for (Cell cell : Cell.cells) {
			if (cell.name.equals("Bruce")) {
				cell.getMouseX((int) (e.getX() / cam.sX + cam.x));
				cell.getMouseY((int) (e.getY() / cam.sY + cam.y));
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 87) {
			for (Cell cell : Cell.cells) {
				if (cell.name.equals("Bruce")) {
					cell.shootMass();
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public static String print(String x) {
		System.out.println(x);
		return "";
	}
}