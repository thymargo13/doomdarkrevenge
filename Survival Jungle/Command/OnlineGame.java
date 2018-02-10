package Command;

import javax.swing.JPanel;

import Map.Board;

public class OnlineGame extends Command{

	private JPanel onlineGame;
	public OnlineGame() {
		onlineGame = new JPanel();
		onlineGame.add(new Board());
	}

	public JPanel execute() {
		return onlineGame;
	}
}
