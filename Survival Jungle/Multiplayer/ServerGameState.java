package Multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.foodonMap;
import Network.Network;
import Network.Server.Client;

public class ServerGameState implements ActionListener{
	
	public static MultiplayerLeaderboard lb;
	public static MultiplayerCamera cam;
	private BufferedImage backBuffer;
	private Graphics g;
	private JPanel jpanel;
	
	public ArrayList<Client> Clients;
	Network Network;
	private boolean isRunning = false;
	
	private final int DELAY = 10;	// milliseconds delay
	private Timer timer;
	
	// Multiplayer
	public ServerGameState(ArrayList<Client> Clients, Network Network) {
		this.Clients = Clients;
		this.Network = Network;
		isRunning = true;
		timer = new Timer(DELAY, this);	// Every DELAY ms the timer will call the actionPerformed()
		timer.start();

	}
	
	public void update() { 
		if (isRunning) {
			for (int i = ServerCell.cellCount; i < Clients.size(); i++) {

				int x = (int) Math.floor(Math.random() * 10001);
				int y = (int) Math.floor(Math.random() * 2801);
				ServerCell c = new ServerCell(Clients.get(i).getUserID(), Clients.get(i).getUsername(),x , y);
				c.goalX = x;
				c.goalY = y;
				ServerCell.serverCells.add(c);
				// CELLADD:ID:X:Y:HP:SCORE
				Network.sendAsServer("CELLADD:" + Clients.get(i).getUserID() + ":" + x + ":" + y + ":" + c.currentHp + ":" + c.currentExp + ":");
				// Send as server to all
			}
			
			if (ServerParticle.particleCount < 500) {	// generate food
				String message = "FOODADD:";
				while (ServerParticle.particleCount < 500){
					int x = (int) Math.floor(Math.random() * 10001);
					int y = (int) Math.floor(Math.random() * 10001);
					String imgBread=foodonMap.getBread();
					ServerParticle.serverParticles.add(new ServerParticle(x,y, 1, false, imgBread));
					// FOODADD:X:Y:B
					message = message + x + ":" + y + ":B" + ":";
					
					x = (int) Math.floor(Math.random() * 10001);
					y = (int) Math.floor(Math.random() * 10001);
					String imgCheese=foodonMap.getCheese();
					ServerParticle.serverParticles.add(new ServerParticle(x,y, 1, false, imgCheese));
					// FOODADD:X:Y:C
					message = message + x + ":" + y + ":C" + ":";

					
					x = (int) Math.floor(Math.random() * 10001);
					y = (int) Math.floor(Math.random() * 10001);
					String imgSteak=foodonMap.getSteak();
					ServerParticle.serverParticles.add(new ServerParticle(x,y, 1, false, imgSteak));
					//FOODADD:X:Y:S
					message = message + x + ":" + y + ":S" + ":";
				}
				Network.sendAsServer(message);			
			}
			
			if (ServerForest.forestCount < 100) {
				String message = "FORESTADD:";
				while (ServerForest.forestCount < 100) {
					int x = (int) Math.floor(Math.random() * 10001);
					int y = (int) Math.floor(Math.random() * 10001);
					ServerForest.serverForests.add(new ServerForest(x, y,400));
					message = message + x + ":" + y + ":" ;
				}
				Network.sendAsServer(message);
			}
			
			if (ServerPool.poolCount < 150) {
				String message = "POOLADD:";
				while (ServerPool.poolCount < 150) {
					int x = (int) Math.floor(Math.random() * 10001);
					int y = (int) Math.floor(Math.random() * 10001);
					ServerPool.serverPools.add(new ServerPool(x,y,400));
					message = message + x + ":" + y + ":" ;
				}
				Network.sendAsServer(message);

			}
			
			if (ServerMud.mudCount < 100) {
				String message = "MUDADD:";
				while (ServerMud.mudCount < 100) {
					int x = (int) Math.floor(Math.random() * 10001);
					int y = (int) Math.floor(Math.random() * 10001);
					ServerMud.serverMuds.add(new ServerMud(x,y,400));
					message = message + x + ":" + y + ":" ;
				}
				Network.sendAsServer(message);
			}
			
			if (Clients.size() > 0) {
				String message= "GAMESTATE:";
				for (Client Client : Clients) {
					ServerCell Cell = searchCell(Client.getUserID());
					// GAMESTATE:ID:X:Y:HP:SCORE
					message = message + Client.getUserID() + ":" + Cell.goalX + ":" + Cell.goalY + ":" + Cell.currentLv.getLevel() +":" +  Cell.currentHp + ":" + Cell.currentExp + ":";
				}
				Network.sendAsServer(message);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		update();
		// Send gamestate
	}
	
	public ArrayList<Client> getServerClients(){
		return Clients;
	}

	public boolean getRunning() {
		return isRunning;
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	// Server Game State Send message always send as server
	public void sendMessage(String message) {
		Network.sendAsServer(message);
	}
	
	public Client searchClients(int ClientID) {
		for (Client Client : Clients) {
			if (Client.getUserID() == ClientID) {
				return Client;
			}
		}
		return null;
	}
	
	public ServerCell searchCell(int ClientID) {
		for (ServerCell Cell : ServerCell.serverCells) {
			if (Cell.id == ClientID) {
				return Cell;
			}
		}
		return null;
	}
}
