package Command;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.MenuPanel;
import Map.OnlineBoard;
import Multiplayer.MultiplayerBoard;
import Network.Network;
import Network.Server.Client;

public class OnlineGame extends Command{

	private JPanel onlinegame;
	private MenuPanel panel;
	private JFrame frame;
	
	public OnlineGame() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
//		onlinegame = new JPanel();
//		onlinegame.add( new OnlineBoard(panel));
//		
		onlinegame = new JPanel();
		Network Network = new Network();
		ArrayList<Client> Clients = new ArrayList<Client>();
		Clients.add(new Client(0,"DEFAULT"));
		// Create client object after entering name
		
//		onlineGame.add(new Board(Clients, false, Network,"SERVER_IP"));
		onlinegame.add(new MultiplayerBoard(Clients, true, Network, "10.115.205.19"));

	}
	public JPanel execute(){
		
		return onlinegame;
		
	}
}
