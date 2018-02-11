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
import Multiplayer.MultiplayerGameState;

public class ServerHost implements Runnable {
	private ServerSocket ServerSocket;
	private ArrayList<Client> Clients;
	private MultiplayerGameState MultiplayerGameState;

	ServerHost(ServerSocket ServerSocket, ArrayList<Client> Clients, MultiplayerGameState MultiplayerGameState){
		this.ServerSocket = ServerSocket;
		this.Clients = Clients;
		this.MultiplayerGameState = MultiplayerGameState;
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
				Clients.add(Client);
				System.out.println("Client : " + Client.getUserID() + " connected from IP " + Client.getIP() + " .");
				
				// Send Client own ID
				Client.sendMessage("CLIENTID:" + i);
				// Send the Clients list
				String message = "CLIENTLIST:";
				for (Client c : Clients) {
					if (c.getUserID() != i) {
						message = message + c.getUserID() + ":" + c.getUsername() + ":";
					}
				}
				Client.sendMessage(message);
				
				// if game already running
				if (MultiplayerGameState.getRunning()) {
					for (MultiplayerCell c : MultiplayerCell.cells) {
						int x = (int) Math.floor(Math.random() * 10001);
						int y = (int) Math.floor(Math.random() * 2801);
						c.goalX = x;
						c.goalY = y;
						Client.sendMessage("CELLADD:" + c.id + ":" + x +":" + y);
					}
					
					message = "FOODADD";
					for (Particle p : Particle.particles) {
						if (!p.getHealth()) {
							message  = message + ":" + p.x + ":" + p.y;
						}
					}
					Client.sendMessage(message);
				}
				
				
				ServerSender ServerSender = new ServerSender(output,Clients,i);
				Thread SenderThread = new Thread(ServerSender);
				SenderThread.start();
				System.out.println("Output stream for client " + Client.getUserID() + " established.");

				ServerReceiver ServerReceiver = new ServerReceiver(input,Clients,i,MultiplayerGameState);
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
