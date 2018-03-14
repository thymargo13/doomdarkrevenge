package Network.Client;

import java.io.BufferedReader;

import Multiplayer.MultiplayerCell;
import Multiplayer.ClientGameState;
import Network.Server.*;

public class ClientReceiver implements Runnable{
	private BufferedReader input;
	private ClientGameState MultiplayerGameState = null;
	
	ClientReceiver(BufferedReader input, ClientGameState MultiplayerGameState){
		this.input = input;
		this.MultiplayerGameState = MultiplayerGameState;
	}
	String message;
	public void run(){
		while (true) {
			try {
				message = input.readLine();
				processMessage(message);
				// Create thread to process message
				System.out.println("Received message from server: " + message);
			} catch (Exception ex) {
				System.out.println("Client receiver error: " + ex.getMessage());
				ex.printStackTrace();
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
					MultiplayerGameState.Clients.get(0).sendMessage("NAME:" + message[1] + ":" + MultiplayerGameState.Clients.get(0).getUsername());
					break;
				case "CLIENTLIST":
					//Sample Message : "CLIENTLIST:ID:USERNAME"
					for (int i = 1; i < message.length; i = i + 2) {
						MultiplayerGameState.Clients.add(new Client(Integer.parseInt(message[i]), message[i+1]));
					}
					break;
				case "FOODADD":
					//Sample Message : "FOODADD:X:Y"
					for (int i = 1; i < message.length; i = i + 2) {
						MultiplayerGameState.generateFood(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]));
					}
//					MultiplayerGameState.generateFood(Integer.parseInt(message[1]), Integer.parseInt(message[2]));
					break;
				case "CELLADD":
					//Sample Message : "CELLADD:ID:X:Y"
					// Looping
					// Set loaded = true, player only can play when loaded = true 
					if (Integer.parseInt(message[1]) == MultiplayerGameState.Clients.get(0).getUserID()) {
						MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[1]), searchClients(Integer.parseInt(message[1])).getUsername() ,Double.parseDouble(message[2]) , Double.parseDouble(message[3]), true));
					} else {
						MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[1]), searchClients(Integer.parseInt(message[1])).getUsername() ,Double.parseDouble(message[2]) , Double.parseDouble(message[3]), false));
					}
					break;
				case "GAMESTATE":
					// GAMESTATE:ID:X:Y:HP:SCORE
					for (int i = 1; i < message.length; i = i + 5) {
						MultiplayerCell Cell = searchCell(Integer.parseInt(message[i]));
						Cell.goalX = Double.parseDouble(message[i+1]);
						Cell.goalY = Double.parseDouble(message[i+2]);
						Cell.currentHp = Integer.parseInt(message[i+3]);
						Cell.currentExp = Integer.parseInt(message[i+4]);
					}
					break;
//				case "MOVE":
//					//Sample Message : "MOVE:ID:X:Y"
//					searchCell(Integer.parseInt(message[1])).goalX = Double.parseDouble(message[2]);
//					searchCell(Integer.parseInt(message[1])).goalY = Double.parseDouble(message[3]);
//					break;
//				case "SCORE":
//					// Sample Message : "SCORE:ID:MASS"
//					searchCell(Integer.parseInt(message[1])).currentExp = (int) Double.parseDouble(message[2]);
//					break;
				default:
					break;
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
	
	public MultiplayerCell searchCell(int ClientID) {
		for (MultiplayerCell Cell : MultiplayerCell.cells) {
			if (Cell.id == ClientID) {
				return Cell;
			}
		}
		return null;
	}
}
