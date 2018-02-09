package Network.Server;

import java.io.DataOutputStream;
import java.util.ArrayList;

import Network.Client.Client;;

public class ServerSender implements Runnable {
	private DataOutputStream output;
	private ArrayList<Client> Clients;
	private int ClientID;
	
	public ServerSender(DataOutputStream output, ArrayList<Client> Clients, int ClientID) {
		this.output = output;
		this.Clients = Clients;
		this.ClientID = ClientID;
	}

	public void run() {
		while (true) {
			try {
				String message = Clients.get(ClientID).getQueue().take();
				output.writeBytes(message);
				System.out.println("Server output : " + message);
				System.out.println("Client ID:" +Clients.get(ClientID).getUserID());
				System.out.println("Client IP: " + Clients.get(ClientID).getIP());
			} catch(Exception ex) {
				System.out.println("Server outstream error: " +ex.getMessage());				
				// Remove client from client lists and send to all clients
				// Stop socket
				break;
			}
		}
	}
}

