package Network.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import Local.Particle;
import Multiplayer.MultiplayerCell;
import Multiplayer.ServerForest;
import Multiplayer.ServerGameState;
import Multiplayer.ServerMud;
import Multiplayer.ServerPool;

public class ServerHost implements Runnable {
	private ServerSocket ServerSocket;
	private ServerGameState ServerGameState;

	ServerHost(ServerSocket ServerSocket, ServerGameState ServerGameState){
		this.ServerSocket = ServerSocket;
		this.ServerGameState = ServerGameState;
	}
	
	public void run() {
		int i = 1;
		while (true) {
			Socket Socket;
			try {
				Socket = ServerSocket.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
				DataOutputStream output = new DataOutputStream(Socket.getOutputStream());

				Client Client = new Client(i, Socket.getInetAddress());
				Server.Clients.add(Client);
				System.out.println("Client : " + Client.getUserID() + " connected from IP " + Client.getIP() + " .");
								
				// Send Client own ID
				Client.sendMessage("CLIENTID:" + i);
				// Send the Clients list
				String message = "CLIENTLIST:";
				for (Client c : Server.Clients) {
					if (c.getUserID() != i) {
						message = message + c.getUserID() + ":" + c.getUsername() + ":";
					}
				}
				Client.sendMessage(message);
				
				// if game already running
				if (ServerGameState.getRunning()) {
					message = "CELLADD:";
					for (MultiplayerCell c : MultiplayerCell.cells) {
						// CELLADD:ID:X:Y:HP:SCORE
						message = message + c.id + ":" + c.x +":" + c.y + ":" + c.currentHp + ":" + c.currentExp + ":";

					}
					Client.sendMessage(message);

					message = "FOODADD";
					for (Particle p : Particle.particles) {
						if (!p.getHealth()) {
							message  = message + ":" + p.x + ":" + p.y + ":";
						}
					}
					Client.sendMessage(message);
					
					message = "FORESTADD:";
					for (ServerForest f : ServerForest.serverForests) {
						message = message + f.getX() + ":" + f.getY() + ":";
					}
					Client.sendMessage(message);
					
					message = "POOLADD:";
					for (ServerPool p : ServerPool.serverPools) {
						message = message + p.getX() + ":" + p.getY() + ":";
					}
					Client.sendMessage(message);
					
					message = "MUDADD:";
					for (ServerMud m : ServerMud.serverMuds) {
						message = message + m.getX() + ":" + m.getY() + ":";
					}
					Client.sendMessage(message);
				}
				
				ServerSender ServerSender = new ServerSender(output,i);
				Thread SenderThread = new Thread(ServerSender);
				SenderThread.start();
				System.out.println("Output stream for client " + Client.getUserID() + " established.");

				ServerReceiver ServerReceiver = new ServerReceiver(input,i,ServerGameState);
				Thread ReceiverThread = new Thread(ServerReceiver);
				ReceiverThread.start();
				System.out.println("Input stream for client " + Client.getUserID() + " established.");

				i++;
			} catch (Exception ex) {
				System.out.println("Server host error : " +ex.getMessage());
				break;
			}
		}
	}
}
