package Network.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import Multiplayer.MultiplayerCell;
import Multiplayer.ServerCell;
import Multiplayer.ServerGameState;

public class ServerReceiver implements Runnable {
	private BufferedReader input;
	private int ClientID;
	private ServerGameState ServerGameState;
	private boolean running = false;
	
	ServerReceiver(BufferedReader input, int ClientID, ServerGameState ServerGameState){
		this.input = input;
		this.ClientID = ClientID;
		this.ServerGameState = ServerGameState;
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
				
				String message = "CLIENTLIST:";
				for (Client c : Server.Clients) {
					message = message + c.getUserID() + ":" + c.getUsername() + ":";
				}
				ServerGameState.sendMessage(message);
				
				MultiplayerCell.cells.remove(searchCell(ClientID));

				closeSocket();
				running = false;
			}
		}
	}
	
	public void processMessage(String data) {
		if (data.length() > 0) {
			String[] message = data.split(":");
			ServerCell cell = searchCell(Integer.parseInt(message[1]));

			switch (message[0]) {
				case "NAME":
					//Sample Message : "NAME:ID:USERNAME"
					searchClients(Integer.parseInt(message[1])).setUsername(message[2]);
					ServerGameState.sendMessage(data);
					break;
				case "MOVE":
					//Sample Message : "MOVE:ID:X:Y"
					cell.goalX = Double.parseDouble(message[2]);
					cell.goalY = Double.parseDouble(message[3]);
					break;
				case "SCORE":
					// Sample Message : "SCORE:ID:MASS"
					 cell.setExp(Integer.parseInt(message[2]));
					break;
				case "LEVEL":
					// Sample Message : "LEVEL:ID:LEVELUP"
					 cell.setLevel(Integer.parseInt(message[2]));
					break;
				case "GAMESTATE":
					// GAMESTATE:ID:X:Y:LEVEL:HP:EXP
					cell.goalX = Double.parseDouble(message[2]);
					cell.goalY = Double.parseDouble(message[3]);
					cell.levelNum = Integer.parseInt(message[4]);
					cell.currentHp = Integer.parseInt(message[5]);
					cell.currentExp = Integer.parseInt(message[6]);
					
					break;
				default:
					break;
			}
			
			for(Client c : Server.Clients) {
				if (c.getUserID() != Integer.parseInt(message[1]) && c.getUserID() != 0) {
					c.getQueue().add(data);
				}
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
	
	public ServerCell searchCell(int ClientID) {
		for (ServerCell Cell : ServerCell.serverCells) {
			if (Cell.id == ClientID) {
				return Cell;
			}
		}
		return null;
	}

}
