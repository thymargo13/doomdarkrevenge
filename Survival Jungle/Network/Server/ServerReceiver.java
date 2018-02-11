package Network.Server;

import java.io.BufferedReader;
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
				System.out.print("Server Receiver received: " + message);
				processMessage(message);
				// Process message
				// 
			} catch (Exception ex) {
				// Remove client from client lists and send to all clients
				// Stop socket
			}
		}
	}
	
	public void processMessage(String data) {
		if (data.length() > 0) {
			String[] message = data.split(":");
			switch (message[0]) {
				case "CLIENTID":
					//Sample Message : "CLIENTID:ID"
					MultiplayerGameState.Clients.get(0).setUserID(Integer.parseInt(message[1]));
					MultiplayerGameState.Clients.get(0).sendMessage("NAME:" + MultiplayerGameState.Clients.get(0).getUsername());
					break;
				case "CLIENTLIST":
					//Sample Message : "CLIENTLIST:ID:USERNAME"
					for (int i = 1; i < message.length; i = i + 2) {
						MultiplayerGameState.Clients.add(new Client(Integer.parseInt(message[i]), message[i+1]));
					}
					break;
				case "FOODADD":
					//Sample Message : "FOODADD:X:Y"
					MultiplayerGameState.generateFood(Integer.parseInt(message[1]), Integer.parseInt(message[2]));
					break;
				case "CELLADD":
					//Sample Message : "CELLADD:ID:X:Y"
					if (Integer.parseInt(message[1]) == MultiplayerGameState.Clients.get(0).getUserID()) {
						MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[1]), searchClients(Integer.parseInt(message[1])).getUsername() ,Double.parseDouble(message[2]) , Double.parseDouble(message[3]), true));
					} else {
						MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[1]), searchClients(Integer.parseInt(message[1])).getUsername() ,Double.parseDouble(message[2]) , Double.parseDouble(message[3]), false));
					}

					break;
				case "MOVE":
					//Sample Message : "MOVE:ID:X:Y"
					MultiplayerCell.cells.get(Integer.parseInt(message[1])).goalX = Double.parseDouble(message[2]);
					MultiplayerCell.cells.get(Integer.parseInt(message[1])).goalY = Double.parseDouble(message[3]);
					break;
				default:
					break;
			}
			
			for(Client c : MultiplayerGameState.Clients) {
				if (c.getUserID() != Integer.parseInt(message[1])) {
					c.getQueue().add(data);
				}
			}
		}
	}
	
	public Client searchClients(int ClientID) {
		for (Client Client : MultiplayerGameState.Clients) {
			if (Client.getUserID() == ClientID) {
				return Client;
			}
		}
		return null;
	}

}
