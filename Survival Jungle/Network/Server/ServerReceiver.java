package Network.Server;

import java.io.BufferedReader;
import java.net.SocketException;
import java.util.ArrayList;

import Multiplayer.MultiplayerCell;
import Multiplayer.MultiplayerGameState;

public class ServerReceiver implements Runnable {
	private BufferedReader input;
	private ArrayList<Client> Clients;
	private int ClientID;
	private MultiplayerGameState MultiplayerGameState;

	ServerReceiver(BufferedReader input, ArrayList<Client> Clients, int ClientID, MultiplayerGameState MultiplayerGameState){
		this.input = input;
		this.Clients = Clients;
		this.ClientID = ClientID;
	}

	public void run() {
		while (true) {
			try {
				String message = input.readLine();
				System.out.println("Server Receiver received: " + message);
				processMessage(message);
				// Process message
				// catch SocketException remove client from client lists and remove cells
			} catch (SocketException e) {
				
			}
			catch (Exception ex) {
				// Remove client from client lists and send to all clients
				// Stop socket
				System.out.println("Server receiver error: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	
	public void processMessage(String data) {
		if (data.length() > 0) {
			String[] message = data.split(":");
			MultiplayerCell cell = searchCell(Integer.parseInt(message[1]));
			switch (message[0]) {
				case "NAME":
					//Sample Message : "NAME:ID:USERNAME"
					cell.name = message[2];
					break;
				case "MOVE":
					//Sample Message : "MOVE:ID:X:Y"
					cell.goalX = Double.parseDouble(message[2]);
					cell.goalY = Double.parseDouble(message[3]);
					break;
				case "SCORE":
					// Sample Message : "SCORE:ID:MASS"
					cell.mass = Double.parseDouble(message[2]);
					break;
				default:
					break;
			}
			
			for(Client c : Clients) {
				if (c.getUserID() != Integer.parseInt(message[1]) && c.getUserID() != 0) {
					c.getQueue().add(data);
				}
			}
		}
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
		for (MultiplayerCell Cell : MultiplayerCell.cells) {
			if (Cell.id == ClientID) {
				return Cell;
			}
		}
		return null;
	}

}
