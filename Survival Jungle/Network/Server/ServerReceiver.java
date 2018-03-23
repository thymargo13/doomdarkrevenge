package Network.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Multiplayer.MultiplayerGameState;
import Multiplayer.MultiplayerParticle;
import Multiplayer.MultiplayerCell;


/**
 * @author Khang Sheong Foong
 * This is a thread running to each client from the server to receive message from the client
 */
public class ServerReceiver implements Runnable{
	private BufferedReader input;
	private int ClientID;
	private MultiplayerGameState MultiplayerGameState;
	private boolean running = false;


	ServerReceiver(BufferedReader input, int ClientID, MultiplayerGameState MultiplayerGameState){
		this.input = input;
		this.ClientID = ClientID;
		this.MultiplayerGameState = MultiplayerGameState;
		running = true;
	}

	public void run() {
		while (running) {
			try {
				String message = input.readLine();
				System.out.println("Server Receiver received: " + message);
				processMessage(message);
				// Process message
				// catch SocketException remove client from client lists and remove cells
			}
			catch (Exception ex) {
				// Remove client from client lists and send to all clients
				// Stop socket
				System.out.println("Server receiver error: " + ex.getMessage());
				
				Server.Clients.remove(searchClients(ClientID));
				
				String message = "REMOVECELL:" + ClientID;
				MultiplayerGameState.sendMessage(message);
				
				MultiplayerCell.cells.remove(searchCell(ClientID));
				MultiplayerCell.cellCount--;
				closeSocket();
				running = false;
			}
		}
	}
	
	public void processMessage(String data) {
		if (data.length() > 0) {
			String[] message = data.split(":");
			MultiplayerCell cell = searchCell(Integer.parseInt(message[1]));

			switch (message[0]) {
				case "FOODMOVE":
					//FOODMOVE:ID:X:Y
					MultiplayerParticle p = searchFood(Integer.parseInt(message[1]));
					p.x = Integer.parseInt(message[2]);
					p.y = Integer.parseInt(message[3]);
					MultiplayerGameState.sendMessage(data);
					break;
				case "NAME":
					//Sample Message : "NAME:ID:USERNAME"
					System.out.println("NAME123: " + message[2]);
					searchClients(Integer.parseInt(message[1])).setUsername(message[2]);
					MultiplayerGameState.sendMessage(data);
					break;
				case "ADDSCORE":
					//Sample Message : "ADDSCORE:ID:SCORE"
					int score = Integer.parseInt(message[2]);
					cell.addExp(score, cell);
					break;
				case "MOVE":
					//Sample Message : "MOVE:ID:X:Y"
					cell.goalX = Double.parseDouble(message[2]);
					cell.goalY = Double.parseDouble(message[3]);
					cell.x = Double.parseDouble(message[2]);
					cell.y = Double.parseDouble(message[3]);
					break;
				case "SCORE":
					// Sample Message : "SCORE:ID:MASS"
					 cell.setExp(Integer.parseInt(message[2]));
					break;
				case "LEVEL":
					// Sample Message : "LEVEL:ID:LEVELUP"
					 cell.setLevel(Integer.parseInt(message[2]));
					break;
				case "HP":
					// Sample Message : "LEVEL:ID:HP"
					cell.updateCell(cell.x, cell.y, cell.levelNum,Integer.parseInt(message[2]) , cell.currentExp);
					break;
				case "GAMESTATE":
					// GAMESTATE:ID:X:Y:LEVEL:HP:EXP
					double x = Double.parseDouble(message[2]);
					double y = Double.parseDouble(message[3]);
					int levelnum = Integer.parseInt(message[4]);
					int hp = Integer.parseInt(message[5]);
					int exp = Integer.parseInt(message[6]);
					cell.updateCell(x, y, levelnum, hp, exp);
					break;
				default:
					break;
			}
		}
	}
	
	public void closeSocket(){
		try {
			input.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Client searchClients(int ClientID) {
		for (Client Client : Server.Clients) {
			if (Client.getUserID() == ClientID) {
				return Client;
			}
		}
		return null;
	}
	
	public MultiplayerCell searchCell(int ClientID) {
		for (MultiplayerCell Cell : MultiplayerCell.cells) {
			if (Cell.id == ClientID) {
				return Cell;
			}
		}
		return null;
	}
	
	public MultiplayerParticle searchFood(int FoodID) {
		for (MultiplayerParticle p : MultiplayerParticle.particles) {
			if (p.id == FoodID) {
				return p;
			}
		}
		return null;
	}


}
