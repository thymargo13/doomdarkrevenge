package Command;
import javax.swing.JPanel;


import Map.OnlineBoard;


public class OnlineGame extends Command{

	private JPanel onlinegame;
	
	public OnlineGame() {
		onlinegame = new JPanel();
		onlinegame.add( new OnlineBoard());
	}
	public JPanel execute(){
		
		return onlinegame;
		
	}
}
