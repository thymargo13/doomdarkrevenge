package Network.Client;

import java.io.BufferedReader;
import Multiplayer.MultiplayerGameState;;

public class ClientReceiver implements Runnable{
	private BufferedReader input;
	private MultiplayerGameState MultiplayerGameState = null;
	
	ClientReceiver(BufferedReader input, MultiplayerGameState MultiplayerGameState){
		this.input = input;
		this.MultiplayerGameState = MultiplayerGameState;
	}

	public void run(){
		while (true) {
			try {
				String message = input.readLine();
				processMessage(message);
				System.out.println("Received message from server: " + message);
			} catch (Exception ex) {
			}
		}
	}
	
	public void processMessage(String message) {
		if (message.length() > 0) {
			String[] messages = message.split(":");
			switch (messages[0]) {
				case "CLIENTID":
					String clientID = message;
					//MultiplayerGameState.Clients.get(0).setID(Integer.parseInt(message[1]));
					break;
				default:
					break;
			}
		}
	}
}
