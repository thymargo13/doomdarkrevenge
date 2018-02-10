package Multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Local.Camera;
import Local.Leaderboard;
import Local.Particle;
import Network.Client.Client;

public class MultiplayerGameState {
	
	public static Leaderboard lb;
	public static Camera cam;
	private BufferedImage backBuffer;
	private Graphics g;
	private JPanel jpanel;
	
	// Multiplayer
	ArrayList<Client> Clients;
	private boolean isHost = false;
	
	// Multiplayer
	public MultiplayerGameState(ArrayList<Client> Clients, boolean isHost) {
		this.Clients = Clients;
		this.isHost = isHost;
		init();
	}
	
	public void init() {
		lb = new Leaderboard();
		cam = new Camera(0, 0, 1, 1);
		backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
	}
	
	public void initDraw (Graphics graphics, JPanel jpanel) {	//get the graphics and panel to process draw method in here
		g = graphics;
		this.jpanel = jpanel;
		draw();		
	}
	
	public void draw() {
		Graphics bbg = backBuffer.getGraphics();
		Graphics bbg2 = backBuffer.getGraphics();

		bbg.setColor(Color.WHITE);	// set background color in the camera
		bbg.fillRect(0, 0, 800, 600);

		cam.set(bbg);
		
		ArrayList<Particle> pCopy = new ArrayList<Particle>(Particle.particles);	// this should be change to food
		for (Particle p : pCopy) {
			p.Draw(bbg);
		}

		for (MultiplayerCell Cell : MultiplayerCell.cells) {	// this should be change to player
			Cell.Draw(bbg, jpanel);
		}

		cam.unset(bbg);
		

		for (MultiplayerCell Cell : MultiplayerCell.cells) {
			// The player
			if (Cell.id == Clients.get(0).getUserID()) {
				String pos = ("X: " + (int) Cell.x + " Y: " + (int) Cell.y);
				bbg2.setColor(Color.BLACK);
				bbg2.drawString(pos, (800 - pos.length() * pos.length()), 10);	// current location
			}
		}
		

		lb.Draw(bbg2);

		g.drawImage(backBuffer, 0, 0, jpanel);
	}
	
	public void update() { 
		lb.Update();
		
		for (int i = 0; i < MultiplayerCell.cells.size(); i++) {
			if (MultiplayerCell.cells.get(i).id == Clients.get(0).getUserID()) {
				cam.Update(MultiplayerCell.cells.get(i));	// update current position
			}
		}

		// Generate all players
		for (int i = MultiplayerCell.cellCount; i < Clients.size(); i++) {
			MultiplayerCell.cells.add(new MultiplayerCell(Clients.get(i).getUserID(), Clients.get(i).getUsername(), (int) Math.floor(Math.random() * 10001), (int) Math.floor(Math.random() * 2801), false));
		}
		
		if (!isHost) {
			// if not host
			// Get food from host
		} else {
			// if host generate foods
			if (Particle.particleCount < 5000) {	// generate food
				Particle.particles.add(new Particle((int) Math.floor(Math.random() * 10001), (int) Math.floor(Math.random() * 10001), 1, false));
				// add to server queue
			}
			
			for (Iterator<Particle> it = Particle.particles.iterator(); it.hasNext();) {
				Particle p = it.next();
				if (!p.getHealth()) {	// check the food been eaten or not
					p.Update();
					// add to server queue
				} else {
					it.remove();
					// add to server queue
				}
			}
		}
				
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			cell.Update();
		}
		
	}
	
	
	public void mouseMoved(MouseEvent e) {	// update current location
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			// Multiplayer
			if (cell.id == Clients.get(0).getUserID()){
				cell.getMouseX((int) (e.getX() / cam.sX + cam.x));
				cell.getMouseY((int) (e.getY() / cam.sY + cam.y));
				// add to server queue
			}
		}
	}
}
