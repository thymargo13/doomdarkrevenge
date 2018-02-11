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
import Network.Network;
import Network.Server.Client;

public class MultiplayerGameState {
	
	public static Leaderboard lb;
	public static Camera cam;
	private BufferedImage backBuffer;
	private Graphics g;
	private JPanel jpanel;
	
	// Multiplayer
	public ArrayList<Client> Clients;
	private boolean isHost = false;
	Network Network;
	private boolean isRunning = false;
	
	// Multiplayer
	public MultiplayerGameState(ArrayList<Client> Clients, boolean isHost, Network Network) {
		this.Clients = Clients;
		this.isHost = isHost;
		this.Network = Network;
		isRunning = true;
		
//		MultiplayerData MultiplayerData = new MultiplayerData(this);
//		Thread MultiplayerThread = new Thread(MultiplayerData);
//		MultiplayerThread.start();
		
		init();
	}
	
	public void init() {
		lb = new Leaderboard(true);
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
		
		if (!isHost) {
			// if not host
			// Get food from host
		} else {
			
			for (int i = MultiplayerCell.cellCount; i < Clients.size(); i++) {
				boolean isPlayer = false;
				if (i == 0) {
					isPlayer = true;
				}
				int x = (int) Math.floor(Math.random() * 10001);
				int y = (int) Math.floor(Math.random() * 2801);
				MultiplayerCell.cells.add(new MultiplayerCell(Clients.get(i).getUserID(), Clients.get(i).getUsername(),x , y, isPlayer));
				Network.sendAsServer("CELLADD:" + Clients.get(i).getUserID() + ":" + x + ":" + y );
				// Send as server to all
			}
			// if host then generate foods
			// DEBUG
			if (Particle.particleCount < 500) {	// generate food
				int x = (int) Math.floor(Math.random() * 10001);
				int y = (int) Math.floor(Math.random() * 10001);
				Particle.particles.add(new Particle(x, y, 1, false));
				Network.sendAsServer("FOODADD:" + x + ":" + y);
				// add to server queue
			}
		}
		
		for (Iterator<Particle> it = Particle.particles.iterator(); it.hasNext();) {
			Particle p = it.next();
			if (!p.getHealth()) {	// check the food been eaten or not
				p.Update(true);
				// add to server queue
			} else {
				it.remove();
				if (!isHost) {
					Clients.get(0).sendMessage("SCORE:" + Clients.get(0).getUserID() + ":"+ MultiplayerCell.cells.get(0).mass);
				} else {
					Network.sendAsServer("SCORE:" + Clients.get(0).getUserID() + ":"+ MultiplayerCell.cells.get(0).mass);
				}
				// add to server queue
			}
		}
				
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			cell.Update();
		}
		
	}
	
	public void generateFood(int x, int y) {
		int mass = 1;
		boolean p = false;
		Particle.particles.add(new Particle(x, y, mass, p));
	}
	
	public void mouseMoved(MouseEvent e) {	// update current location
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			// Multiplayer
			if (cell.id == Clients.get(0).getUserID()){
				cell.getMouseX((int) (e.getX() / cam.sX + cam.x));
				cell.getMouseY((int) (e.getY() / cam.sY + cam.y));
				// add to server queue
				if (!isHost) {
					Clients.get(0).sendMessage("MOVE:" + Clients.get(0).getUserID() + ":" + ((int)(e.getX() / cam.sX + cam.x)) +":" + ((int)(e.getY() / cam.sY + cam.y)));
				} else {
					Network.sendAsServer("MOVE:" + Clients.get(0).getUserID() + ":" + ((int)(e.getX() / cam.sX + cam.x)) +":" + ((int)(e.getY() / cam.sY + cam.y)));
				}
			}
		}
	}
	
	public boolean getRunning() {
		return isRunning;
	}
	
	public boolean getIsHost() {
		return isHost;
	}
}
