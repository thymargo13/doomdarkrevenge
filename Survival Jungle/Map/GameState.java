package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import Command.Setting;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Audio.*;
import Entity.foodonMap;
import Local.Camera;
import Local.Cell;
import Local.Forest;
import Local.Leaderboard;
import Local.Mud;
import Local.Particle;
import Local.Pool;

/**
 * @author Wing Shan Tam, Hiu Yin Tang
 * 
 * Draws the score board and draws the forest, pool, mud onto the game
 * Generates food and the AI players
 * Gets the player's location and Exp points
 * 
 * @author Bitnarae Kim
 *  Plays the music if it is not muted and continously loops it
 */

public class GameState {
	
	public static Leaderboard lb;
	public static Camera cam;
	private BufferedImage backBuffer;
	public static boolean playerCreated;
	private Graphics g;
	private JPanel jpanel;
	private Audio_player music;
	private String bgImg = "/Resource/background/bgbg.png";
	
	public GameState() {
		init();
	}
	
	public void init() {
		lb = new Leaderboard();
		cam = new Camera(0, 0, 1, 1);
		backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		playerCreated = false;
		
		Setting Music = new Setting();
		//gets the music playing at the menu page
		BooleanControl mutecontrol = (BooleanControl) Audio_player.clip.getControl(BooleanControl.Type.MUTE);
		System.out.println(Music.getMute());
		//gets the music
		mutecontrol.setValue(Music.getMute());
		Audio_player.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void initDraw (Graphics graphics, JPanel jpanel) {	//get the graphics and panel to process draw method in here
		g = graphics;
		this.jpanel = jpanel;
		draw();		
	}
	
	public void draw() {
		Graphics bbg = backBuffer.getGraphics();
		Graphics bbg2 = backBuffer.getGraphics();
		ImageIcon bg = new ImageIcon(getClass().getResource(bgImg));
		bbg.drawImage(bg.getImage(), (int) 0, (int) 0, (int) 800, (int) 600, null);
//		bbg.setColor(Color.);	// set background color in the camera
//		bbg.fillRect(0, 0, 800, 600);

		cam.set(bbg);
		
		ArrayList<Particle> pCopy = new ArrayList<Particle>(Particle.particles);	// this should be change to food
		for (Particle p : pCopy) {
//			p.Draw(bbg);
			p.Draw(bbg, jpanel);
		}
		
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
			if (cell.name.equals("Player")) {
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
			if (Cell.cells.get(i).name.equals("Player")) {
				cam.Update(Cell.cells.get(i));	// update current position
			}
		}

		if (Cell.cellCount < 150) {	// generate NPC
			Cell.cells.add(new Cell(("Animal " + Cell.cellCount), (int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), false));
		}

		if (Particle.particleCount < 10000) {	// generate food
			String imgBread=foodonMap.getBread();
			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), 1, false, imgBread));
			String imgCheese=foodonMap.getCheese();
			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), 1, false, imgCheese));
			
			String imgSteak=foodonMap.getSteak();
			Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), 1, false, imgSteak));
		}

		
		//Forest & Pool & Mud!!
		
		if(Forest.forestCount < 1000) {
	//		int dForest=Forest.getD();
			Forest.forests.add(new Forest((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001),270));
		}
		if(Pool.poolCount < 1500) {
				Pool.pools.add(new Pool((int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001),270));
		}
		if(Mud.mudCount < 1000) {
				Mud.muds.add(new Mud((int) Math.floor(Math.random() * 10001),
						(int) Math.floor(Math.random() * 10001),270));
		}
				
		if (!playerCreated) {	// generate player
			playerCreated = true;
			Cell.cells.add(new Cell("Player", (int) Math.floor(Math.random() * 10001),
					(int) Math.floor(Math.random() * 10001), true));
		}

		for (Iterator<Pool> pl = Pool.pools.iterator(); pl.hasNext();) {
			Pool p = pl.next();
			
			if (p.isShot) {	// check the food been eaten or not

				p.Update();
			}
		}
		
		for (Iterator<Forest> ft = Forest.forests.iterator(); ft.hasNext();) {
			Forest f = ft.next();
			
			if (!f.couldHide) {	// check the food been eaten or not
				
				f.Update();
			}
		}
		
		for (Iterator<Mud> md = Mud.muds.iterator(); md.hasNext();) {
			Mud m=md.next();
			for (Cell cell : Cell.cells) {
				if(cell.levelNum == 0) {
					
				}else {
					m.Update();
				}
				
			}
		}
		for (Iterator<Particle> it = Particle.particles.iterator(); it.hasNext();) {
			Particle p = it.next();
			if (!p.getHealth()) {	// check the food been eaten or not
				p.Update();
			} else {
				it.remove();
			}
		}
		
		for (Cell cell : Cell.cells) {
			cell.Update();
		}
	}
	
	public void mouseMoved(MouseEvent e) {	// update current location
		for (Cell cell : Cell.cells) {
			if (cell.name.equals("Player")) {
				cell.getMouseX((int) (e.getX() / cam.sX + cam.x));
				cell.getMouseY((int) (e.getY() / cam.sY + cam.y));
			}
		}
	}
}
