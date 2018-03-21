package Network.Client;

import java.io.BufferedReader;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Multiplayer.MultiplayerCell;
import Multiplayer.MultiplayerGameState;
import Multiplayer.MultiplayerParticle;
import Network.Server.*;

public class ClientReceiver implements Runnable{
	private BufferedReader input;
	private MultiplayerGameState MultiplayerGameState = null;
	boolean running = true;
	
	ClientReceiver(BufferedReader input, MultiplayerGameState MultiplayerGameState){
		this.input = input;
		this.MultiplayerGameState = MultiplayerGameState;
	}
	
	String message;
	public void run(){
		while (running) {
			try {
				message = input.readLine();
				System.out.println("Received message from server: " + message);
				processMessage(message);
			} catch (Exception ex) {
				System.out.println("Client receiver error: " + ex.getMessage());
				closeSocket();
				running = false;
				
				JOptionPane.showMessageDialog(null, "Disconnected from server.", "Error", JOptionPane.ERROR_MESSAGE);				
				System.exit(0);
//				Stop game
			}
		}
	}
	
	public void processMessage(String data) {
		if (data.length() > 0) {
			try {
				String[] message = data.split(":");
				switch (message[0]) {
					case "FOODMOVE":
						//FOODMOVE:ID:X:Y
						MultiplayerParticle p = searchFood(Integer.parseInt(message[1]));
						p.x = Integer.parseInt(message[2]);
						p.y = Integer.parseInt(message[3]);
						break;
					case "CLIENTID":
						//Sample Message : "CLIENTID:ID"
						MultiplayerGameState.Clients.get(0).setUserID(Integer.parseInt(message[1]));
						MultiplayerGameState.Clients.get(0).sendMessage("NAME:" + message[1] + ":" + MultiplayerGameState.Clients.get(0).getUsername() + ":");
						break;
					case "CLIENTLIST":
						//Sample Message : "CLIENTLIST:ID:USERNAME"
						for (int i = 1; i < message.length; i = i + 2) {
							MultiplayerGameState.Clients.add(new Client(Integer.parseInt(message[i]), message[i+1]));
						}
						break;
					case "ADDSCORE":
						//Sample Message : "ADDSCORE:ID:SCORE"
						MultiplayerCell Cell1 = searchCell(Integer.parseInt(message[1]));
						int score = Integer.parseInt(message[2]);
						Cell1.addExp(score, Cell1);
						break;
					case "FOODADD":
						//Sample Message : "FOODADD:ID:X:Y:B/C/S"
						for (int i = 1; i < message.length; i = i + 4) {
							MultiplayerGameState.generateFood(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]), Integer.parseInt(message[i+2]), message[i+3].charAt(0));
						}
						break;
					case "CELLADD":
						// CELLADD:ID:NAME:X:Y:HP:SCORE
						for (int i = 1; i < message.length; i = i + 6) {
							if (Integer.parseInt(message[1]) == MultiplayerGameState.Clients.get(0).getUserID()) {
								searchClients(Integer.parseInt(message[i])).setUsername(message[i+1]);
								MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[i]), message[i+1] ,Double.parseDouble(message[i+2]) , Double.parseDouble(message[i+3]), true, Integer.parseInt(message[i+4]), Integer.parseInt(message[i+5]) ));
							} else {
//								searchClients(Integer.parseInt(message[i])).setUsername(message[i+1]);
								MultiplayerCell.cells.add(new MultiplayerCell(Integer.parseInt(message[i]), message[i+1] ,Double.parseDouble(message[i+2]) , Double.parseDouble(message[i+4]), false, Integer.parseInt(message[i+4]), Integer.parseInt(message[i+5]) ));
							}
						}
						break;
					case "FORESTADD":
						// FORESTADD:X:Y
						for (int i = 1; i < message.length; i = i + 2) {
							MultiplayerGameState.generateForest(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]));
						}					
						break;
					case "POOLADD":
						// FORESTADD:X:Y
						for (int i = 1; i < message.length; i = i + 2) {
							MultiplayerGameState.generatePool(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]));
						}					
						break;
					case "MUDADD":
						// FORESTADD:X:Y
						for (int i = 1; i < message.length; i = i + 2) {
							MultiplayerGameState.generateMud(Integer.parseInt(message[i]), Integer.parseInt(message[i+1]));
						}					
						break;
					case "GAMESTATE":
						// GAMESTATE:ID:X:Y:LEVEL:HP:SCORE
						for (int i = 1; i < message.length; i = i + 6) {
							MultiplayerCell Cell = searchCell(Integer.parseInt(message[i]));
							double x = Double.parseDouble(message[i+1]);
							double y = Double.parseDouble(message[i+2]);
							int levelnum = (Integer.parseInt(message[i+3]));
							int hp = Integer.parseInt(message[i+4]);
							int exp = Integer.parseInt(message[i+5]);
							
							if (Integer.parseInt(message[i]) != MultiplayerGameState.Clients.get(0).getUserID()) {
								Cell.updateCell(x, y, levelnum, hp, exp);
							}
//							} else {
//								Cell.updateCell(Cell.goalX, Cell.goalY, levelnum, hp, exp);
//							}
							
						}
						break;
					case "MOVE":
						MultiplayerCell c = searchCell(Integer.parseInt(message[1]));
						//Sample Message : "MOVE:ID:X:Y"
						c.goalX = Double.parseDouble(message[2]);
						c.goalY = Double.parseDouble(message[3]);
						c.x = Double.parseDouble(message[2]);
						c.y = Double.parseDouble(message[3]);
						break;
					case "RESPAWN":
						MultiplayerCell ce = searchCell(Integer.parseInt(message[1]));
						//Sample Message : "RESPAWN:ID:X:Y:LEVEL"
						ce.goalX = Double.parseDouble(message[2]);
						ce.goalY = Double.parseDouble(message[3]);
						ce.x = Double.parseDouble(message[2]);
						ce.y = Double.parseDouble(message[3]);
						int level = Integer.parseInt(message[4]);
						ce.updateCell(ce.x, ce.y, level , ce.level.get(level).getHealth(), 0);
						break;
					case "HP":
						MultiplayerCell cel = searchCell(Integer.parseInt(message[1]));
						// Sample Message : "LEVEL:ID:LEVELUP"
						cel.updateCell(cel.x, cel.y, cel.levelNum,Integer.parseInt(message[2]) , cel.currentExp);
						break;
					case "REMOVECELL":
						// REMOVECELL:ID
						MultiplayerCell.cells.remove(searchCell(Integer.parseInt(message[1])));
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
		for (Client Client : MultiplayerGameState.Clients) {
			if (Client.getUserID() == ClientID) {
				return Client;
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
	
	public MultiplayerCell searchCell(int ClientID) {
		for (MultiplayerCell Cell : MultiplayerCell.cells) {
			if (Cell.id == ClientID) {
				return Cell;
			}
		}
		return null;
	}
	
	public void closeSocket(){
		try {
			input.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}