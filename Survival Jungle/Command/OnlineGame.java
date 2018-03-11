package Command;

import java.util.ArrayList;

import javax.swing.JPanel;

import Multiplayer.*;
import Network.Network;
import Network.Server.Client;

public class OnlineGame extends Command{

	private JPanel onlineGame;
	public OnlineGame() {
		onlineGame = new JPanel();
		Network Network = new Network();
		ArrayList<Client> Clients = new ArrayList<Client>();
		Clients.add(new Client(0,"DEFAULT"));
		// Create client object after entering name
		
//		onlineGame.add(new Board(Clients, false, Network,"SERVER_IP"));
		onlineGame.add(new MultiplayerBoard(Clients, true, Network, "10.115.205.19"));
	}

	public JPanel execute() {
		return onlineGame;
	}
}
