package Network.Server;

import java.io.DataOutputStream;
import java.util.ArrayList;;

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
				Thread.sleep(15);

				String message = searchClients(ClientID).getQueue().take();
				output.writeBytes(message);
				System.out.println("Server output : " + message);
				System.out.println("Client ID: " + searchClients(ClientID).getUserID());
				System.out.println("Client IP: " + searchClients(ClientID).getIP());
			} catch(Exception ex) {
				System.out.println("Server sender error: " + ex.getMessage());
				//Clients.remove(searchClients(ClientID));
				// Send new list to every clients
				ex.printStackTrace();
				// Remove client from client lists and send to all clients
				// Stop socket
				break;
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
}

