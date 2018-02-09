package Network.Server;

import java.io.DataOutputStream;

import Network.Client.Client;;

public class ServerSender implements Runnable {
	private DataOutputStream output;
	private Client Client;
	
	public ServerSender(DataOutputStream output, Client Client) {
		this.Client = Client;
		this.output = output;
	}

	public void run() {
		while (true) {
			try {
				String message = this.Client.getQueue().take();
				output.writeBytes(message);
				System.out.println("Server output : " + message + "\nClient ID:"+Client.getUserID()+"+\nDestination:"+Client.getIP());
			} catch(Exception ex) {
				System.out.println("Server outstream error: " +ex.getMessage());				
				// Remove client from client lists and send to all clients
				break;
			}
		}
	}
}

