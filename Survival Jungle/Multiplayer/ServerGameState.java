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
		isRunning = false;
		timer = new Timer(DELAY, this);	// Every DELAY ms the timer will call the actionPerformed()
		timer.start();
//		MultiplayerData MultiplayerData = new MultiplayerData(this);
//		Thread MultiplayerThread = new Thread(MultiplayerData);
//		MultiplayerThread.start();
	}
	
	public void update() { 
		if (isRunning) {
			for (int i = MultiplayerCell.cellCount; i < Clients.size(); i++) {
				boolean isPlayer = false;
				if (i == 0) {
					isPlayer = true;
				}
				int x = (int) Math.floor(Math.random() * 10001);
				int y = (int) Math.floor(Math.random() * 2801);
				MultiplayerCell c = new MultiplayerCell(Clients.get(i).getUserID(), Clients.get(i).getUsername(),x , y, isPlayer); 
				MultiplayerCell.serverCells.add(c);
				// CELLADD:ID:X:Y:HP:SCORE
				Network.sendAsServer("CELLADD:" + Clients.get(i).getUserID() + ":" + x + ":" + y + ":" + c.currentHp + ":" + c.currentExp);
				// Send as server to all
			}
			
			if (MultiplayerParticle.particleCount < 500) {
				String message = "FOODADD:";
				while (MultiplayerParticle.particleCount < 500) {	// generate food
					int x = (int) Math.floor(Math.random() * 10001);
					int y = (int) Math.floor(Math.random() * 10001);
					MultiplayerParticle.particles.add(new MultiplayerParticle(x, y, 1, false));
					message = message + x + ":" + y + ":";
					// add to server queue
				}
				Network.sendAsServer(message);
			}
			
			String message= "GAMESTATE:";
			for (Client Client : Clients) {
				MultiplayerCell Cell = searchCell(Client.getUserID());
				// GAMESTATE:ID:X:Y:HP:SCORE
				message = message + Client.getUserID() + ":" + Cell.x + ":" + Cell.y + ":" + Cell.currentHp + ":" + Cell.currentExp;
			}
			
			Network.sendAsServer(message);
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
	
	public MultiplayerCell searchCell(int ClientID) {
		for (MultiplayerCell Cell : MultiplayerCell.serverCells) {
			if (Cell.id == ClientID) {
				return Cell;
			}
		}
		return null;
	}
}
