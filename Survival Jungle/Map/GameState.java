package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Audio.*;
import Local.Camera;
import Local.Cell;
import Local.Forest;
import Local.Leaderboard;
import Local.Mud;
import Local.Particle;
import Local.Pool;



public class GameState {
	
	public static Leaderboard lb;
	public static Camera cam;
	private BufferedImage backBuffer;
	public static boolean playerCreated;
	private Graphics g;
	private JPanel jpanel;
	public String img1="/Resource/objects/bread.png";
	public String img2="/Resource/objects/cheese.png";
	public String img3="/Resource/objects/steak.png";
	private Audio_player music ;
	
	public GameState() {
		init();
	}
	
	public void init() {
		lb = new Leaderboard();
		cam = new Camera(0, 0, 1, 1);
		backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		playerCreated = false;
		
		music = new Audio_player("/Audio/music.mp3");
		music.play();
	}
	
	public void initDraw (Graphics graphics, JPanel jpanel) {	//get the graphics and panel to process draw method in here
		g = graphics;
		this.jpanel = jpanel;
		draw();		
	}
	
	public void draw() {
		Graphics bbg = backBuffer.getGraphics();
		Graphics bbg2 = backBuffer.getGraphics();
//		Graphics bbg3=backBuffer.getGraphics();

		bbg.setColor(Color.WHITE);	// set background color in the camera
		bbg.fillRect(0, 0, 800, 600);

		cam.set(bbg);
		
		ArrayList<Particle> pCopy = new ArrayList<Particle>(Particle.particles);	// this should be change to food
		for (Particle p : pCopy) {
//			p.Draw(bbg);
			p.Draw(bbg, jpanel);
		}
//		test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		ArrayList<Cheese> cCopy = new ArrayList<Cheese>(Cheese.cheeses);	// this should be change to food
//		for (Cheese c : cCopy) {
////			p.Draw(bbg);
//			c.Draw(bbg, jpanel);
//		}
		
		for (Cell cell : Cell.cells) {	// this should be change to player
			cell.Draw(bbg, jpanel);
		}
		
		ArrayList<Forest> fCopy=new ArrayList<Forest>(Forest.forests);
		for(Forest f: fCopy) {
			f.draw(bbg, jpanel);
		}
		ArrayList<Pool> poolCopy=new ArrayList<Pool>(Pool.pools);
		for(Pool p:poolCopy) {
			p.draw(bbg, jpanel);
		}
		ArrayList<Mud> mudCopy=new ArrayList<Mud>(Mud.muds);
		for(Mud m:mudCopy) {
			m.draw(bbg, jpanel);
		}
		cam.unset(bbg);

		for (Cell cell : Cell.cells) {
			if (cell.name.equals("Bruce")) {
				String pos = ("X: " + (int) cell.x + " Y: " + (int) cell.y);
				bbg2.setColor(Color.BLACK);
				bbg2.drawString(pos, (800 - pos.length() * pos.length()), 10);	// current location
				
				bbg2.drawRect(50, 700, 500, 50);
				bbg2.setColor(Color.YELLOW);
				bbg2.fillRect(50, 700, 500, 50);//currentExp
			}
		}
		
		lb.Draw(bbg2);

		g.drawImage(backBuffer, 0, 0, jpanel);
	}
	
	public void update() { 
		lb.Update();

		for (int i = 0; i < Cell.cells.size(); i++) {
			if (Cell.cells.get(i).name.equals("Bruce")) {
				cam.Update(Cell.cells.get(i));	// update current position
			}
		}

		if (Cell.cellCount < 150) {	// generate NPC
			Cell.cells.add(new Cell(("Cell " + Cell.cellCount), (int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 2801), false));
		}

		if (Particle.particleCount < 7000) {	// generate food
			String img1="/Resource/objects/bread.png";
			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), 1, false, img1));
			String img2="/Resource/objects/cheese.png";
			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), 1, false, img2));
			
			String img3="/Resource/objects/steak.png";
			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), 1, false, img3));
//			
//			String img4="/Resource/objects/water.png";
//			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
//					(int) Math.floor(Math.random() * 10001), 1, false, img4));
		}
		
//		test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		if (Cheese.particleCount < 5000) {	// generate food
//			Cheese.particles.add(new Cheese((int) Math.floor(Math.random() * 10001),
//					(int) Math.floor(Math.random() * 10001), 1, false));
//		}
		
		//Forest&&Pool&&Mud!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		if(Forest.forestCount < 500) {
			Forest.forests.add(new Forest((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001),400));
		}
		if(Pool.poolCount < 500) {
			Pool.pools.add(new Pool((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001),400));
		}
		if(Mud.mudCount < 500) {
			Mud.muds.add(new Mud((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001),400));
		}

		if (!playerCreated) {	// generate player
			playerCreated = true;
			Cell.cells.add(new Cell("Bruce", (int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), true));
		}

		for (Iterator<Particle> it = Particle.particles.iterator(); it.hasNext();) {
			Particle p = it.next();
			if (!p.getHealth()) {	// check the food been eaten or not
				p.Update(p.img);
			} else {
				it.remove();
			}
		}
//		test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		for (Iterator<Cheese> it2 = Cheese.cheeses.iterator(); it2.hasNext();) {
//			Cheese c = it2.next();
//			if (!c.getHealth()) {	// check the food been eaten or not
//				c.Update();
//			} else {
//				it2.remove();
//			}
//		}

		for (Cell cell : Cell.cells) {
			cell.Update();
		}
	}
	
	public void mouseMoved(MouseEvent e) {	// update current location
		for (Cell cell : Cell.cells) {
			if (cell.name.equals("Bruce")) {
				cell.getMouseX((int) (e.getX() / cam.sX + cam.x));
				cell.getMouseY((int) (e.getY() / cam.sY + cam.y));
			}
		}
	}
}
