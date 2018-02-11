package Command;

import javax.swing.JPanel;

import Map.Board;
import Network.Network;

public class OnlineGame extends Command{

	private JPanel onlineGame;
	public OnlineGame() {
		onlineGame = new JPanel();
		Network network = new Network();
		
		
		onlineGame.add(new Board());
	}

	public JPanel execute() {
		return onlineGame;
	}
}
