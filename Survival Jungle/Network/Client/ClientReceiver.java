package Network.Client;

import java.io.BufferedReader;

import Multiplayer.MultiplayerCell;
import Multiplayer.MultiplayerGameState;
import Network.Server.*;

public class ClientReceiver implements Runnable{
	private BufferedReader input;
	private MultiplayerGameState ClientGameState = null;
	
	ClientReceiver(BufferedReader input, MultiplayerGameState MultiplayerGameState){
		this.input = input;
		this.ClientGameState = MultiplayerGameState;
	}
	String message;
	public void run(){
		while (true) {
			try {
				message = input.readLine();
				System.out.println("Received message from server: " + message);
				processMessage(message);
			} catch (Exception ex) {
				System.out.println("Client receiver error: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	
	public void processMessage(String data) {
		if (data.length() > 0) {
			try {
				String[] message = data.split(":");
				switch (message[0]) {
					case "NAME":
						//Sample Message : "NAME:ID:USERNAME"
//						searchClients(Integer.parseInt(message[1])).setUsername(message[2]);
						break;
					case "CLIENTID":
						//Sample Message : "CLIENTID:ID"
						ClientGameState.Clients.get(0).setUserID(Integer.parseInt(message[1]));
						ClientGameState.Clients.get(0).sendMessage("NAME:" + message[1] + ":" + ClientGameState.Clients.get(0).getUsername() + ":");
						break;
					case "CLIENTLIST":
						//Sample Message : "CLIENTLIST:ID:USERNAME"
						for (int i = 1; i < message.length; i = i + 2) {
							ClientGameState.Clients.add(new Client(Integer.parseInt(message[i]), message[i+1]));
						}
						break;
					case "FOODADD":
						//Sample Message : "FOODADD:X:Y:B/C/S"
						for (int i = 1; i < message.length; i = i + 3) {
							ClientGameState.generateFood(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]), message[i+2].charAt(0));
						}
						break;
					case "CELLADD":
						// CELLADD:ID:NAME:X:Y:HP:SCORE
						for (int i = 1; i < message.length; i = i + 6) {
							if (Integer.parseInt(message[1]) == ClientGameState.Clients.get(0).getUserID()) {
								searchClients(Integer.parseInt(message[i])).setUsername(message[i+1]);
								MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[i]), searchClients(Integer.parseInt(message[i])).getUsername() ,Double.parseDouble(message[1+2]) , Double.parseDouble(message[i+3]), true, Integer.parseInt(message[i+4]), Integer.parseInt(message[i+5]) ));
							} else {
								searchClients(Integer.parseInt(message[i])).setUsername(message[i+1]);
								MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[i]), searchClients(Integer.parseInt(message[i])).getUsername() ,Double.parseDouble(message[1+2]) , Double.parseDouble(message[i+4]), false, Integer.parseInt(message[i+4]), Integer.parseInt(message[i+5]) ));
							}
						}
						break;
					case "FORESTADD":
						// FORESTADD:X:Y
						for (int i = 1; i < message.length; i = i + 2) {
							ClientGameState.generateForest(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]));
						}					
						break;
					case "POOLADD":
						// FORESTADD:X:Y
						for (int i = 1; i < message.length; i = i + 2) {
							ClientGameState.generatePool(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]));
						}					
						break;
					case "MUDADD":
						// FORESTADD:X:Y
						for (int i = 1; i < message.length; i = i + 2) {
							ClientGameState.generateMud(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]));
						}					
						break;
					case "GAMESTATE":
						// GAMESTATE:ID:X:Y:LEVEL:HP:SCORE
						for (int i = 1; i < message.length; i = i + 6) {
							if (Integer.parseInt(message[i]) == ClientGameState.Clients.get(0).getUserID()) {
								continue;
							}
							
							MultiplayerCell Cell = searchCell(Integer.parseInt(message[i]));
							double x = Double.parseDouble(message[i+1]);
							double y = Double.parseDouble(message[i+2]);
							int levelnum = (Integer.parseInt(message[i+3]));
							int hp = Integer.parseInt(message[i+4]);
							int exp = Integer.parseInt(message[i+5]);
							
							Cell.updateCell(x, y, levelnum, hp, exp);
						}
						break;
					default:
						break;
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public Client searchClients(int ClientID) {
		for (Client Client : ClientGameState.Clients) {
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
