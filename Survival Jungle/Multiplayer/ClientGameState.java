package Multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;

import Audio.Audio_player;
import Entity.foodonMap;
import Local.Cell;
import Local.Forest;
import Local.Mud;
import Local.Particle;
import Local.Pool;
import Network.Network;
import Network.Server.Client;

public class ClientGameState implements ActionListener{
	
	public static MultiplayerLeaderboard lb;
	public static MultiplayerCamera cam;
	private BufferedImage backBuffer;
	private Graphics g;
	private JPanel jpanel;
	
	// Multiplayer
	public ArrayList<Client> Clients;
	private boolean isHost = false;
	Network Network;
	private boolean isRunning = false;
	private Audio_player music ;
	private final int DELAY = 10;	// milliseconds delay
	private Timer timer;
	
	public ClientGameState(ArrayList<Client> Clients, Network Network) {
		this.Clients = Clients;
		this.Network = Network;
		isRunning = true;
	
		timer = new Timer(DELAY, this);	// Every DELAY ms the timer will call the actionPerformed()
		timer.start();
		
		init();
		
	}
	
	public void init() {
		lb = new MultiplayerLeaderboard();
		cam = new MultiplayerCamera(0, 0, 1, 1);
		backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
//		music = new Audio_player("/Audio/music.mp3");
//		music.play();	
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
		
		ArrayList<MultiplayerParticle> pCopy = new ArrayList<MultiplayerParticle>(MultiplayerParticle.particles);	// this should be change to food
		for (MultiplayerParticle p : pCopy) {
			p.Draw(bbg,jpanel);
		}

		for (MultiplayerCell Cell : MultiplayerCell.cells) {	// this should be change to player
			Cell.Draw(bbg, jpanel);
		}
			
		ArrayList<MultiplayerForest> fCopy=new ArrayList<MultiplayerForest>(MultiplayerForest.forests);
		for(MultiplayerForest f: fCopy) {
			f.draw(bbg, jpanel);
		}
		
		ArrayList<MultiplayerPool> poolCopy=new ArrayList<MultiplayerPool>(MultiplayerPool.pools);
		for(MultiplayerPool p:poolCopy) {
			p.draw(bbg, jpanel);
		}
		ArrayList<MultiplayerMud> mudCopy=new ArrayList<MultiplayerMud>(MultiplayerMud.muds);
		for(MultiplayerMud m:mudCopy) {
			m.draw(bbg, jpanel);
		}

		cam.unset(bbg);
		

		for (MultiplayerCell Cell : MultiplayerCell.cells) {
			// The player
			if (Cell.id == Clients.get(0).getUserID()) {
				String pos = ("X: " + (int) Cell.x + " Y: " + (int) Cell.y);
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
		
		try {
			for (int i = 0; i < MultiplayerCell.cells.size(); i++) {
				if (MultiplayerCell.cells.get(i).id == Clients.get(0).getUserID()) {
					cam.Update(MultiplayerCell.cells.get(i));	// update current position
				}
			}
			
			
			for (MultiplayerParticle p : MultiplayerParticle.particles) {
				if (!p.getHealth()) {	// check the food been eaten or not
					p.Update(this);
				} else {
					MultiplayerParticle.particles.remove(p);
				}
			}
			
					
			for (MultiplayerCell cell : MultiplayerCell.cells) {
				cell.Update(this);
			}
			
			for (MultiplayerPool pl : MultiplayerPool.pools) {
				if (pl.isShot) {
					pl.Update();
				}
			}
			
			for (MultiplayerForest fr : MultiplayerForest.forests) {
				if (fr.couldHide) {
					fr.Update();
				}
			}
			
			for (MultiplayerMud mud : MultiplayerMud.muds) {
				for (MultiplayerCell cell : MultiplayerCell.cells) {
					if(cell.currentLv == cell.level.get(0)) {
						
					}else {
						mud.Update();
					}
				}
			}
			
			
		} catch (ConcurrentModificationException e) {
			// Throw
		}
		
	}
	
	public void generateFood(int x, int y, char type) {
	
		switch (type){
			case 'B':
				String imgBread=foodonMap.getBread();
				MultiplayerParticle.particles.add(new MultiplayerParticle(x,y, 1, false, imgBread));
				break;
			case 'C':
				String imgCheese=foodonMap.getCheese();
				MultiplayerParticle.particles.add(new MultiplayerParticle(x,y, 1, false, imgCheese));
				break;
			case 'S':
				String imgSteak=foodonMap.getSteak();
				MultiplayerParticle.particles.add(new MultiplayerParticle(x,y, 1, false, imgSteak));
				break;
			
			default:
				break;
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		for (MultiplayerCell Cell : MultiplayerCell.cells) {
			// The player
			if (Cell.id == Clients.get(0).getUserID()) {
				// GAMESTATE:ID:X:Y:LEVEL:HP:EXP
				Clients.get(0).sendMessage("GAMESTATE:" + Clients.get(0).getUserID() + ":" + Cell.goalX +":" + Cell.goalY + ":" + Cell.levelNum + ":" + Cell.currentHp + ":" + Cell.currentExp +":");
			}
		}
	}
	
	public void generateForest(int x, int y){
		MultiplayerForest.forests.add(new MultiplayerForest(x,y,400));
	}
	
	public void generatePool(int x, int y){
		MultiplayerPool.pools.add(new MultiplayerPool(x,y,400));
	}
	
	public void generateMud(int x, int y){
		MultiplayerMud.muds.add(new MultiplayerMud(x,y,400));
	}
	
	public void mouseMoved(MouseEvent e) {
		for (MultiplayerCell cell : MultiplayerCell.cells) {
			if (cell.id == Clients.get(0).getUserID()){
				cell.getMouseX((int) (e.getX() / cam.sX + cam.x));
				cell.getMouseY((int) (e.getY() / cam.sY + cam.y));
				Clients.get(0).sendMessage("MOVE:" + Clients.get(0).getUserID() + ":" + ((int)(e.getX() / cam.sX + cam.x)) +":" + ((int)(e.getY() / cam.sY + cam.y)));

			}
		}
	}
	
	public boolean getRunning() {
		return isRunning;
	}
	
	public boolean getIsHost() {
		return isHost;
	}
	
	public void sendMessage(String message) {
		Clients.get(0).sendMessage(message);
	}
}
