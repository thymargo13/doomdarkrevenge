package Network.Server;

import java.io.BufferedReader;
import java.util.ArrayList;

import Network.Client.*;

public class ServerReceiver implements Runnable {
	private BufferedReader input;
	private ArrayList<Client> Clients;
	private int ClientID;

	ServerReceiver(BufferedReader input, ArrayList<Client> Clients, int ClientID){
		this.input = input;
		this.Clients = Clients;
		this.ClientID = ClientID;
	}

	public void run() {
		while (true) {
			try {
				String message = input.readLine();
				System.out.println("Server Receiver received: " + message);
				// Process message
				// 
			} catch (Exception ex) {
				// Remove client from client lists and send to all clients
				// Stop socket
			}

		}

	}

}
